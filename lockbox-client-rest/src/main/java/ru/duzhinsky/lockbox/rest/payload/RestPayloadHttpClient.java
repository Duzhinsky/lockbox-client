package ru.duzhinsky.lockbox.rest.payload;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestPayloadHttpClient {

    private final PayloadEndpoint payloadEndpoint = new PayloadEndpoint();

    public void getPayload(
        String iamToken,
        String secretId,
        Optional<String> versionId,
        Consumer<CloseableHttpResponse> onResponse
    ) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response =
                httpclient.execute(payloadEndpoint.getRequest(iamToken, secretId, versionId))) {
                onResponse.accept(response);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
