package config;


import com.client.impl.soapClient.ServiceSoapClient;
import com.client.impl.soapClient.SoapClient;
import com.client.impl.soapClient.SoapClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.destination.DestinationProvider;

@Configuration
@Import(ProviderConfig.class)
public class WebServiceConfig {

    @Bean
    Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        //TODO configuration
        return jaxb2Marshaller;
    }

    @Bean
    WebServiceTemplate getWebServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
        return webServiceTemplate;
    }

    @Bean
    ServiceSoapClient getServiceSoapClient(WebServiceTemplate webServiceTemplate, DestinationProvider destinationProvider) {
        SoapClient soapClient = new SoapClientImpl(destinationProvider, webServiceTemplate);
        return new ServiceSoapClient(soapClient);
    }
}
