package me.demo.reactive.web.error;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.i18n.I18nMessageExtractor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BasicWebErrorAttributes extends DefaultErrorAttributes {
    private final I18nMessageExtractor i18nMessageExtractor;
    private final LocaleContextResolver localeContextResolver;

    @Override
    public Map<String, Object> getErrorAttributes(
            final ServerRequest request,
            final ErrorAttributeOptions options
    ) {
        final Throwable error = getError(request);
        if (error instanceof BasicWebException) {
            final var basicWebException = (BasicWebException) error;
            final var locale = localeContextResolver.resolveLocaleContext(request.exchange()).getLocale();
            final var errorAttributes = super.getErrorAttributes(request, options);
            errorAttributes.replace("status", basicWebException.getStatus());
            errorAttributes.replace("message", i18nMessageExtractor.extract(basicWebException.getI18nMessageHint(), locale));
        }
        return super.getErrorAttributes(request, options);
    }
}
