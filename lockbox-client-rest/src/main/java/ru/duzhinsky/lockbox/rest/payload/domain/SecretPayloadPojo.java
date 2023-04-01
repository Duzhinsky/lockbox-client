package ru.duzhinsky.lockbox.rest.payload.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lockbox.domain.secret.SecretPayload;
import lockbox.domain.secret.SecretPayloadEntry;

public class SecretPayloadPojo implements SecretPayload {

    private final String versionId;

    private final List<SecretPayloadEntryPojo> payload;

    @JsonCreator
    public SecretPayloadPojo(
        @JsonProperty("versionId") String versionId,
        @JsonProperty("entries") List<SecretPayloadEntryPojo> payload
    ) {
        this.versionId = versionId;
        this.payload = payload;
    }

    @Override
    public String getVersionId() {
        return versionId;
    }

    @Override
    public List<? extends SecretPayloadEntry> getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "SecretPayloadPojo{" +
            "versionId='" + versionId + '\'' +
            ", entries=" + payload +
            '}';
    }
}
