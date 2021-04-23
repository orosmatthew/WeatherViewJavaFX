package UI;

import Map.Coordinate;
import Weather.WeatherServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MapPane extends GridPane {

    private WeatherPane weatherPane;

    public MapPane() {
        MapView mapView = new MapView(480, 7);

        add(mapView, 0, 0, 2, 1);
        setAlignment(Pos.CENTER);

        Label latitudeLabel = new Label("Latitude: ");
        Label longitudeLabel = new Label("Longitude: ");

        TextField latitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLat()));
        TextField longitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLon()));

        add(latitudeLabel, 0, 1);
        add(longitudeLabel, 0, 2);

        add(latitudeField, 1, 1);
        add(longitudeField, 1, 2);


        Label zoomLabel = new Label("Zoom Level: ");
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (int i = 0; i < 15; i++) {
            options.add(i);
        }
        final ComboBox zoomMenu = new ComboBox(options);
        zoomMenu.setValue(7);

        add(zoomLabel, 0, 3);
        add(zoomMenu, 1, 3);

        Button mapButton = new Button("Map!");

        WeatherServer weatherServer = new WeatherServer();

        mapButton.setOnAction(actionEvent -> {
            Coordinate inputCoordinate = new Coordinate(Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()));
            mapView.setCoordinate(inputCoordinate);
            mapView.setZoom((Integer) zoomMenu.getValue());
            mapView.getOverlays().clear();
            WeatherOverlay crossOverlay = new WeatherOverlay("cross", inputCoordinate, weatherServer, weatherPane);
            mapView.getOverlays().add(crossOverlay);
            mapView.updateMap();
        });

        add(mapButton, 1, 4);

        setHgap(10);
        setVgap(10);


        mapView.updateMap();
    }

    public void setWeatherPane(WeatherPane weatherPane) {
        this.weatherPane = weatherPane;
    }

}
