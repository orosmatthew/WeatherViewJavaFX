package UI;

import Map.BoundingBox;
import Map.Map;
import Map.MapHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MapView extends Pane {

    private final int mapSize;
    private final ImageView imageView;
    private final ArrayList<Overlay> overlays = new ArrayList<>();
    private final ArrayList<ImageView> overlayImageViews = new ArrayList<>();
    private final int zoom;
    private Map map;
    private Image mapImage;
    private BoundingBox boundingBox;
    private double latitude;
    private double longitude;

    public MapView(int mapSize, int zoom) {
        this.mapSize = mapSize;
        this.zoom = zoom;
        latitude = 41.347881d;
        longitude = -81.808503d;
        imageView = new ImageView();
        getChildren().add(imageView);
        updateMap();
    }

    public ArrayList<Overlay> getOverlays() {
        return overlays;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void updateMap() {
        map = new Map(latitude, longitude, zoom);
        BufferedImage mapBufferedImage = map.getImage(this.mapSize);
        mapImage = SwingFXUtils.toFXImage(mapBufferedImage, null);
        boundingBox = MapHelper.tile2boundingBox(map.getTileX(), map.getTileY(), map.getZoom());

        imageView.setImage(mapImage);

        for (ImageView imageView : overlayImageViews) {
            getChildren().remove(imageView);
        }

        overlayImageViews.clear();

        for (Overlay overlay : overlays) {
            int[] pos = latlon2map(overlay.getLatitude(), overlay.getLongitude());

            ImageView overlayImageView = new ImageView(overlay.getOverlayImage());

            getChildren().add(overlayImageView);
            overlayImageViews.add(overlayImageView);

            overlayImageView.setScaleX(1);
            overlayImageView.setScaleY(1);

            overlayImageView.setX(mapSize * -0.01);
            overlayImageView.setY(mapSize * -0.01);

            overlayImageView.setTranslateX(pos[0]);
            overlayImageView.setTranslateY(pos[1]);
        }

    }

    public int[] latlon2map(double lat, double lon) {

        double rangeX = Math.abs(boundingBox.east - boundingBox.west);
        double rangeY = Math.abs(boundingBox.north - boundingBox.south);

        double shortLat = lat - boundingBox.south;
        double shortLon = lon - boundingBox.west;

        int[] pos = new int[]{0, 0};

        pos[0] = (int) ((mapSize * shortLon) / rangeX);
        pos[1] = mapSize - (int) ((mapSize * shortLat) / rangeY);

        return pos;

    }


}
