package me.demo.reactive.domainglobal.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseDomainException extends RuntimeException {
    private final String errorCode;
    private final Object[] i18nErrorMessageArgs;

    public BaseDomainException(
            final String errorCode,
            final Object[] i18nErrorMessageArgs,
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
        this.errorCode = errorCode;
        this.i18nErrorMessageArgs = i18nErrorMessageArgs;
    }

    public BaseDomainException(final String errorCode, final Object[] i18nErrorMessageArgs) {
        this(errorCode, i18nErrorMessageArgs, null, null);
    }

    public BaseDomainException(final String errorCode) {
        this(errorCode, null);
    }
}