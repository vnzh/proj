import WeatherResponse.AccueweatherResponse;
import WeatherResponse.Weather;

import java.io.IOException;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        AccuweatherModel model =new AccuweatherModel();

//        System.out.println(model.detectCityKey("moscow"));

       List<Weather> weathers = model.getWeather("Moscow", Period.FIVE_DAYS);
//        System.out.println(model.getWeather("Moscow", Period.FIVE_DAYS));

//        for (Weather w: weathers) {
//            System.out.println(w);
//        }

        WeatherStorage storage = new WeatherStorage();
        storage.addResalts(weathers);
        System.out.println(storage.info());
    }
}