package io.sh4.nasaapihandler.adapters;

import io.sh4.nasaapihandler.models.Weather;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class CommonMathsAdapter extends Mean {

    private Weather weather;

    public CommonMathsAdapter(Weather weather) {
        this.weather = weather;
    }

    @Override
    public double evaluate() {
        return evaluate(weather);
    }

    public double evaluate(Weather weather) {
        double[] arr = new double[2];
        arr[0] = weather.getMax();
        arr[1] = weather.getMin();
        return evaluate(arr);
    }
}
