package me.demo.reactive.webmember.adapter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MemberRouterTest {
    @Value("${hello.world:nothing}")
    private String hello;

    @Test
    void test() {
        System.out.println(hello);
    }
}