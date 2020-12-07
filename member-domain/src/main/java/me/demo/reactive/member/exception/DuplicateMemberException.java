package me.demo.reactive.member.exception;

import me.demo.reactive.domain.error.I18nDomainException;
import me.demo.reactive.member.constant.MemberI18nErrorCodes;

public class DuplicateMemberException extends I18nDomainException {
    public DuplicateMemberException() {
        super(
                MemberI18nErrorCodes.DUPLICATE_MEMBER.getCode(),
                MemberI18nErrorCodes.DUPLICATE_MEMBER.getErrorMessageArgs()
        );
    }
}
