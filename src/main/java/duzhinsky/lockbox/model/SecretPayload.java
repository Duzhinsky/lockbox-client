package duzhinsky.lockbox.model;

import duzhinsky.lockbox.model.SecretPayloadEntry.TextOrBinary;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Value;
import yandex.cloud.api.lockbox.v1.PayloadOuterClass;

@Value
public class SecretPayload {

    String versionId;

    List<SecretPayloadEntry> entries;

    public Optional<SecretPayloadEntry> findEntryByKey(String key) {
        return entries.stream()
            .filter(entry -> entry.getKey().equals(key))
            .findFirst();
    }

    public Optional<String> getTextByKey(String key) {
        return findEntryByKey(key)
            .map(SecretPayloadEntry::getValue)
            .flatMap(TextOrBinary::getText);
    }

    public static SecretPayload fromGrpc(PayloadOuterClass.Payload grpcPayload) {
        return new SecretPayload(
            grpcPayload.getVersionId(),
            grpcPayload.getEntriesList().stream()
                .map(SecretPayloadEntry::fromGrpc)
                .collect(Collectors.toList())
        );
    }
}
