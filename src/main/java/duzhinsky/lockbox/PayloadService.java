package duzhinsky.lockbox;

import duzhinsky.lockbox.model.SecretPayload;
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

    public SecretPayload getSecret(String secretId, String versionId) {
        return SecretPayload.fromGrpc(payloadServiceBlockingStub.get(
            GetPayloadRequest.newBuilder()
                .setSecretId(secretId)
                .setVersionId(versionId)
                .build()
        ));
    }
}
