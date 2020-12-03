package me.demo.reactive.webmember.adapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class BaseMemberRouterTest {
    @Autowired
    protected WebTestClient webTestClient;

}
