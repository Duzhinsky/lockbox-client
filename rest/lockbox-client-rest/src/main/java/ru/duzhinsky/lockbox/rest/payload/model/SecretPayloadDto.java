package ru.duzhinsky.lockbox.rest.payload.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.duzhinsky.lockbox.model.secret.SecretPayload;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecretPayloadDto {

    @JsonProperty("versionId")
    private String versionId;

    @JsonProperty("entries")
    private List<SecretPayloadEntryDto> entries;

    public SecretPayload toBusinessModel() {
        return new SecretPayload(
            versionId,
            entries.stream()
                .map(SecretPayloadEntryDto::toBusinessModel)
                .collect(Collectors.toList())
        );
    }
}
