package UI;

import Map.Map;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Weather View");



        GridPane gridPane = new GridPane();

        MapView mapView = new MapView();

        double lat1 = 41.347881d;
        double lon1 = -81.808503d;

        double lat2 = 41.656497;
        double lon2 = -83.478575;

        mapView.addOverlay("cross", lat1, lon1);
        mapView.addOverlay("cloud", lat2, lon2);

        gridPane.add(mapView, 1, 1);

        Scene scene = new Scene(gridPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
