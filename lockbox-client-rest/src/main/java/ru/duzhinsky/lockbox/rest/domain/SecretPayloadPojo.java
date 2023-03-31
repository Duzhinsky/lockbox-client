package ru.duzhinsky.lockbox.rest.domain;

import java.util.List;
import lockbox.domain.secret.SecretPayload;
import lockbox.domain.secret.SecretPayloadEntry;

public class SecretPayloadPojo implements SecretPayload {

    private final String versionId;

    private final List<SecretPayloadEntryPojo> entries;

    public SecretPayloadPojo(
        String versionId,
        List<SecretPayloadEntryPojo> entries
    ) {
        this.versionId = versionId;
        this.entries = entries;
    }

    @Override
    public String getVersionId() {
        return versionId;
    }

    @Override
    public List<? extends SecretPayloadEntry> getPayload() {
        return entries;
    }

    @Override
    public String toString() {
        return "SecretPayloadPojo{" +
            "versionId='" + versionId + '\'' +
            ", entries=" + entries +
            '}';
    }
}
