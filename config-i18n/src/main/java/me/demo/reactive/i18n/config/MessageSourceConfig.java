package me.demo.reactive.i18n.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MessageSourceConfig {
    @Bean
    public MessageSourceSupport messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public ApplicationRunner messageSourceRunner(final ReloadableResourceBundleMessageSource messageSource) {
        return args -> {
            final String messageSourcesLocationPattern = "classpath*:/i18n/*message_*.properties";
            final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            for (final Resource resource : resolver.getResources(messageSourcesLocationPattern)) {
                Optional.ofNullable(resource.getFilename())
                        .map(s -> s.replaceAll(".properties", ""))
                        .ifPresent(s -> messageSource.addBasenames("i18n/" + s));
            }
        };
    }
}
