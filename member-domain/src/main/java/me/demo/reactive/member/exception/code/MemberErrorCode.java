package me.demo.reactive.member.exception.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MemberErrorCode {
    INVALID_EMAIL("member.email.invalid"),
    UNSUPPORTED_PLATFORM("member.platform.unsupported"),
    DUPLICATE_MEMBER("member.duplicate")
    ;

    private final String code;

    MemberErrorCode(final String code) {
        this.code = code;
    }
}
