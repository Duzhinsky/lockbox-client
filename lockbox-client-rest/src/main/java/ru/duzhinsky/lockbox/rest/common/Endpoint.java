package ru.duzhinsky.lockbox.rest.common;

import java.util.Map;
import kong.unirest.Unirest;

public class Endpoint {

    private final String method;

    private final String basicUri;

    public Endpoint(String method, String basicUri) {
        this.method = method;
        this.basicUri = basicUri;
    }

    public <T> T request(
        Map<String, String> headers,
        Map<String, Object> routeParams,
        Map<String, Object> params,
        String body,
        Class<T> clazz
    ) {
        return Unirest.request(method, basicUri)
//            .body(body)
            .headers(headers)
            .routeParam(routeParams)
            .queryString(params)
            .asObject(clazz)
            .getBody();
    }
}
