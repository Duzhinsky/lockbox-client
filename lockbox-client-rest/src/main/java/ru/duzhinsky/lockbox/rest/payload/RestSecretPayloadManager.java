package ru.duzhinsky.lockbox.rest.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import org.apache.http.util.EntityUtils;
import ru.duzhinsky.lockbox.SecretPayloadManager;
import ru.duzhinsky.lockbox.exception.LockboxException;
import ru.duzhinsky.lockbox.exception.SecretNotFoundException;
import ru.duzhinsky.lockbox.exception.UnauthorizedException;
import ru.duzhinsky.lockbox.rest.payload.domain.SecretPayloadPojo;

public class RestSecretPayloadManager implements SecretPayloadManager {

    private final RestPayloadHttpClient proxy = new RestPayloadHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SecretPayloadPojo getPayload(String iamToken, String secretId, String versionId) {
        StringBuilder responseBody = new StringBuilder();
        proxy.getPayload(iamToken, secretId, Optional.ofNullable(versionId), response -> {
            switch (response.getStatusLine().getStatusCode()) {
                case 403:
                    throw new UnauthorizedException(iamToken);
                case 404:
                    throw new SecretNotFoundException(secretId, versionId);
                case 200:
                    try {
                        responseBody.append(EntityUtils.toString(response.getEntity()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new LockboxException("Unknown error");
            }
        });

        try {
            return objectMapper.readValue(responseBody.toString(), SecretPayloadPojo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public SecretPayloadPojo getPayload(String iamToken, String secretId) {
        return getPayload(iamToken, secretId, null);
    }
}
