package ru.duzhinsky.lockbox.spring;

import org.springframework.stereotype.Component;

@Component
public interface SecretBean {

    String value();

    default String version() {
        return "";
    }

    default String key() {
        return "";
    };
}