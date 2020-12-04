package me.demo.reactive.member.exception;

import me.demo.reactive.domainglobal.exception.BaseDomainException;
import me.demo.reactive.member.exception.code.MemberErrorCode;

public class InvalidEmailException extends BaseDomainException {
    public InvalidEmailException() {
        super(MemberErrorCode.INVALID_EMAIL.getCode());
    }
}
