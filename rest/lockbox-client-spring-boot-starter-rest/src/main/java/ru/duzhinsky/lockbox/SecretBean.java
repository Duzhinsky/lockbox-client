package ru.duzhinsky.lockbox;

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