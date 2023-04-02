package lockbox;

import lockbox.domain.secret.SecretPayload;
import lockbox.exception.SecretNotFoundException;
import lockbox.exception.UnauthorizedException;

public interface SecretPayloadManager {

    /**
     * @throws IllegalArgumentException if secretId is not valid
     * @throws UnauthorizedException if api hasn't authorized the iamToken
     * @throws SecretNotFoundException if the secret is not found
     */
    SecretPayload getPayload(String iamToken, String secretId);

    /**
     * @throws IllegalArgumentException if secretId is not valid
     * @throws UnauthorizedException if api hasn't authorized the iamToken
     * @throws SecretNotFoundException if the secret or version is not found
     */
    SecretPayload getPayload(String iamToken, String secretId, String versionId);
}
