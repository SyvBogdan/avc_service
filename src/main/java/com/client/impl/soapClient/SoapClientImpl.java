package com.client.impl.soapClient;

import com.client.impl.AbstractClient;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.destination.DestinationProvider;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;

public class SoapClientImpl extends AbstractClient implements SoapClient {

    private WebServiceTemplate webServiceTemplate;

    public SoapClientImpl(DestinationProvider destinationProvider, WebServiceTemplate webServiceTemplate) {
        super(destinationProvider);
        this.webServiceTemplate = webServiceTemplate;
    }

    @Override
    public <T> T sendAndReceive(String servicePath, WebServiceMessageCallback requestCallback, WebServiceMessageExtractor<T> responseExtractor) {
        return webServiceTemplate.sendAndReceive(getDestination()+servicePath, requestCallback, responseExtractor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T> T marshalSendAndReceive(String servicePath, R requestPayload) throws XmlMappingException {
        return (T)webServiceTemplate.marshalSendAndReceive(getDestination()+servicePath,requestPayload);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T> T marshalSendAndReceive(String servicePath, R requestPayload, WebServiceMessageCallback requestCallback) throws XmlMappingException {
        return (T)webServiceTemplate.marshalSendAndReceive(getDestination()+servicePath,requestPayload ,requestCallback);
    }

    @Override
    public <T> T sendSourceAndReceive(String servicePath, Source requestPayload, WebServiceMessageCallback requestCallback, SourceExtractor<T> responseExtractor) {
        return webServiceTemplate.sendSourceAndReceive(servicePath,requestPayload,requestCallback,responseExtractor);
    }
}
