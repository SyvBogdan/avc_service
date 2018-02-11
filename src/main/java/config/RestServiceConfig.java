package config;


import com.client.impl.restClient.RestClient;
import com.client.impl.restClient.RestClientImpl;
import com.client.impl.restClient.ServiceRestClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.support.destination.DestinationProvider;


@Configuration
@Import(ProviderConfig.class)
public class RestServiceConfig {

    @Bean
    ServiceRestClient getServiceRestClient(RestTemplate restTemplate, DestinationProvider destinationProvider) {

        //TODO configurations
        HttpClient client = HttpClientBuilder.create()
                .disableCookieManagement()
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);
        restTemplate.setRequestFactory(factory);

        RestClient restClient = new RestClientImpl(destinationProvider, restTemplate);
        return new ServiceRestClient(restClient, MediaType.APPLICATION_XML);

    }
}
