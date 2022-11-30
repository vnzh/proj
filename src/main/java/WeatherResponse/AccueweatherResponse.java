package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccueweatherResponse implements WeatherResponse {
    @JsonProperty("Headline")
    private Headline headline;
    @JsonProperty("DailyForecasts")
    private List<DailyForecasts> dailyForecasts;

    public List<DailyForecasts> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecasts> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "Whether{" +
                "headline=" + headline +
                ", dailyForecasts=" + dailyForecasts +
                '}';
    }


}