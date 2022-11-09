import WeatherResponse.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherStorage {

    private List<Weather> weathers  ;

    public WeatherStorage() {
        this.weathers = new ArrayList<>();
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public void addResalts (List<Weather> w) {
        this.getWeathers().addAll(w);
    }


    public String info() {
       StringBuilder builder = new StringBuilder();
        for (Weather w: weathers) {
          builder.append(w);
          builder.append("\n");
        }
        builder.delete(builder.length()-2, builder.length()-1);
        return builder.toString();
    }
}
