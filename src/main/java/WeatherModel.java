import WeatherResponse.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherModel {

    public List<Weather> getWeather(String sity, Period period) throws IOException;
}
