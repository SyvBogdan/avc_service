package com.client.impl.soapClient;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ServiceSoapClient {

    private SoapClient soapClient;

    public ServiceSoapClient(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    public WebServiceMessageCallback createWebServiceMessageCallback(Consumer<WebServiceMessage> callBack) {

        return new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                callBack.accept(message);
                //TODO some logic with message before sending it to SOAP Server
            }
        };
    }

    public <T> WebServiceMessageExtractor<T> createWebServiceMessageExtractor(Function<WebServiceMessage, T> extractor) {

        return new WebServiceMessageExtractor<T>() {
            @Override
            public T extractData(WebServiceMessage message) throws IOException, TransformerException {
                return extractor.apply(message);
                //TODO some logic with message after receiving it from SOAP Server
            }
        };
    }

    public <T, R> T marshalSendAndReceive(String servicePath, R payload) {
        return soapClient.marshalSendAndReceive(servicePath, payload);
    }

    public <T, R> T marshalSendAndReceive(String servicePath, R payload, Consumer<WebServiceMessage> callBack) {
        return soapClient.marshalSendAndReceive(servicePath, payload, createWebServiceMessageCallback(callBack));
    }

    public <T> T SendAndReceive(String servicePath, Consumer<WebServiceMessage> callBack, Function<WebServiceMessage, T> extractor) {
        return soapClient.sendAndReceive(servicePath, createWebServiceMessageCallback(callBack), createWebServiceMessageExtractor(extractor));
    }

}

