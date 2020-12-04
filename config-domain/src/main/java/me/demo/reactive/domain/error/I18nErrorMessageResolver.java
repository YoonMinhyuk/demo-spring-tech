package me.demo.reactive.domain.error;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class I18nErrorMessageResolver {
    private final MessageSource messageSource;

    public String resolve(final I18nErrorCodeProvider provider, final Locale locale) {
        return messageSource.getMessage(provider.errorCode(), provider.errorMessageArgs(), locale);
    }
}
