package io.sh4.nasaapihandler.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    private String url;
    private String msg;

    @Override
    public String toString() {
        return url;
    }
}
