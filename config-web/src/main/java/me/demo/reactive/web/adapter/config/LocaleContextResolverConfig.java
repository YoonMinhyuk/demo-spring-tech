package me.demo.reactive.web.adapter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

@Configuration
@ConditionalOnMissingBean(LocaleContextResolver.class)
public class LocaleContextResolverConfig {
    @Bean
    public LocaleContextResolver localeContextResolver() {
        final AcceptHeaderLocaleContextResolver resolver = new AcceptHeaderLocaleContextResolver();
        resolver.setDefaultLocale(Locale.KOREA);
        return resolver;
    }
}
