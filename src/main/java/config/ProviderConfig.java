package config;

import com.client.impl.DestinationProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.ws.client.support.destination.DestinationProvider;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class ProviderConfig {

    //import variable from settings
    private String host;

    @Bean
    DestinationProvider getDestinationProvider() {
        try {
            URI uri = new URI(host);
            return new DestinationProviderImpl(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException("URI exception");
        }
    }
}
