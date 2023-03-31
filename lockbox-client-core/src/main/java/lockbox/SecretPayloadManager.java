package lockbox;

import lockbox.domain.secret.SecretPayload;

public interface SecretPayloadManager {

    SecretPayload getPayload(String IamToken, String secretId);

    SecretPayload getPayload(String IamToken, String secretId, String versionId);
}
