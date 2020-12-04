package me.demo.reactive.webmember.adapter.rest;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.member.application.command.MemberCommandAppService;
import me.demo.reactive.member.exception.DuplicateMemberException;
import me.demo.reactive.web.error.I18nErrorCodeProvider;
import me.demo.reactive.web.error.I18nWebErrorHandler;
import me.demo.reactive.webmember.constant.MemberI18nErrorCodes;
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
    private final I18nWebErrorHandler i18nWebErrorHandler;

    public Mono<ServerResponse> signUp(final ServerRequest request) {
        return request.bodyToMono(SignUpRequestSpec.class)
                      .map(SignUpRequestSpec::toMemberRecord)
                      .map(memberRecord -> SignUpResponseSpec.of(memberCommandAppService.signUp(memberRecord)))
                      .onErrorMap(
                              DuplicateMemberException.class,
                              throwable -> i18nWebErrorHandler.badRequest(MemberI18nErrorCodes.DUPLICATE_MEMBER, throwable)
                      )
                      .flatMap(signUpResponseSpec ->
                              ServerResponse.created(signUpResponseSpec.getSelfLinkUri()).bodyValue(signUpResponseSpec)
                      );
    }
}
