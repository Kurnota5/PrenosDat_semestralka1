package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldData {
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @JsonProperty("name")
    private String meno;

    @JsonProperty("unit")
    private String jednotka;

    @JsonProperty("description")
    private String popis;

    public FieldData(String meno, String jednotka, String popis) {
        this.meno = meno;
        this.jednotka = jednotka;
        this.popis = popis;
    }
}
