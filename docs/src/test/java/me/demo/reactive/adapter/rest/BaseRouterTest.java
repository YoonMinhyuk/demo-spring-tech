package me.demo.reactive.adapter.rest;

import me.demo.reactive.global.adpater.config.RestDocsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
@Import(RestDocsConfig.class)
public class BaseRouterTest {
    @Autowired
    protected WebTestClient webTestClient;

    @Test
    void testWebTestClientIsNotNull() {
        assertThat(webTestClient).isNotNull();
    }
}
