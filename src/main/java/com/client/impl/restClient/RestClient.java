package com.client.impl.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public interface RestClient {

    RestTemplate getRestTemplate();

    <D> ResponseEntity<D> get(String servicePath, Class<D> responseClass, Object... uriVariables);

    <D, R> ResponseEntity<D> post(String servicePath, HttpEntity<R> request, Class<D> responseClass, Object... uriVariables);

    <D, R> ResponseEntity<D> put(String servicePath, HttpEntity<R> request, Class<D> responseClass, Object... uriVariables);

    void delete(String serviceUrl, Map<String, String> uriVariables);

}
