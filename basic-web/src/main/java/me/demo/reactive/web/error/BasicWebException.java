package me.demo.reactive.web.error;

import lombok.ToString;
import me.demo.reactive.i18n.I18nMessageHint;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ToString
public class BasicWebException extends ResponseStatusException {
    private final I18nMessageHint i18nMessageHint;

    public BasicWebException(
            final HttpStatus status,
            final I18nMessageHint i18nMessageHint
    ) {
        super(status);
        this.i18nMessageHint = i18nMessageHint;
    }

    I18nMessageHint getI18nMessageHint() {
        return i18nMessageHint;
    }
}
