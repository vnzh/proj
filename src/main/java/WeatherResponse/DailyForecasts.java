package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecasts {
    @JsonProperty("Date")
    private String date;
    @JsonProperty("EpochDate")
    private String epochDate;
    @JsonProperty("Temperature")
    private Temperature temperature;

    @JsonProperty("Day")
    private DayPart day;
    @JsonProperty("Night")
    private  DayPart night;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(String epochDate) {
        this.epochDate = epochDate;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public DayPart getDay() {
        return day;
    }

    public void setDay(DayPart day) {
        this.day = day;
    }

    public DayPart getNight() {
        return night;
    }

    public void setNight(DayPart night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return "DailyForecasts{" +
                "date='" + date + '\'' +
                ", epochDate='" + epochDate + '\'' +
                ", temperature=" + temperature +
                ", day=" + day +
                ", night=" + night +
                '}';
    }
}


