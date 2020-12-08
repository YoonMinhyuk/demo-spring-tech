package me.demo.reactive.restdocs.adapter.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsWebTestClientConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.restdocs.operation.preprocess.UriModifyingOperationPreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@TestConfiguration
public class RestDocsConfig {
    @Value("${server.port:8080}")
    private int port;

    @Bean
    @Profile({"test", "local"})
    @ConditionalOnMissingBean(RestDocsWebTestClientConfigurationCustomizer.class)
    public RestDocsWebTestClientConfigurationCustomizer defaultRestDocsWebTestClientConfigurationCustomizer() {
        return generateCustomizer("http", "127.0.0.1", false);
    }

    @Bean
    @Profile({"dev"})
    @ConditionalOnMissingBean(RestDocsWebTestClientConfigurationCustomizer.class)
    public RestDocsWebTestClientConfigurationCustomizer devRestDocsWebTestClientConfigurationCustomizer() {
        return generateCustomizer("https", "me.demo.reactive", true);
    }

    private RestDocsWebTestClientConfigurationCustomizer generateCustomizer(
            final String schema,
            final String host,
            final boolean removePort
    ) {
        return configurer -> {
            final UriModifyingOperationPreprocessor uriModifyingOperationPreprocessor =
                    modifyUris().scheme(schema)
                                .host(host)
                                .port(port);
            if (removePort) uriModifyingOperationPreprocessor.removePort();
            configurer.operationPreprocessors().withRequestDefaults(prettyPrint(), uriModifyingOperationPreprocessor);
        };
    }
}