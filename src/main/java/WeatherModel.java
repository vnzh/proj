import WeatherResponse.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherModel {

    public  void getWeather(String sity, Period period) throws IOException;
}
