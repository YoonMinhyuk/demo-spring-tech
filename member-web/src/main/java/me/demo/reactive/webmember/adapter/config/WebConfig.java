package me.demo.reactive.webmember.adapter.config;

import me.demo.reactive.webmember.adapter.config.formatter.PlatformConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@ConditionalOnMissingBean(WebFluxConfigurer.class)
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new PlatformConverter());
    }
}
