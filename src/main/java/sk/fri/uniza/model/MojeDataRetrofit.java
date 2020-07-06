package sk.fri.uniza.model;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.api.DobrovolnaUloha2Service;

public class MojeDataRetrofit {
    private final Retrofit retrofit;
    private final DobrovolnaUloha2Service dobrovolnaUloha2Service;

    public MojeDataRetrofit() {
        retrofit = new Retrofit.Builder()
                // Url adresa servera
                .baseUrl("http://localhost:8080/")
                // Na konvertovanie JSON objektu na java POJO použijeme
                // Jackson knižnicu
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Vytvorenie inštancie komunikačného rozhrania
        dobrovolnaUloha2Service = retrofit.create(DobrovolnaUloha2Service.class);
    }

    public DobrovolnaUloha2Service getDobrovolnaUloha2Service() {
        return dobrovolnaUloha2Service;
    }

}
