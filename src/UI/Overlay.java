package UI;

import javafx.scene.image.Image;

import java.io.File;

public class Overlay {

    private final Image overlayImage;
    private final double latitude;
    private final double longitude;

    public Overlay(String overlay, double latitude, double longitude) {
        File file = new File("overlays/" + overlay + ".png");
        this.overlayImage = new Image(file.toURI().toString());
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Image getOverlayImage() {
        return overlayImage;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
