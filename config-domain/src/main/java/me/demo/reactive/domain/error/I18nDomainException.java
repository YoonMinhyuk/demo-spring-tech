package me.demo.reactive.domain.error;

public class I18nDomainException extends RuntimeException {
    private final I18nErrorCodeProvider provider;

    public I18nDomainException(
            final I18nErrorCodeProvider provider,
            final String messageInLog,
            final Throwable cause
    ) {
        super(messageInLog, cause);
        this.provider = provider;
    }

    public I18nDomainException(
            final I18nErrorCodeProvider provider,
            final Throwable cause
    ) {
        this(provider, null, cause);
    }

    public I18nDomainException(
            final I18nErrorCodeProvider provider,
            final String messageInLog
    ) {
        this(provider, messageInLog, null);
    }

    public I18nDomainException(
            final I18nErrorCodeProvider provider
    ) {
        this(provider, null, null);
    }

    public String errorCode() {
        return provider.errorCode();
    }

    public Object[] errorMessageArgs() {
        return provider.errorMessageArgs();
    }
}