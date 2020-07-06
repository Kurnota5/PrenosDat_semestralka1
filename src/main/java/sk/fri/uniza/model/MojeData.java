package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MojeData {
    @JsonProperty("dateTime")
    private String dateTime;
    @JsonProperty("value")
    private String value;
    @JsonProperty("type")
    private String type;

    public MojeData(String dateTime, String value, String type) {
        this.dateTime = dateTime;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "dateTime='" + dateTime + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'';
    }
}
