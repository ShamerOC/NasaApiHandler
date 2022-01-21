package io.sh4.nasaapihandler.services;

import io.sh4.nasaapihandler.models.Picture;
import io.sh4.nasaapihandler.models.PictureList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PictureService {

    private final WebClient.Builder webClientBuilder;

    private final String pictureOfTheDay = "https://api.nasa.gov/planetary/apod?api_key=vts7aTcMZrspTn7fgl0GVskf5KD0r3scAEioO51p";
    private final String coordinatesPicture = "https://api.nasa.gov/planetary/earth/assets?api_key=vts7aTcMZrspTn7fgl0GVskf5KD0r3scAEioO51p";

    @Autowired
    public PictureService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public PictureList getLast7PicturesOfTheDay() {
        LocalDate localDate = LocalDate.now().minusDays(7);
        String startDate = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

         Picture[] picture = webClientBuilder.build()
                .get()
                .uri(pictureOfTheDay + "&start_date=" + startDate)
                .retrieve()
                .bodyToMono(Picture[].class).log().block();

        PictureList pictures = new PictureList();

        for (int i = 0; i < picture.length; i++) {
            pictures.add(picture[i]);
        }
        return pictures;
    }

    public Picture getCoordinatesPicture(String lat, String lon) {
        int i = 0;
        Picture picture = null;
        LocalDate localDate = LocalDate.now();
        while (picture == null && i++ < 12) {
            String date = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();
            picture = webClientBuilder.build()
                    .get()
                    .uri(coordinatesPicture + "&lat=" + lat + "&lon=" + lon + "&date=" + date)
                    .retrieve()
                    .bodyToMono(Picture.class)
                    .onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex)).log().block();
            localDate = localDate.minusDays(30);
        }
        if (picture == null) picture = new Picture("", "No image found");

        return picture;
    }
}
