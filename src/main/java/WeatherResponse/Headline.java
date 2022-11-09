package WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Headline {
    @JsonProperty("EffectiveDate")
    private String effectiveDate;
    @JsonProperty("EffectiveEpochDate")
    private float effectiveEpochDate;
    @JsonProperty("Severity")
    private float severity;
    @JsonProperty("Text")
    private String text;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("EndDate")
    private String endDate;
    @JsonProperty("EndEpochDate")
    private float endEpochDate;
    @JsonProperty("MobileLink")
    private String mobileLink;
    @JsonProperty("Link")
    private String link;


    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public float getEffectiveEpochDate() {
        return effectiveEpochDate;
    }

    public void setEffectiveEpochDate(float effectiveEpochDate) {
        this.effectiveEpochDate = effectiveEpochDate;
    }

    public float getSeverity() {
        return severity;
    }

    public void setSeverity(float severity) {
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getEndEpochDate() {
        return endEpochDate;
    }

    public void setEndEpochDate(float endEpochDate) {
        this.endEpochDate = endEpochDate;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Headline{" +
                "effectiveDate='" + effectiveDate + '\'' +
                ", effectiveEpochDate=" + effectiveEpochDate +
                ", severity=" + severity +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endEpochDate=" + endEpochDate +
                ", mobileLink='" + mobileLink + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}