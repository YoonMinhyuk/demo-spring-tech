package me.demo.reactive.member.exception;

import me.demo.reactive.domain.exception.BaseDomainException;
import me.demo.reactive.member.exception.code.MemberErrorCode;

public class DuplicateMemberException extends BaseDomainException {
    public DuplicateMemberException() {
        super(MemberErrorCode.DUPLICATE_MEMBER.getCode());
    }
}
