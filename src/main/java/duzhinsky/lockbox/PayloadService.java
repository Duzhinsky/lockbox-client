package duzhinsky.lockbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import duzhinsky.lockbox.model.SecretPayload;
import java.time.Duration;
import yandex.cloud.api.lockbox.v1.PayloadServiceGrpc;
import yandex.cloud.api.lockbox.v1.PayloadServiceGrpc.PayloadServiceBlockingStub;
import yandex.cloud.api.lockbox.v1.PayloadServiceOuterClass.GetPayloadRequest;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

public class PayloadService {

    private final PayloadServiceBlockingStub payloadServiceBlockingStub;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PayloadService(CredentialProvider credentialProvider, Duration timeout) {
        payloadServiceBlockingStub = ServiceFactory.builder()
            .credentialProvider(credentialProvider)
            .requestTimeout(timeout)
            .build()
            .create(PayloadServiceBlockingStub.class, PayloadServiceGrpc::newBlockingStub);
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

    public String getSecretText(String secretId, String versionId, String key) {
        return getSecret(secretId, versionId).getTextByKey(key).orElse("");
    }

    public String getSecretText(String secretId, String key) {
        return getSecret(secretId).getTextByKey(key).orElse("");
    }
}
