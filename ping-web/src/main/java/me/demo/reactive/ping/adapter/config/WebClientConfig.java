package me.demo.reactive.ping.adapter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final WebClient.Builder webClientBuilder;

    @Bean
    @LoadBalanced
    public WebClient webClient() {
        return webClientBuilder.build();
    }
}
