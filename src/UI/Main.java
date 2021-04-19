package UI;

import Map.Coordinate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

        MapView mapView = new MapView(480, 7);

        //double lat2 = 41.656497;
        //double lon2 = -83.478575;

        //Overlay cloudOverlay = new Overlay("cloud", lat2, lon2);
        //mapView.getOverlays().add(cloudOverlay);

        gridPane.add(mapView, 0, 0, 2, 1);
        gridPane.setAlignment(Pos.CENTER);

        Label latitudeLabel = new Label("Latitude: ");
        Label longitudeLabel = new Label("Longitude: ");

        TextField latitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLat()));
        TextField longitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLon()));

        gridPane.add(latitudeLabel, 0, 1);
        gridPane.add(longitudeLabel, 0, 2);

        gridPane.add(latitudeField, 1, 1);
        gridPane.add(longitudeField, 1, 2);


        Label zoomLabel = new Label("Zoom Level: ");
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (int i = 0; i < 15; i++) {
            options.add(i);
        }
        final ComboBox zoomMenu = new ComboBox(options);
        zoomMenu.setValue(7);

        gridPane.add(zoomLabel, 0, 3);
        gridPane.add(zoomMenu, 1, 3);

        Button mapButton = new Button("Map!");

        mapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Coordinate inputCoordinate = new Coordinate(Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()));
                mapView.setCoordinate(inputCoordinate);
                mapView.setZoom((Integer) zoomMenu.getValue());
                mapView.getOverlays().clear();
                Overlay crossOverlay = new Overlay("cross", inputCoordinate);
                mapView.getOverlays().add(crossOverlay);
                mapView.updateMap();
            }
        });

        gridPane.add(mapButton, 1, 4);

        gridPane.setHgap(10);
        gridPane.setVgap(10);


        mapView.updateMap();

        Scene scene = new Scene(gridPane, 550, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
