package ru.duzhinsky.lockbox.rest.domain;

import java.util.Optional;
import lockbox.domain.secret.SecretPayloadEntry;

public class SecretPayloadEntryPojo implements SecretPayloadEntry {

    private final String key;

    private final String textValue;

    private final String binaryValue;

    public SecretPayloadEntryPojo(
        String key,
        String textValue,
        String binaryValue
    ) {
        this.key = key;
        this.textValue = textValue;
        this.binaryValue = binaryValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Optional<String> getTextValue() {
        return Optional.ofNullable(textValue);
    }

    @Override
    public Optional<String> getBinaryValue() {
        return Optional.ofNullable(binaryValue);
    }

    @Override
    public String toString() {
        return "SecretPayloadEntryPojo{" +
            "key='" + getKey() + '\'' +
            ", textValue='" + getTextValue() + '\'' +
            ", binaryValue='" + getBinaryValue() + '\'' +
            '}';
    }
}
