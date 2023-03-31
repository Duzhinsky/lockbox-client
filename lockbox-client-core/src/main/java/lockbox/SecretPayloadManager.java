package lockbox;

import lockbox.model.SecretPayload;

public interface SecretPayloadManager {

    SecretPayload getPayload(String secretId);
}
