package io.sh4.nasaapihandler.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sh4.nasaapihandler.adapters.CommonMathsAdapter;
import io.sh4.nasaapihandler.models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient.Builder webClientBuilder;
//    private final String weather = "https://api.nasa.gov/insight_weather/?api_key=vts7aTcMZrspTn7fgl0GVskf5KD0r3scAEioO51p&feedtype=json&ver=1.0";
    private final String weather = "https://mars.nasa.gov/rss/api/?feed=weather&category=msl&feedtype=json";


    @Autowired
    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Weather getCurrentWeather() {
        String str = webClientBuilder.build()
                .get()
                .uri(weather)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        var objMapper = new ObjectMapper();
        Weather result = null;
        try {
//            JsonNode json = objMapper.readTree(str).iterator().next();
            JsonNode json = objMapper.readTree(str).get("soles").iterator().next();
//            JsonNode it = objMapper.readTree(str).iterator().next();
//            System.out.println(json);
            double min = Double.parseDouble(json.get("min_temp").toString().replace("\"", ""));
            double max = Double.parseDouble(json.get("max_temp").toString().replace("\"", ""));
            result = Weather.builder().min(min).max(max).build();
            var commonMathsAdapter = new CommonMathsAdapter(result);
            result.setMean(commonMathsAdapter.evaluate());
            System.out.println(min + " " + max + " " + result.getMean());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
