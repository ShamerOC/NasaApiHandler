package io.sh4.nasaapihandler.models;

import java.util.Iterator;

public class PictureIterator implements Iterator<Picture> {

    private final PictureList pictures;
    private int index;

    public PictureIterator(PictureList pictures) {
        this.pictures = pictures;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return pictures.size() > index + 1;
    }

    @Override
    public Picture next() {
        Picture picture = pictures.get(index + 1);
        index++;
        return picture;
    }
}
