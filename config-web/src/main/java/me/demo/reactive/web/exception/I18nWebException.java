package me.demo.reactive.web.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ToString
public class I18nWebException extends ResponseStatusException {
    private final Object[] i18nErrorMessageArgs;

    public I18nWebException(
            final HttpStatus status,
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs,
            final Throwable cause
    ) {
        super(status, i18nErrorCode, cause);
        this.i18nErrorMessageArgs = i18nErrorMessageArgs;
    }

    public I18nWebException(
            final HttpStatus status,
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs
    ) {
        this(status, i18nErrorCode, i18nErrorMessageArgs, null);
    }

    public I18nWebException(
            final HttpStatus status,
            final String i18nErrorCode,
            final Throwable throwable
    ) {
        this(status, i18nErrorCode, null, throwable);
    }

    public String getI18nErrorCode() {
        return getReason();
    }

    public HttpStatus getHttpStatus() {
        return getStatus();
    }

    public Object[] getI18nErrorMessageArgs() {
        return i18nErrorMessageArgs;
    }
}
