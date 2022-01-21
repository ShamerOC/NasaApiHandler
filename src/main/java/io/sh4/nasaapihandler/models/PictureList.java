package io.sh4.nasaapihandler.models;

import java.util.ArrayList;
import java.util.Iterator;

public class PictureList extends ArrayList<Picture> {

    @Override
    public Iterator<Picture> iterator() {
        return new PictureIterator(this);
    }
}
