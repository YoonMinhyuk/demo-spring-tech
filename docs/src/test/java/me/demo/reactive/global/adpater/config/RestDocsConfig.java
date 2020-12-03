package me.demo.reactive.global.adpater.config;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsWebTestClientConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.restdocs.operation.preprocess.UriModifyingOperationPreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@TestConfiguration
public class RestDocsConfig {
    @Bean
    @Profile({"!dev", "!prd"})
    public RestDocsWebTestClientConfigurationCustomizer defaultRestDocsWebTestClientConfigurationCustomizer() {
        return generateCustomizer("http", "127.0.0.1", 8080, false);
    }

    @Bean
    @Profile({"dev", "prd"})
    public RestDocsWebTestClientConfigurationCustomizer devRestDocsWebTestClientConfigurationCustomizer() {
        return generateCustomizer("https", "me.demo.reactive", 80, true);
    }

    private RestDocsWebTestClientConfigurationCustomizer generateCustomizer(
            final String schema,
            final String host,
            final int port,
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
