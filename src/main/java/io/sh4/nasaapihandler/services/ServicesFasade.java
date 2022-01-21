package io.sh4.nasaapihandler.services;

import io.sh4.nasaapihandler.models.Picture;
import io.sh4.nasaapihandler.models.PictureList;
import io.sh4.nasaapihandler.models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesFasade {

    private final PictureService pictureService;
    private final WeatherService weatherService;

    @Autowired
    ServicesFasade(PictureService pictureService, WeatherService weatherService) {
        this.pictureService = pictureService;
        this.weatherService = weatherService;
    }

    public PictureList getPictureOfTheDay() {
        return pictureService.getLast7PicturesOfTheDay();
    }

    public Picture getCoordinatesPicture(String lat, String lon) {
        return pictureService.getCoordinatesPicture(lat, lon);
    }

    public Weather getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }

}
