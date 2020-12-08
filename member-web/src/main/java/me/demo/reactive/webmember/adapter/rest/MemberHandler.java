package me.demo.reactive.webmember.adapter.rest;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.domain.error.I18nDomainException;
import me.demo.reactive.member.application.command.MemberCommandAppService;
import me.demo.reactive.web.error.BasicWebException;
import me.demo.reactive.webmember.spec.request.SignUpRequestSpec;
import me.demo.reactive.webmember.spec.response.SignUpResponseSpec;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class MemberHandler {
    private final MemberCommandAppService memberCommandAppService;

    public Mono<ServerResponse> signUp(final ServerRequest request) {
        return request.bodyToMono(SignUpRequestSpec.class)
                      .map(SignUpRequestSpec::toMemberRecord)
                      .flatMap(memberRecord ->
                              Mono.fromCallable(() -> memberCommandAppService.signUp(memberRecord))
                                  .subscribeOn(Schedulers.boundedElastic())
                                  .onErrorMap(
                                          I18nDomainException.class,
                                          e -> new BasicWebException(HttpStatus.BAD_REQUEST, e)
                                  )
                      )
                      .map(SignUpResponseSpec::of)
                      .flatMap(signUpResponseSpec ->
                              ServerResponse.created(signUpResponseSpec.getSelfLinkUri()).bodyValue(signUpResponseSpec)
                      );
    }
}
