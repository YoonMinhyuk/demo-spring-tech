package me.demo.reactive.member.exception;

import me.demo.reactive.domainglobal.exception.BaseDomainException;
import me.demo.reactive.member.exception.i18n.MemberI18nErrorCode;

public class InvalidEmailException extends BaseDomainException {
    public InvalidEmailException() {
        super(MemberI18nErrorCode.INVALID_EMAIL.getCode());
    }
}
