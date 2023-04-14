package duzhinsky.lockbox.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;
import yandex.cloud.api.lockbox.v1.PayloadOuterClass;

@Value
public class SecretPayload {

    String versionId;

    List<SecretPayloadEntry> entries;

    public static SecretPayload fromGrpc(PayloadOuterClass.Payload grpcPayload) {
        return new SecretPayload(
            grpcPayload.getVersionId(),
            grpcPayload.getEntriesList().stream()
                .map(SecretPayloadEntry::fromGrpc)
                .collect(Collectors.toList())
        );
    }
}
