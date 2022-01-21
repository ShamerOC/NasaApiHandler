package io.sh4.nasaapihandler.controllers;

import io.sh4.nasaapihandler.models.Picture;
import io.sh4.nasaapihandler.services.ServicesFasade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureForGivenCoordinatesController {

    private final ServicesFasade servicesFasade;

    @Autowired
    public PictureForGivenCoordinatesController(ServicesFasade servicesFasade) {
        this.servicesFasade = servicesFasade;
    }

    @GetMapping(path = "/PictureForCoordinates/{latitude}/{longitude}")
    public Picture getPicture(@PathVariable String latitude, @PathVariable String longitude) { // dekorator
        return servicesFasade.getCoordinatesPicture(latitude, longitude);
    }
}
