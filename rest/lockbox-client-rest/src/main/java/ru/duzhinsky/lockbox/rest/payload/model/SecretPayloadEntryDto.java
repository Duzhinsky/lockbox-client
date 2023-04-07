package ru.duzhinsky.lockbox.rest.payload.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.duzhinsky.lockbox.model.secret.PayloadEntryType;
import ru.duzhinsky.lockbox.model.secret.SecretPayloadEntry;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecretPayloadEntryDto {

    @JsonProperty("key")
    private String key;

    @JsonProperty("textValue")
    private String textValue;

    @JsonProperty("binaryValue")
    private String binaryValue;

    public SecretPayloadEntry toBusinessModel() {
        return new SecretPayloadEntry(
            key,
            textValue != null ? PayloadEntryType.TEXT : PayloadEntryType.BINARY,
            textValue != null ? textValue : binaryValue
        );
    }
}
