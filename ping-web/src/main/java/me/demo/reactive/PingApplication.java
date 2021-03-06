package me.demo.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PingApplication {
    public static void main(String[] args) {
        SpringApplication.run(PingApplication.class, args);
    }
}
