package me.demo.reactive.ping.adapter.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PingRouter {
    @Bean
    public RouterFunction<ServerResponse> pingRoute() {
        Map<String, Boolean> body = new HashMap<>();
        body.put("isAlive", true);
        return RouterFunctions.route()
                              .GET("/ping", request -> ServerResponse.ok().bodyValue(body))
                              .build();
    }
}
