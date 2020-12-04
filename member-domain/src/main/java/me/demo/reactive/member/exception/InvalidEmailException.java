package me.demo.reactive.member.exception;

import me.demo.reactive.domain.error.I18nDomainException;
import me.demo.reactive.member.constant.MemberI18nErrorCodes;

public class InvalidEmailException extends I18nDomainException {
    public InvalidEmailException() {
        super(MemberI18nErrorCodes.INVALID_EMAIL);
    }
}
