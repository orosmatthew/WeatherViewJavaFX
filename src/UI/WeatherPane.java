package UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class WeatherPane extends GridPane {

    TextArea weatherText;

    public WeatherPane() {
        weatherText = new TextArea();
        weatherText.setMinHeight(450);
        weatherText.setEditable(false);
        add(weatherText, 0, 0);
    }

    public void setText(String text) {
        weatherText.setText(text);
    }

}
