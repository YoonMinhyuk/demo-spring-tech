package me.demo.reactive.domain.error;

public interface I18nErrorCodeProvider {
    String errorCode();

    Object[] errorMessageArgs();
}
