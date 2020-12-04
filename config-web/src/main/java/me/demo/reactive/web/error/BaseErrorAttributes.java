package me.demo.reactive.web.error;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.web.exception.I18nWebException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BaseErrorAttributes extends DefaultErrorAttributes {
    private final MessageSource messageSource;
    private final LocaleContextResolver localeContextResolver;

    @Override
    public Map<String, Object> getErrorAttributes(final ServerRequest request, final ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        final Throwable error = getError(request);

        if (error instanceof I18nWebException) {
            final I18nWebException i18nWebException = (I18nWebException) error;
            errorAttributes.replace("status", i18nWebException.getHttpStatus());
            errorAttributes.replace(
                    "message",
                    getI18nErrorMessage(
                            i18nWebException,
                            localeContextResolver.resolveLocaleContext(request.exchange()).getLocale()
                    )
            );
        }

        return errorAttributes;
    }

    private String getI18nErrorMessage(final I18nWebException e, final Locale locale) {
        return messageSource.getMessage(e.getI18nErrorCode(), e.getI18nErrorMessageArgs(), locale);
    }
}
