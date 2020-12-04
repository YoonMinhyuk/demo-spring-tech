package me.demo.reactive.member.constant;

import lombok.Getter;
import lombok.ToString;
import me.demo.reactive.domain.error.I18nErrorCodeProvider;

@Getter
@ToString
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
