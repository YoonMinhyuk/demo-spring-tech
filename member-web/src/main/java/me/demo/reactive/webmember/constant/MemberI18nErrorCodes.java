package me.demo.reactive.webmember.constant;

import lombok.Getter;
import me.demo.reactive.web.error.I18nErrorCodeProvider;

@Getter
public enum MemberI18nErrorCodes implements I18nErrorCodeProvider {
    INVALID_EMAIL("member.email.invalid"),
    UNSUPPORTED_PLATFORM("member.platform.unsupported"),
    DUPLICATE_MEMBER("member.duplicate")
    ;

    private final String code;
    private final Object[] errorMessageArgs;

    MemberI18nErrorCodes(final String code, final Object[] errorMessageArgs) {
        this.code = code;
        this.errorMessageArgs = errorMessageArgs;
    }

    MemberI18nErrorCodes(final String code) {
        this(code, null);
    }

    @Override
    public final String errorCode() {
        return getCode();
    }

    @Override
    public final Object[] errorMessageArgs() {
        return getErrorMessageArgs();
    }
}
