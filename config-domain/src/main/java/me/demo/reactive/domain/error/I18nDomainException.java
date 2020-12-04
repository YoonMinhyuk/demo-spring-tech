package me.demo.reactive.domain.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class I18nDomainException extends RuntimeException {
    private final I18nErrorCodeProvider i18nErrorCodeProvider;

    public I18nDomainException(
            final I18nErrorCodeProvider i18nErrorCodeProvider,
            final String messageInLog,
            final Throwable cause
    ) {
        super(messageInLog, cause);
        this.i18nErrorCodeProvider = i18nErrorCodeProvider;
    }

    public I18nDomainException(
            final I18nErrorCodeProvider i18nErrorCodeProvider,
            final Throwable cause
    ) {
        this(i18nErrorCodeProvider, null, cause);
    }

    public I18nDomainException(
            final I18nErrorCodeProvider i18nErrorCodeProvider,
            final String messageInLog
    ) {
        this(i18nErrorCodeProvider, messageInLog, null);
    }

    public I18nDomainException(
            final I18nErrorCodeProvider i18nErrorCodeProvider
    ) {
        this(i18nErrorCodeProvider, null, null);
    }

    public String errorCode() {
        return i18nErrorCodeProvider.errorCode();
    }

    public Object[] errorMessageArgs() {
        return i18nErrorCodeProvider.errorMessageArgs();
    }
}