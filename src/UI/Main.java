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
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Weather View");

        Map map = new Map();
        BufferedImage mapImage = map.getImage();
        Image image = SwingFXUtils.toFXImage(mapImage, null);

        GridPane gridPane = new GridPane();
        ImageView imageView = new ImageView(image);

        gridPane.add(imageView, 0, 0);

        Scene scene = new Scene(gridPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
