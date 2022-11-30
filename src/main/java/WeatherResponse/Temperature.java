package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("Minimum")
    private TempValue minimum;
    @JsonProperty("Maximum")
    private TempValue maximum;

    public TempValue getMinimum() {
        return minimum;
    }

    public void setMinimum(TempValue minimum) {
        this.minimum = minimum;
    }

    public TempValue getMaximum() {
        return maximum;
    }

    public void setMaximum(TempValue maximum) {
        this.maximum = maximum;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "minimum=" + minimum +
                ", maximum=" + maximum +
                '}';
    }
}
