package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TempValue {
    @JsonProperty("Value")
    private String val;
    @JsonProperty("\"Unit\"")
    private String unit;
//    @JsonProperty("UnitType")
//    private String unitType;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "TempValue{" +
                "val='" + val + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
