package me.demo.reactive.domainglobal.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseDomainException extends RuntimeException {
    private final String errorCode;

    public BaseDomainException(
            final String errorCode,
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseDomainException(final String errorCode) {
        this(errorCode, null, null);
    }
}