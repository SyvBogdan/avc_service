package com.client.impl;


import org.springframework.ws.client.support.destination.DestinationProvider;

import java.net.MalformedURLException;

public abstract class AbstractClient {

    private DestinationProvider destinationProvider;

    public AbstractClient(DestinationProvider destinationProvider) {
        this.destinationProvider = destinationProvider;
    }

    public String getDestination() {
        try {
            return destinationProvider.getDestination().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Wrong Url path");
        }
    }

    protected DestinationProvider getDestinationProvider() {
        return destinationProvider;
    }

}
