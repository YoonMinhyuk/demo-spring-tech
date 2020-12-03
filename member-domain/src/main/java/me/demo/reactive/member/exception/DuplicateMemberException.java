package me.demo.reactive.member.exception;

import me.demo.reactive.domainglobal.exception.BaseDomainException;
import me.demo.reactive.member.exception.i18n.MemberI18nErrorCode;

public class DuplicateMemberException extends BaseDomainException {
    public DuplicateMemberException() {
        super(MemberI18nErrorCode.DUPLICATE_MEMBER.getCode());
    }
}
