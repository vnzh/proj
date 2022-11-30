package WeatherResponse;

import java.util.Date;

public class Weather {

    private String location;
    private String date;
    private String info;
    private double temperature;

    public Weather(String location, String date, String info, double temperature) {
        this.location = location;
        this.date = date;
        this.info = info;
        this.temperature = temperature;
    }

    public Weather() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
       return String.format("В городе %s на  дату %s ожидается %s, температура %.2f C",
                location, date, info, temperature);
    }

}
