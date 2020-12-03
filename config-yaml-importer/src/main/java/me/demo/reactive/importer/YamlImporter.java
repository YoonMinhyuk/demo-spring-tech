package me.demo.reactive.importer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class YamlImporter implements EnvironmentPostProcessor {
    final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(
            final ConfigurableEnvironment environment,
            final SpringApplication application
    ) {
        final List<String> yamlLocationPatterns = Arrays.asList(
                "classpath*:/**/application-*.yml",
                "classpath*:/**/application-*.yaml"
        );
        final PathMatchingResourcePatternResolver resolver = generatePathMatchingResourcePatternResolver(application);
        final MutablePropertySources propertySources = environment.getPropertySources();

        yamlLocationPatterns.stream()
                            .map(locationPattern -> getPropertySourceResources(resolver, locationPattern))
                            .flatMap(Arrays::stream)
                            .filter(resource -> Objects.nonNull(resource.getFilename()))
                            .flatMap(resource -> loadYaml(environment, resource).stream())
                            .forEach(propertySources::addLast);
    }

    private PathMatchingResourcePatternResolver generatePathMatchingResourcePatternResolver(
            final SpringApplication application
    ) {
        return Optional.ofNullable(application.getResourceLoader())
                       .map(PathMatchingResourcePatternResolver::new)
                       .orElseGet(PathMatchingResourcePatternResolver::new);
    }

    private Resource[] getPropertySourceResources(
            final PathMatchingResourcePatternResolver resolver,
            final String locationPattern
    ) {
        try {
            return resolver.getResources(locationPattern);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PropertySource<?>> loadYaml(
            final ConfigurableEnvironment environment,
            final Resource resource
    ) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource " + resource + " does not exist");
        }

        try {
            return yamlPropertySourceLoader.load(resource.getFilename(), resource)
                                           .stream()
                                           .filter(propertySource -> isNotDuplicatePropertySource(environment, propertySource))
                                           .filter(propertySource -> isAcceptsProfiles(environment, propertySource))
                                           .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load yaml configuration from " + resource, e);
        }
    }

    private boolean isNotDuplicatePropertySource(
            final ConfigurableEnvironment environment,
            final PropertySource<?> propertySource
    ) {
        final String newPropertySourceName = propertySource.getName();
        return environment.getPropertySources()
                          .stream()
                          .map(PropertySource::getName)
                          .noneMatch(existentPropertySourceName ->
                                  existentPropertySourceName.contains(
                                          newPropertySourceName.substring(0, newPropertySourceName.lastIndexOf("."))
                                  )
                          );
    }

    private boolean isAcceptsProfiles(ConfigurableEnvironment environment, PropertySource<?> propertySource) {
        final Binder binder = new Binder(ConfigurationPropertySources.from(propertySource));
        return binder.bind("spring.config.activate.on-profile", Bindable.of(String[].class))
                     .map(Arrays::asList)
                     .orElseGet(ArrayList::new)
                     .stream()
                     .anyMatch(propertyProfile -> environment.acceptsProfiles(Profiles.of(propertyProfile)));
    }
}
