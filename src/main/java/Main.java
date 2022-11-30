import WeatherResponse.AccueweatherResponse;
import WeatherResponse.Weather;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;

import static java.util.Calendar.*;


public class Main {

    public static void main(String[] args) throws IOException {
//        AccuweatherModel model =new AccuweatherModel();
//
////        System.out.println(model.detectCityKey("moscow"));
//
//       List<Weather> weathers = model.getWeather("Moscow", Period.FIVE_DAYS);
////        System.out.println(model.getWeather("Moscow", Period.FIVE_DAYS));
//        WeatherStorage storage = new WeatherStorage();
//        storage.addResalts(weathers);
////        for (Weather w: weathers) {
////            System.out.println(w);
////        }
//        weathers = model.getWeather("Moscow", Period.NOW);
//
//        storage.addResalts(weathers);
//        System.out.println(storage.info());
//
        Locale.setDefault(Locale.US);
        UISimple simple = new UISimple();
        try {
            simple.userInter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        String s = "2022-11-29T07:00";
//        System.out.printf("%s  %s %s  %s  %s \n", s.substring(0, 4), s.substring(5, 7),
//                s.substring(8, 10), s.substring(11, 13), s.substring(14, 16));
//        Calendar calendar = new GregorianCalendar(Integer.parseInt(s.substring(0, 4)),
//                Integer.parseInt(s.substring(5, 7)) - 1,
//                Integer.parseInt(s.substring(8, 10)), Integer.parseInt(s.substring(11, 13)),
//                Integer.parseInt(s.substring(14, 16)));
//        System.out.printf("%s  %s %s \n", calendar.get(DAY_OF_MONTH), calendar.get(MONTH), calendar.get(YEAR));
////        System.out.println(getInstance().getTime());
////        System.out.println(calendar.getTime());
//        calendar.add(DAY_OF_MONTH, 5);
//        System.out.println(calendar.getTime());
//        calendar = Calendar.getInstance();
//        System.out.printf("%s  %s %s \n", calendar.get(DAY_OF_MONTH), calendar.get(MONTH), calendar.get(YEAR));
//        System.out.println("HOUR  " + calendar.get(HOUR));
//        System.out.println(calendar.getTimeZone());
//        int diff = Integer.parseInt(s.substring(11, 13)) - calendar.get(HOUR);
//        System.out.println(diff);



    }
}