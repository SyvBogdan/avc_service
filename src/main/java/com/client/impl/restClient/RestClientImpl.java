package com.client.impl.restClient;

import com.client.impl.AbstractClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.support.destination.DestinationProvider;

import java.util.Map;

public class RestClientImpl extends AbstractClient implements RestClient {

    private RestTemplate restTemplate;

    public RestClientImpl(DestinationProvider destinationProvider, RestTemplate restTemplate) {
        super(destinationProvider);
        this.restTemplate = restTemplate;
    }

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public <D> ResponseEntity<D> get(String servicePath, Class<D> responseClass, Object... uriVariables) {
        return getRestTemplate().getForEntity(getDestination() + servicePath, responseClass, uriVariables);
    }

    @Override
    public <D, R> ResponseEntity<D> post(String servicePath, HttpEntity<R> request, Class<D> responseClass, Object... uriVariables) {
        return getRestTemplate().postForEntity(getDestination() + servicePath, request, responseClass, uriVariables);
    }

    public <D, R> ResponseEntity<D> put(String servicePath, HttpEntity<R> request, Class<D> responseClass, Object... uriVariables) {
        return getRestTemplate().exchange(getDestination() + servicePath, HttpMethod.PUT, request, responseClass, uriVariables);
    }

    @Override
    public void delete(String servicePath, Map<String, String> uriVariables) {
        getRestTemplate().delete(servicePath, uriVariables);
    }
}
