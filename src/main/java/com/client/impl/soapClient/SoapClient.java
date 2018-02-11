package com.client.impl.soapClient;

import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;

import javax.xml.transform.Source;

public interface SoapClient {

    <T> T sendAndReceive(String servicePath, WebServiceMessageCallback requestCallback, WebServiceMessageExtractor<T> responseExtractor);

    <R, T> T marshalSendAndReceive(String servicePath, R requestPayload) throws XmlMappingException;

    <R, T> T marshalSendAndReceive(String servicePath, R requestPayload, WebServiceMessageCallback requestCallback) throws XmlMappingException;

    <T> T sendSourceAndReceive(String servicePath, Source requestPayload, WebServiceMessageCallback requestCallback, SourceExtractor<T> responseExtractor);
}
