package io.sh4.nasaapihandler.listeners;

import io.sh4.nasaapihandler.models.Weather;
import io.sh4.nasaapihandler.services.ServicesFasade;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherListener {

    private Weather weather;
    private ServicesFasade servicesFasade;

    @Autowired
    public WeatherListener(ServicesFasade servicesFasade) {
        this.servicesFasade = servicesFasade;
    }

    public void update() {
        weather = servicesFasade.getCurrentWeather();
    }

    public Weather getCurrentWeather() {
        return weather;
    }
}
