package me.demo.reactive.globalexception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String i18nErrorCode;
    private final Object[] i18nErrorMessageArgs;

    public BaseException(
            final HttpStatus httpStatus,
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs,
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.i18nErrorCode = i18nErrorCode;
        this.i18nErrorMessageArgs = i18nErrorMessageArgs;
    }

    public BaseException(
            final HttpStatus httpStatus,
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs
    ) {
        this(httpStatus, i18nErrorCode, i18nErrorMessageArgs, null, null);
    }

    public BaseException(final HttpStatus httpStatus, final String i18nErrorCode) {
        this(httpStatus, i18nErrorCode, null);
    }
}
