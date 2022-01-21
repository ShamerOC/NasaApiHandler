package io.sh4.nasaapihandler.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Weather {
    private double min;
    private double max;
    private double mean;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private double min;
        private double max;
        private double mean;

        public Builder min(double min) {
            this.min = min;
            return this;
        }

        public Builder max(double max) {
            this.max = max;
            return this;
        }

        public Builder mean(double mean) {
            this.mean = mean;
            return this;
        }

        public Weather build() {
            Weather weather = new Weather();
            weather.min = this.min;
            weather.max = this.max;
            weather.mean = this.mean;
            return weather;
        }
    }
}
