package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DayPart {

    @JsonProperty("IconPhrase")
    private String  iconPhrase;

    public String getIconPhrase() {
        return iconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        this.iconPhrase = iconPhrase;
    }

    @Override
    public String toString() {
        return "DayPart{" +
                "iconPhrase='" + iconPhrase + '\'' +
                '}';
    }
}
