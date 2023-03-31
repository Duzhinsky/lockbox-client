package ru.duzhinsky.lockbox.rest.payload;

import java.util.Map;
import lockbox.SecretPayloadManager;
import ru.duzhinsky.lockbox.rest.common.Endpoint;
import ru.duzhinsky.lockbox.rest.domain.SecretPayloadPojo;

public class RestSecretPayloadManager implements SecretPayloadManager {

    private static final Endpoint getPayloadEndpoint = new Endpoint(
        "GET",
        "https://payload.lockbox.api.cloud.yandex.net/lockbox/v1/secrets/{secretId}/payload"
    );

    public SecretPayloadPojo getPayload(String iamToken, String secretId, String versionId) {
        Map<String, Object> params = versionId.isBlank()
            ? Map.of() : Map.of("versionId", versionId);
        return getPayloadEndpoint.request(
            Map.of("Authorization", "Bearer " + iamToken),
            Map.of("secretId", secretId),
            params,
            "",
            SecretPayloadPojo.class
        );
    }

    public SecretPayloadPojo getPayload(String iamToken, String secretId) {
        return getPayload(iamToken, secretId, "");
    }
}
