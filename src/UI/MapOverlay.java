package UI;

import Map.Coordinate;
import javafx.scene.image.Image;

import java.io.File;

public class MapOverlay {

    private final Image overlayImage;
    private final Coordinate coordinate;

    public MapOverlay(String overlay, Coordinate coordinate) {
        File file = new File("overlays/" + overlay + ".png");
        this.overlayImage = new Image(file.toURI().toString());
        this.coordinate = coordinate;
    }

    public Image getOverlayImage() {
        return overlayImage;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void click() {

    }
}
