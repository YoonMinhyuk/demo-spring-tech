package me.demo.reactive.member.exception;

import me.demo.reactive.domain.error.I18nDomainException;
import me.demo.reactive.member.constant.MemberI18nErrorCodes;

public class UnsupportedPlatformException extends I18nDomainException {
    public UnsupportedPlatformException() {
        super(
                MemberI18nErrorCodes.UNSUPPORTED_PLATFORM.getCode(),
                MemberI18nErrorCodes.UNSUPPORTED_PLATFORM.getErrorMessageArgs()
        );
    }
}
