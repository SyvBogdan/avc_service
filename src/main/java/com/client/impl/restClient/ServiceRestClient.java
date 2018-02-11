package com.client.impl.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

public class ServiceRestClient {

    private RestClient restClient;

    private MediaType mediaType;

    public ServiceRestClient(RestClient restClient, MediaType mediaType) {
        this.restClient = restClient;
        this.mediaType = mediaType;
    }

    private <R> HttpEntity<R> createRequest(R request) {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(mediaType);
        return new HttpEntity<>(request, httpHeader);
    }

    public <D, R> D create(String servicePath, R request, Class<D> responseType, Object... uriParams) {
        return restClient.post(servicePath, createRequest(request), responseType, uriParams).getBody();
    }

    public <D, R> D change(String servicePath, R request, Class<D> responseType, Object... uriParams) {
        return restClient.put(servicePath, createRequest(request), responseType, uriParams).getBody();
    }

    public <D, R> D find(String servicePath, Class<D> responseType, Object... uriParams) {
        return restClient.get(servicePath, responseType, uriParams).getBody();
    }

    public void delete(String servicePath, Map<String, String> mapParams) {
        restClient.delete(servicePath, mapParams);
    }


}
