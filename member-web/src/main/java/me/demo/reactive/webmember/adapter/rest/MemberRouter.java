package me.demo.reactive.webmember.adapter.rest;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.webmember.constant.MemberLinks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class MemberRouter {
    private final MemberHandler memberHandler;

    @Bean
    public RouterFunction<ServerResponse> memberRouting() {
        return RouterFunctions.route()
                              .POST(MemberLinks.SIGN_UP.getLink(), memberHandler::signUp)
                              .build();
    }
}
