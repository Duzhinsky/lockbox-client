package duzhinsky.lockbox;

import duzhinsky.lockbox.model.SecretPayload;
import duzhinsky.lockbox.model.SecretPayloadEntry;
import duzhinsky.lockbox.model.SecretPayloadEntry.TextOrBinary;
import java.time.Duration;
import yandex.cloud.api.lockbox.v1.PayloadServiceGrpc;
import yandex.cloud.api.lockbox.v1.PayloadServiceGrpc.PayloadServiceBlockingStub;
import yandex.cloud.api.lockbox.v1.PayloadServiceOuterClass.GetPayloadRequest;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

public class PayloadService {

    private final PayloadServiceBlockingStub payloadServiceBlockingStub;

    public PayloadService(CredentialProvider credentialProvider, Duration timeout) {
        payloadServiceBlockingStub = ServiceFactory.builder()
            .credentialProvider(credentialProvider)
            .requestTimeout(timeout)
            .build()
            .create(PayloadServiceBlockingStub.class, PayloadServiceGrpc::newBlockingStub);
    }

    public String getSecretText(SecretPayload secretPayload, String key) {
        return secretPayload.findEntryByKey(key)
            .map(SecretPayloadEntry::getValue)
            .flatMap(TextOrBinary::getText)
            .orElse("");
    }

    public String getSecretText(String secretId, String versionId, String key) {
        return getSecretText(getSecret(secretId, versionId), key);
    }

    public String getSecretText(String secretId, String key) {
        return getSecretText(getSecret(secretId), key);
    }

    public SecretPayload getSecret(String secretId, String versionId) {
        return getSecret(GetPayloadRequest.newBuilder()
            .setSecretId(secretId).setVersionId(versionId).build());
    }

    public SecretPayload getSecret(String secretId) {
        return getSecret(GetPayloadRequest.newBuilder().setSecretId(secretId).build());
    }

    public SecretPayload getSecret(GetPayloadRequest getPayloadRequest) {
        return SecretPayload.fromGrpc(payloadServiceBlockingStub.get(getPayloadRequest));
    }
}
