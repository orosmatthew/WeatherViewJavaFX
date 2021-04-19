package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Weather View");

        GridPane gridPane = new GridPane();

        MapView mapView = new MapView(450, 7);

        //double lat2 = 41.656497;
        //double lon2 = -83.478575;

        //Overlay cloudOverlay = new Overlay("cloud", lat2, lon2);
        //mapView.getOverlays().add(cloudOverlay);

        gridPane.add(mapView, 0, 0, 2, 1);
        gridPane.setAlignment(Pos.CENTER);

        Label latitudeLabel = new Label("Latitude: ");
        Label longitudeLabel = new Label("Longitude: ");

        TextField latitudeField = new TextField(String.valueOf(mapView.getLatitude()));
        TextField longitudeField = new TextField(String.valueOf(mapView.getLongitude()));

        gridPane.add(latitudeLabel, 0, 1);
        gridPane.add(longitudeLabel, 0, 2);

        gridPane.add(latitudeField, 1, 1);
        gridPane.add(longitudeField, 1, 2);

        Button mapButton = new Button("Map!");

        mapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mapView.setLatitude(Double.parseDouble(latitudeField.getText()));
                mapView.setLongitude(Double.parseDouble(longitudeField.getText()));
                mapView.getOverlays().clear();
                Overlay crossOverlay = new Overlay("cross", Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()));
                mapView.getOverlays().add(crossOverlay);
                mapView.updateMap();
            }
        });

        gridPane.add(mapButton, 1, 3);

        gridPane.setHgap(10);
        gridPane.setVgap(10);


        mapView.updateMap();

        Scene scene = new Scene(gridPane, 550, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
