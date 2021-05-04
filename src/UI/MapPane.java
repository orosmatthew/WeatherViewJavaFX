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
import javafx.scene.layout.HBox;

public class MapPane extends GridPane {

    private final TextField latitudeField;
    private final TextField longitudeField;
    private final MapView mapView;
    private final ComboBox zoomMenu;
    private final WeatherServer weatherServer;
    private WeatherPane weatherPane;

    public MapPane() {
        mapView = new MapView(450, 7);

        mapView.setOnMouseClicked(mouseEvent -> {
            Coordinate coordinate = mapView.map2coord(new int[]{(int) mouseEvent.getX(), (int) mouseEvent.getY()});
            setMapCoordinate(coordinate);
        });

        HBox navigationBox = new HBox();
        navigationBox.setSpacing(5);

        Button leftButton = new Button("←");
        leftButton.setOnAction(actionEvent -> {
            Coordinate mapCoord = mapView.getCoordinate();
            Coordinate newCoord = new Coordinate(mapCoord.getLat(), mapCoord.getLon() - 1);
            mapView.setCoordinate(newCoord);
            addCross(newCoord);
            mapView.updateMap();
        });
        Button rightButton = new Button("→");
        rightButton.setOnAction(actionEvent -> {
            Coordinate mapCoord = mapView.getCoordinate();
            Coordinate newCoord = new Coordinate(mapCoord.getLat(), mapCoord.getLon() + 1);
            mapView.setCoordinate(newCoord);
            addCross(newCoord);
            mapView.updateMap();
        });
        Button upButton = new Button("↑");
        upButton.setOnAction(actionEvent -> {
            Coordinate mapCoord = mapView.getCoordinate();
            Coordinate newCoord = new Coordinate(mapCoord.getLat() + 1, mapCoord.getLon());
            mapView.setCoordinate(newCoord);
            addCross(newCoord);
            mapView.updateMap();
        });
        Button downButton = new Button("↓");
        downButton.setOnAction(actionEvent -> {
            Coordinate mapCoord = mapView.getCoordinate();
            Coordinate newCoord = new Coordinate(mapCoord.getLat() - 1, mapCoord.getLon());
            mapView.setCoordinate(newCoord);
            addCross(newCoord);
            mapView.updateMap();
        });

        navigationBox.getChildren().add(leftButton);
        navigationBox.getChildren().add(rightButton);
        navigationBox.getChildren().add(upButton);
        navigationBox.getChildren().add(downButton);

        add(navigationBox, 0, 0);

        add(mapView, 0, 1, 2, 1);
        setAlignment(Pos.CENTER);

        Label latitudeLabel = new Label("Latitude: ");
        Label longitudeLabel = new Label("Longitude: ");

        latitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLat()));
        longitudeField = new TextField(String.valueOf(mapView.getCoordinate().getLon()));

        add(latitudeLabel, 0, 2);
        add(longitudeLabel, 0, 3);

        add(latitudeField, 1, 2);
        add(longitudeField, 1, 3);


        Label zoomLabel = new Label("Zoom Level: ");
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (int i = 0; i < 15; i++) {
            options.add(i);
        }
        zoomMenu = new ComboBox(options);
        zoomMenu.setValue(7);

        add(zoomLabel, 0, 4);
        add(zoomMenu, 1, 4);

        Button mapButton = new Button("Map!");

        weatherServer = new WeatherServer();

        mapButton.setOnAction(actionEvent -> {
            Coordinate inputCoordinate = new Coordinate(Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()));
            setMapCoordinate(inputCoordinate);
        });

        add(mapButton, 1, 5);

        setHgap(10);
        setVgap(10);

        addCross(mapView.getCoordinate());
        mapView.updateMap();
    }

    public void setWeatherPane(WeatherPane weatherPane) {
        this.weatherPane = weatherPane;
    }

    public void setMapCoordinate(Coordinate coordinate) {
        latitudeField.setText(String.valueOf(coordinate.getLat()));
        longitudeField.setText(String.valueOf(coordinate.getLon()));
        mapView.setCoordinate(coordinate);
        mapView.setZoom((Integer) zoomMenu.getValue());
        addCross(coordinate);
        mapView.updateMap();
    }

    public void addCross(Coordinate coordinate) {
        mapView.getOverlays().clear();
        WeatherOverlay crossOverlay = new WeatherOverlay("cross", coordinate, weatherServer, weatherPane);
        mapView.getOverlays().add(crossOverlay);
    }

}
