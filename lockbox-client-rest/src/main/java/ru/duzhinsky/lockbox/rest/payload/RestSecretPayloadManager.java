package ru.duzhinsky.lockbox.rest.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import lockbox.SecretPayloadManager;
import org.apache.http.util.EntityUtils;
import ru.duzhinsky.lockbox.rest.payload.domain.SecretPayloadPojo;

public class RestSecretPayloadManager implements SecretPayloadManager {

    private final RestPayloadHttpClient proxy = new RestPayloadHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SecretPayloadPojo getPayload(String iamToken, String secretId, String versionId) {
        StringBuilder responseBody = new StringBuilder();
        proxy.getPayload(iamToken, secretId, Optional.ofNullable(versionId), response -> {
            switch (response.getStatusLine().getStatusCode()) {
                case 403:
                    // todo throw auth ex
                    break;
                case 404:
                    break;
                case 200:
                    try {
                        responseBody.append(EntityUtils.toString(response.getEntity()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
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
