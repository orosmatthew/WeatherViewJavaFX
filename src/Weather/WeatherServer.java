package Weather;

import Map.Coordinate;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class WeatherServer {

    private final String[] errorMessages = new String[]{
            "Marine Forecast Not Supported",
            "Data Unavailable For Requested Point",
            "Unexpected Problem"
    };
    HttpClient client;

    public WeatherServer() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

    }

    public WeatherResponse getWeather(Coordinate coordinate) {

        DecimalFormat decimalFormat = new DecimalFormat("#.####");

        String url = String.format("https://api.weather.gov/points/%s,%s", decimalFormat.format(coordinate.getLat()), decimalFormat.format(coordinate.getLon()));

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            for (String message : errorMessages) {
                if (response.body().contains(message)) {
                    return null;
                }
            }
            WeatherPointResponse weatherPointResponse = gson.fromJson(response.body(), WeatherPointResponse.class);
            if (weatherPointResponse != null || weatherPointResponse.getProperties().getForecast() != null) {
                WeatherResponse weatherResponse = getForecast(weatherPointResponse.getProperties().getForecast());
                if (weatherResponse != null) {
                    if (weatherResponse.getProperties() != null) {
                        if (weatherResponse.getProperties().getPeriods() != null) {
                            return weatherResponse;
                        }
                    }
                }
                return null;
            }
            return null;
        } catch (IOException exception) {
            System.out.println("Unable to communicate with web server");
            return null;
        } catch (InterruptedException interruptedException) {
            System.out.println("Connection interrupted");
            return null;
        }
    }

    private WeatherResponse getForecast(String url) {


        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(response.body(), WeatherResponse.class);

            return weatherResponse;

        } catch (IOException exception) {
            System.out.println("Unable to communicate with web server");
            return null;
        } catch (InterruptedException interruptedException) {
            System.out.println("Connection interrupted");
            return null;
        }
    }

}
