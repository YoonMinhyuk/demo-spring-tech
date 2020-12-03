package me.demo.reactive.globalexception;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BaseErrorAttributes extends DefaultErrorAttributes {
    private final MessageSource messageSource;

    @Override
    public Map<String, Object> getErrorAttributes(final ServerRequest request, final ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        final Locale locale = request.queryParam("lang")
                                     .map(Locale::forLanguageTag)
                                     .orElse(Locale.KOREA);

        final Throwable error = getError(request);

        if (error instanceof BaseException) {
            final BaseException baseException = (BaseException) error;
            errorAttributes.replace("status", baseException.getHttpStatus());
            errorAttributes.replace("message", getI18nErrorMessage(baseException, locale));
        }

        return errorAttributes;
    }

    private String getI18nErrorMessage(final BaseException e, final Locale locale) {
        return messageSource.getMessage(e.getI18nErrorCode(), e.getI18nErrorMessageArgs(), locale);
    }
}
