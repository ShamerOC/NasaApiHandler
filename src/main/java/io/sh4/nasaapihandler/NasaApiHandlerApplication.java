package io.sh4.nasaapihandler;

import io.sh4.nasaapihandler.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class NasaApiHandlerApplication {

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build());
    }

    public static void main(String[] args) {
        SpringApplication.run(NasaApiHandlerApplication.class, args);
    }

}
