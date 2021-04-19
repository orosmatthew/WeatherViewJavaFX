package UI;

import Map.BoundingBox;
import Map.Coordinate;
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
    private final Map map;
    private int zoom;
    private Image mapImage;
    private BoundingBox boundingBox;
    private Coordinate coordinate;

    public MapView(int mapSize, int zoom) {
        this.mapSize = mapSize;
        this.zoom = zoom;
        coordinate = new Coordinate(41.347881d, -81.808503d);
        imageView = new ImageView();
        getChildren().add(imageView);
        map = new Map(coordinate, this.zoom);
        updateMap();
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public ArrayList<Overlay> getOverlays() {
        return overlays;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void updateMap() {
        map.setCoordinate(coordinate);
        map.setZoom(zoom);
        map.updateTile();
        BufferedImage mapBufferedImage = map.getImage(this.mapSize);
        mapImage = SwingFXUtils.toFXImage(mapBufferedImage, null);
        boundingBox = MapHelper.tile2boundingBox(map.getTileX(), map.getTileY(), map.getZoom());

        imageView.setImage(mapImage);

        for (ImageView imageView : overlayImageViews) {
            getChildren().remove(imageView);
        }

        overlayImageViews.clear();

        for (Overlay overlay : overlays) {
            int[] pos = coord2map(overlay.getCoordinate());

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

    public int[] coord2map(Coordinate coordinate) {

        double rangeX = Math.abs(boundingBox.east - boundingBox.west);
        double rangeY = Math.abs(boundingBox.north - boundingBox.south);

        double shortLat = coordinate.getLat() - boundingBox.south;
        double shortLon = coordinate.getLon() - boundingBox.west;

        int[] pos = new int[]{0, 0};

        pos[0] = (int) ((mapSize * shortLon) / rangeX);
        pos[1] = mapSize - (int) ((mapSize * shortLat) / rangeY);

        return pos;

    }


}
