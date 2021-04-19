package UI;

import Map.Map;
import Map.BoundingBox;
import Map.MapHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.image.BufferedImage;
import java.io.File;

public class MapView extends Pane {

    private Map map;
    private Image mapImage;
    private ImageView imageView;
    private BoundingBox boundingBox;

    public MapView() {

        map = new Map();
        BufferedImage mapBufferedImage = map.getImage();
        mapImage = SwingFXUtils.toFXImage(mapBufferedImage, null);
        boundingBox = MapHelper.tile2boundingBox(map.getTileX(), map.getTileY(), map.getZoom());

        imageView = new ImageView(mapImage);
        getChildren().add(imageView);
    }

    public void addOverlay(String overlay, double lat, double lon) {

        int[] pos = latlon2map(lat, lon);

        File file = new File("overlays/" + overlay + ".png");
        Image cloudImage = new Image(file.toURI().toString());
        ImageView cloudImageView = new ImageView(cloudImage);

        getChildren().add(cloudImageView);

        cloudImageView.setScaleX(1);
        cloudImageView.setScaleY(1);

        cloudImageView.setX(-5);
        cloudImageView.setY(-5);

        cloudImageView.setTranslateX(pos[0]);
        cloudImageView.setTranslateY(pos[1]);
    }

    public int[] latlon2map(double lat, double lon) {

        double rangeX = Math.abs(boundingBox.east - boundingBox.west);
        double rangeY = Math.abs(boundingBox.north - boundingBox.south);

        double shortLat = lat - boundingBox.south;
        double shortLon = lon - boundingBox.west;

        int[] pos = new int[] {0, 0};

        pos[0] = (int)((500 * shortLon) / rangeX);
        pos[1] = 500 - (int)((500 * shortLat) / rangeY);


        return pos;

    }



}
