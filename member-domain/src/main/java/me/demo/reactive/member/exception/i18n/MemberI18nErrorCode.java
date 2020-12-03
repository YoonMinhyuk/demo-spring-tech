package me.demo.reactive.member.exception.i18n;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MemberI18nErrorCode {
    INVALID_EMAIL("member.email.invalid"),
    UNSUPPORTED_PLATFORM("member.platform.unsupported"),
    DUPLICATE_MEMBER("member.duplicate")
    ;

    private final String code;

    MemberI18nErrorCode(final String code) {
        this.code = code;
    }
}
