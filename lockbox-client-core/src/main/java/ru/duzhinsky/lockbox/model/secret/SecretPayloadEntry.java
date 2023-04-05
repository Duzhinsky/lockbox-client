package ru.duzhinsky.lockbox.model.secret;

import lombok.Value;

@Value
public class SecretPayloadEntry {

    String key;

    PayloadEntryType type;

    String value;
}
