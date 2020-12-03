package me.demo.reactive.member.exception;

import me.demo.reactive.domainglobal.exception.BaseDomainException;
import me.demo.reactive.member.exception.i18n.MemberI18nErrorCode;

public class UnsupportedPlatformException extends BaseDomainException {
    public UnsupportedPlatformException() {
        super(MemberI18nErrorCode.UNSUPPORTED_PLATFORM.getCode());
    }
}
