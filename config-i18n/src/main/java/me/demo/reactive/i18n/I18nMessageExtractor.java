package me.demo.reactive.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class I18nMessageExtractor {
    private final MessageSource messageSource;

    public String extract(final I18nMessageHint hint, final Locale locale) {
        return messageSource.getMessage(hint.getCode(), hint.getMessageArgs(), locale);
    }

    public String extract(final I18nMessageHint hint) {
        return extract(hint, Locale.getDefault());
    }
}
