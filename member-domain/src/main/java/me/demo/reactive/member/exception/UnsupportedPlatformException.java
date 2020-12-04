package me.demo.reactive.member.exception;

import me.demo.reactive.domain.exception.BaseDomainException;
import me.demo.reactive.member.exception.code.MemberErrorCode;

public class UnsupportedPlatformException extends BaseDomainException {
    public UnsupportedPlatformException() {
        super(MemberErrorCode.UNSUPPORTED_PLATFORM.getCode());
    }
}
