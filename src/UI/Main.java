package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather View");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER);

        WeatherPane weatherPane = new WeatherPane();

        MapPane mapPane = new MapPane();
        mapPane.setWeatherPane(weatherPane);

        hbox.getChildren().add(mapPane);
        hbox.getChildren().add(weatherPane);

        Scene scene = new Scene(hbox, 1000, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
