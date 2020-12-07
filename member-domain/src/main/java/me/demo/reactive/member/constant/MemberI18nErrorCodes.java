package me.demo.reactive.member.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MemberI18nErrorCodes {
    INVALID_EMAIL("member.email.invalid"),
    UNSUPPORTED_PLATFORM("member.platform.unsupported"),
    DUPLICATE_MEMBER("member.duplicate");

    private final String code;
    private final Object[] errorMessageArgs;

    MemberI18nErrorCodes(final String code, final Object[] errorMessageArgs) {
        this.code = code;
        this.errorMessageArgs = errorMessageArgs;
    }

    MemberI18nErrorCodes(final String code) {
        this(code, null);
    }
}
