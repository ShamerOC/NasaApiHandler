package io.sh4.nasaapihandler.controllers;

import io.sh4.nasaapihandler.models.Picture;
import io.sh4.nasaapihandler.models.PictureList;
import io.sh4.nasaapihandler.services.ServicesFasade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureOfTheDayController {

    private final ServicesFasade servicesFasade;

    @Autowired
    PictureOfTheDayController(ServicesFasade servicesFasade) {
        this.servicesFasade = servicesFasade;
    }

    @GetMapping(path = "/picture")
    public Picture[] getPicture() {
        PictureList pictures = servicesFasade.getPictureOfTheDay();
        var it = pictures.iterator();
        Picture[] result = new Picture[pictures.size()];
        int i = 0;
        while (it.hasNext()) {
            result[i++] = it.next();
        }
        return result;
    }
}
