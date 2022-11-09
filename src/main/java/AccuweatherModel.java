import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import WeatherResponse.DailyForecasts;
import WeatherResponse.Weather;
import WeatherResponse.AccueweatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "pXJd8MokcZCdrd2MsoGl2DBZAyCa0zvv";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final String LANGUAGE = "ru";
    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final String DETAILS = "false";
    private static final String DETAILS_QUERY_PARAM = "details";
    private static final String METRIC = "true";
    private static final String METRIC_QUERY_PARAM = "metric";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    //    curl -X GET "http://dataservice.accuweather.com/forecasts/v1/daily/1day/295212
//    ?apikey=AZJxwUoAS3KGeeEoQxTo15K1z5HHIVUZ&language=ru&details=false&metric=true
    public List<Weather> getWeather(String selectedCity, Period period) throws IOException {
        String weatherResponse = "";
        HttpUrl httpUrl = null;
        Request request = null;
        Response response = null;
        switch (period) {
            case NOW:
                httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(DETAILS_QUERY_PARAM, DETAILS)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC)
                        .build();

                request = new Request.Builder()
                        .url(httpUrl)
                        .build();
                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night

                break;
//            curl -X GET "http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212
//            ?apikey=AZJxwUoAS3KGeeEoQxTo15K1z5HHIVUZ&language=ru&metric=true"
            case FIVE_DAYS:
                //TODO*: реализовать вывод погоды на 5 дней
                httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(DETAILS_QUERY_PARAM, DETAILS)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC)
                        .build();

                request = new Request.Builder()
                        .url(httpUrl)
                        .build();
                break;
        }
        response = okHttpClient.newCall(request).execute();
        weatherResponse = response.body().string();

//        System.out.println(weatherResponse);

        return weather(selectedCity, weatherResponse);
    }

    private List<Weather> weather(String selectedCity, String weatherResponse) throws JsonProcessingException {
        AccueweatherResponse response =
                objectMapper.readValue(weatherResponse, AccueweatherResponse.class);
        List<Weather> weathers = new ArrayList();

        for (DailyForecasts d : response.getDailyForecasts()) {
//            long day = Long.parseLong(d.getEpochDate());
//            weather.setDate(new Date(day).toString());
            Weather weather = new Weather();
            weather.setLocation(selectedCity);
            weather.setDate(d.getDate().substring(0, 10));
            weather.setInfo(d.getDay().getIconPhrase());
            double max = Double.parseDouble(d.getTemperature().getMaximum().getVal());
            double min = Double.parseDouble(d.getTemperature().getMinimum().getVal());
            weather.setTemperature((max + min) / 2.0);
            weathers.add(weather);
//            System.out.println(weather);
        }
        return weathers;
    }


    //    curl -X GET "http://dataservice.accuweather.com/locations/v1/cities/autocomplete?
//    apikey=AZJxwUoAS3KGeeEoQxTo15K1z5HHIVUZ&q=moscow"
    public String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }


}
