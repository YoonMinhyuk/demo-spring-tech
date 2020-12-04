package me.demo.reactive.web.error;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.web.exception.I18nWebException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class I18nWebErrorHandler {
    public Mono<I18nWebException> error(
            final HttpStatus httpStatus,
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs,
            final Throwable throwable
    ) {
        return Mono.error(new I18nWebException(httpStatus, i18nErrorCode, i18nErrorMessageArgs, throwable));
    }

    public Mono<I18nWebException> error(
            final HttpStatus httpStatus,
            final String i18nErrorCode,
            final Throwable throwable
    ) {
        return error(httpStatus, i18nErrorCode, null, throwable);
    }

    public Mono<I18nWebException> badRequest(
            final String i18nErrorCode,
            final Object[] i18nErrorMessageArgs,
            final Throwable throwable
    ) {
        return error(HttpStatus.BAD_REQUEST, i18nErrorCode, i18nErrorMessageArgs, throwable);
    }

    public Mono<I18nWebException> badRequest(
            final String i18nErrorCode,
            final Throwable throwable
    ) {
        return badRequest(i18nErrorCode, null, throwable);
    }
}
