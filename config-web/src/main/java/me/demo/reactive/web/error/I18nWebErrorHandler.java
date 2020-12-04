package me.demo.reactive.web.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class I18nWebErrorHandler {
    public I18nWebException error(
            final HttpStatus httpStatus,
            final I18nErrorCodeProvider i18nErrorCodeProvider,
            final Throwable throwable
    ) {
        return new I18nWebException(
                httpStatus,
                i18nErrorCodeProvider.errorCode(),
                i18nErrorCodeProvider.errorMessageArgs(),
                throwable
        );
    }

    public I18nWebException error(
            final HttpStatus httpStatus,
            final I18nErrorCodeProvider i18nErrorCodeProvider
    ) {
        return error(httpStatus, i18nErrorCodeProvider, null);
    }

    public I18nWebException badRequest(
            final I18nErrorCodeProvider i18nErrorCodeProvider,
            final Throwable throwable
    ) {
        return error(HttpStatus.BAD_REQUEST, i18nErrorCodeProvider, throwable);
    }
}
