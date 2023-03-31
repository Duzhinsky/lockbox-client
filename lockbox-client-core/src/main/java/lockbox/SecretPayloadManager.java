package lockbox;

import lockbox.domain.secret.SecretPayload;

public interface SecretPayloadManager {

    SecretPayload getPayload(String secretId);

    SecretPayload getPayload(String secretId, String versionId);
}
