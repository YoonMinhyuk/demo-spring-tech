package me.demo.reactive.domain.error;

import lombok.Getter;
import lombok.ToString;
import me.demo.reactive.i18n.I18nMessageHint;

@Getter
@ToString
public class I18nDomainException extends RuntimeException implements I18nMessageHint {
    private final String i18nCode;
    private final Object[] i18nMessageArgs;

    public I18nDomainException(
            final String i18nCode,
            final Object[] i18nMessageArgs,
            final String messageInLog,
            final Throwable cause
    ) {
        super(messageInLog, cause);
        this.i18nCode = i18nCode;
        this.i18nMessageArgs = i18nMessageArgs;
    }

    public I18nDomainException(
            final String i18nCode,
            final Object[] i18nMessageArgs,
            final Throwable cause
    ) {
        this(i18nCode, i18nMessageArgs, null, cause);
    }

    public I18nDomainException(
            final String i18nCode,
            final Object[] i18nMessageArgs,
            final String messageInLog
    ) {
        this(i18nCode, i18nMessageArgs, messageInLog, null);
    }

    public I18nDomainException(
            final String i18nCode,
            final Object[] i18nMessageArgs
    ) {
        this(i18nCode, i18nMessageArgs, null, null);
    }

    @Override
    public String getCode() {
        return getI18nCode();
    }

    @Override
    public Object[] getMessageArgs() {
        return getI18nMessageArgs();
    }
}