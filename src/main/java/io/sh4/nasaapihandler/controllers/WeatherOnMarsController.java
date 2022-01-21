package io.sh4.nasaapihandler.controllers;

import io.sh4.nasaapihandler.adapters.CommonMathsAdapter;
import io.sh4.nasaapihandler.listeners.WeatherListener;
import io.sh4.nasaapihandler.models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherOnMarsController {

    private final WeatherListener weatherListener;

    @Autowired
    WeatherOnMarsController(WeatherListener weatherListener) {
        this.weatherListener = weatherListener;
    }

    @GetMapping(path = "/notify/weather")
    public void notifyWeatherService() {
        weatherListener.update();
    }

    @GetMapping(path = "/weather")
    public Weather getWeather() {
        return weatherListener.getCurrentWeather();
    }
}
