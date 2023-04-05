package ru.duzhinsky.lockbox.rest.payload.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.duzhinsky.lockbox.model.secret.PayloadEntryType;
import ru.duzhinsky.lockbox.model.secret.SecretPayloadEntry;

@Value
public class SecretPayloadEntryDto {

    @JsonProperty("key")
    String key;

    @JsonProperty("textValue")
    String textValue;

    @JsonProperty("binaryValue")
    String binaryValue;

    public SecretPayloadEntry toBusinessModel() {
        return new SecretPayloadEntry(
            key,
            textValue != null ? PayloadEntryType.TEXT : PayloadEntryType.BINARY,
            textValue != null ? textValue : binaryValue
        );
    }
}
