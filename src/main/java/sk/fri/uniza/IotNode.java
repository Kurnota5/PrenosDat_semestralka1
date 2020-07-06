package sk.fri.uniza;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.api.WeatherStationService;
import sk.fri.uniza.model.Token;
import sk.fri.uniza.model.WeatherData;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class IotNode {
    private final Retrofit retrofit;
    private final WeatherStationService weatherStationService;

    public IotNode() {
        retrofit = new Retrofit.Builder()
                // Url adresa kde je umietnená WeatherStation služba
                .baseUrl("http://ip172-18-0-26-bs1ojq3oudsg00ar8v10-9000.direct.labs.play-with-docker.com/9000/")
                // Na konvertovanie JSON objektu na java POJO použijeme
                // Jackson knižnicu
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        // Vytvorenie inštancie komunikačného rozhrania
        weatherStationService = retrofit.create(WeatherStationService.class);
    }

    public WeatherStationService getWeatherStationService() {
        return weatherStationService;
    }

    double getAverageTemperature(String station, String from, String to) {
        double averageTempPom = 0;
        double numberOfDatasets = 0;

        Call<List<WeatherData>> historyWeatherPojo =
                weatherStationService.getHistoryWeather(station,from,to,List.of("airTemperature"));
        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<List<WeatherData>> response = historyWeatherPojo.execute();
            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                List<WeatherData> historyData = response.body();
                for(WeatherData data:historyData) {
                    averageTempPom += data.getAirTemperature();
                    numberOfDatasets++;
                }
                double averageTemp = averageTempPom/numberOfDatasets;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return averageTempPom;
    }

    String getJWTToken(String povodnyString, List<String> claims) {
        String b64String = Base64.getEncoder().encodeToString(povodnyString.getBytes());
        b64String = "Basic " + b64String;
        String to = "";
        Call<Token> getToken =
                weatherStationService.getToken(b64String,claims);
        try {
            // Odoslanie požiadavky na server pomocou REST rozhranie
            Response<Token> response = getToken.execute();
            if (response.isSuccessful()) { // Dotaz na server bol neúspešný
                //Získanie údajov vo forme inštancie triedy WeatherData
                Token token = response.body();
                to = token.toString();

                System.out.println(to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return to;
    }
}