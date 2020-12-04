package me.demo.reactive.web.error;

public interface I18nErrorCodeProvider {
    String errorCode();

    Object[] errorMessageArgs();
}