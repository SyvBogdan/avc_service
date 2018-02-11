package com.client.impl;

import org.springframework.ws.client.support.destination.DestinationProvider;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DestinationProviderImpl implements DestinationProvider {

    final private URI destinationUri;
    private URL url;

    @PostConstruct
    void initMethod() throws MalformedURLException {
        url = getDestination().toURL();
    }

    public DestinationProviderImpl(URI destinationUrl) {
        this.destinationUri = destinationUrl;
    }

    public String getDestinationUrlString() {
        return url.toExternalForm();
    }

    @Override
    public URI getDestination() {
        return destinationUri;
    }
}
