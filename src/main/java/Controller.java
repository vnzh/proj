import WeatherResponse.Weather;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Controller {
    private UISimple uiSimple;
    private AccuweatherModel accuweatherModel;
    private WeatherStorage weatherStorage;
    private SQLite lite;

    public AccuweatherModel getAccuweatherModel() {
        return accuweatherModel;
    }

    public void setAccuweatherModel(AccuweatherModel accuweatherModel) {
        this.accuweatherModel = accuweatherModel;
    }

    public Controller(UISimple uiSimple) {
        this.uiSimple = uiSimple;
        this.weatherStorage = new WeatherStorage();
        this.lite = new SQLite(this);
    }

    public void weatherReq(String sity, String period) throws IOException, SQLException, ClassNotFoundException {
//       System.out.println("gjksjkfh");
        setAccuweatherModel(new AccuweatherModel(this));
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
        lite.connnection();
        boolean hs = lite.hasCity(sity);
        lite.disconnect();

        switch (period) {
            case "1":

//                if   DB  has   forecast  get data  from DB;  else  get request to accueweather
                if (hs) {
                    lite.connnection();
                    int cityId = lite.selectCity(sity);
                    lite.disconnect();
                    lite.connnection();
                    boolean cD = lite.checkBySityDate(cityId, date);
                    lite.disconnect();
                    if (cD) { lite.connnection();
                        for (Weather w : lite.selectForecasts(cityId, date)) {
                            uiSimple.printWeather(w.toString());
                            lite.disconnect();
                        }
                    }
                } else
                    getAccuweatherModel().getWeather(sity, Period.NOW);
                break;
            case "5":
                if (hs) {lite.connnection();
                    int cityId = lite.selectCity(sity);
                    lite.disconnect();
                    calendar.add(Calendar.DAY_OF_MONTH, 5);
                    date = dateFormat.format(calendar.getTime());
                    lite.connnection();
                    boolean cD = lite.checkBySityDate(cityId, date);
                    lite.disconnect();
                    if (cD) {
                        calendar.add(Calendar.DAY_OF_MONTH, -5);
                        date = dateFormat.format(calendar.getTime());
                        for (int i = 0; i <= 5; i++) {
                            lite.connnection();
                            uiSimple.printWeather(lite.selectForecasts(cityId, date).toString());
                            lite.disconnect();
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                            date = dateFormat.format(calendar.getTime());
                            System.out.println(date);
                        }
                    } else getAccuweatherModel().getWeather(sity, Period.FIVE_DAYS);
                } else {
                    getAccuweatherModel().getWeather(sity, Period.FIVE_DAYS);
                }
                break;
            case "9":
                getWeatherFromDB();
            default:
                break;
        }

    }

    private void getWeatherFromDB() throws SQLException, ClassNotFoundException {
        lite.connnection();
        for (Weather w : lite.selectForecasts()) {
            uiSimple.printWeather(w.toString());
        }
        lite.disconnect();
    }


    public void writeWeather(List<Weather> weathers) {
//        System.out.println(weathers);
//        weatherStorage.addResalts(weathers);
//        System.out.println(weatherStorage.info());
        for (Weather w : weathers) {
            uiSimple.printWeather(w.toString());
        }

        try {
            writeWeatherToDB(weathers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void writeWeatherToDB(List<Weather> weathers) throws SQLException, ClassNotFoundException {
        //check  if  city  has in DB
        //add  sity  if hasnt
        lite.connnection();
        if (!lite.hasCity(weathers.get(0).getLocation())) {
            lite.insertCity(weathers.get(0).getLocation());
        }lite.disconnect();
        lite.connnection();
        int cityId = lite.selectCity(weathers.get(0).getLocation());
        lite.disconnect();
//        System.out.println("cityId   after " + cityId);


        for (Weather w : weathers) {
//            System.out.println("cityId foreach  " + cityId);
            lite.connnection();
            boolean cs = lite.checkBySityDate(cityId, w.getDate());
            lite.disconnect();
            lite.connnection();
            if (cs) {
                lite.updateOnSityDate(cityId, w.getDate(), w.getInfo(), w.getTemperature());
            } else {
                lite.insertInto(cityId, w.getDate(), w.getInfo(), w.getTemperature());
            }
        }
        lite.disconnect();
    }
}