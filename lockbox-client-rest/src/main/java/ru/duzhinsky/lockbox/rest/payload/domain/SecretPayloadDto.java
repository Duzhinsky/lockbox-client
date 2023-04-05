package ru.duzhinsky.lockbox.rest.payload.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;
import ru.duzhinsky.lockbox.model.secret.SecretPayload;

@Value
public class SecretPayloadDto {

    @JsonProperty("versionId")
    String versionId;

    @JsonProperty("entries")
    List<SecretPayloadEntryDto> entries;

    public SecretPayload toBusinessModel() {
        return new SecretPayload(
            versionId,
            entries.stream()
                .map(SecretPayloadEntryDto::toBusinessModel)
                .collect(Collectors.toList())
        );
    }
}
