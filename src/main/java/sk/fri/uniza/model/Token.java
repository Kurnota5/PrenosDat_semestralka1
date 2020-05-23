package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("token")
    private String token;

    @JsonProperty("token")
    public String getStationName() {
        return token;
    }
    @JsonProperty("token")
    public void setStationName(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}
