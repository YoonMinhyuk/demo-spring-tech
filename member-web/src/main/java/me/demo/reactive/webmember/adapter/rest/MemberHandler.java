package me.demo.reactive.webmember.adapter.rest;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.domain.error.I18nDomainException;
import me.demo.reactive.domain.error.I18nErrorMessageResolver;
import me.demo.reactive.member.application.command.MemberCommandAppService;
import me.demo.reactive.member.exception.DuplicateMemberException;
import me.demo.reactive.webmember.spec.request.SignUpRequestSpec;
import me.demo.reactive.webmember.spec.response.SignUpResponseSpec;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MemberHandler {
    private final MemberCommandAppService memberCommandAppService;
    private final I18nErrorMessageResolver i18nErrorMessageResolver;

    // TODO: 2020/12/04 locale 부터 처리
    public Mono<ServerResponse> signUp(final ServerRequest request) {
        return request.bodyToMono(SignUpRequestSpec.class)
                      .map(SignUpRequestSpec::toMemberRecord)
                      .map(memberRecord -> SignUpResponseSpec.of(memberCommandAppService.signUp(memberRecord)))
                      .onErrorMap(
                              I18nDomainException.class,
                              throwable -> i18nErrorMessageResolver.resolve(throwable.getI18nErrorCodeProvider(), )
                      )
                      .flatMap(signUpResponseSpec ->
                              ServerResponse.created(signUpResponseSpec.getSelfLinkUri()).bodyValue(signUpResponseSpec)
                      );
    }
}
