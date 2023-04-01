package ru.duzhinsky.lockbox.rest.payload;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class PayloadEndpoint {

    public HttpGet getRequest(String iamToken, String secretId, Optional<String> versionId) {
        HttpGet httpGet = new HttpGet(getUri(secretId, versionId));
        httpGet.addHeader("Authorization", "Bearer " + iamToken);
        return httpGet;
    }

    private URI getUri(String secretId, Optional<String> versionId) {
        var pathBuilder =
            new StringBuilder("https://payload.lockbox.api.cloud.yandex.net/lockbox/v1/secrets/");
        pathBuilder.append(secretId).append("/").append("payload");
        try {
            var uriBuilder = new URIBuilder(pathBuilder.toString());
            versionId.map(vid -> uriBuilder.addParameter("versionId", vid));
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
