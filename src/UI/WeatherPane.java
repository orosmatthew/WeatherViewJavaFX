package UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class WeatherPane extends GridPane {

    TextArea weatherText = new TextArea();

    public WeatherPane() {
        weatherText = new TextArea();
        weatherText.setEditable(false);
        add(weatherText, 0, 0);
    }

    public void setText(String text) {
        weatherText.setText(text);
    }

}
