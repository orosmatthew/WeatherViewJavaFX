package UI;

import Map.Coordinate;
import Weather.Period;
import Weather.WeatherResponse;
import Weather.WeatherServer;

public class WeatherOverlay extends MapOverlay {

    private final WeatherResponse weatherResponse;
    private final WeatherServer weatherServer;
    private final WeatherPane weatherPane;

    public WeatherOverlay(String overlay, Coordinate coordinate, WeatherServer weatherServer, WeatherPane weatherPane) {
        super(overlay, coordinate);
        this.weatherServer = weatherServer;
        weatherResponse = this.weatherServer.getWeather(getCoordinate());
        this.weatherPane = weatherPane;
        setWeather();
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    @Override
    public void click() {
        setWeather();
    }

    public void setWeather() {
        if (weatherResponse != null) {
            String weatherText = "";
            for (Period period : getWeatherResponse().getProperties().getPeriods()) {
                weatherText += period.getName() + ": " + period.getShortForecast() + "\n";
            }
            setWeatherText(weatherText);
        } else {
            setWeatherText("No Weather/Forecast Data Available!" + "\n");
        }
    }

    private void setWeatherText(String text) {
        if (weatherPane != null) {
            weatherPane.setText(text);
        }
    }

}
