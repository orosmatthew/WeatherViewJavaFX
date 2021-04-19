package Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Map {

    private BufferedImage image;
    private final TileServer tileServer;
    private final String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";
    private int tileX;
    private int tileY;

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getZoom() {
        return zoom;
    }

    private int zoom;

    public Map() {

        tileServer = new CachedTileServer();

        zoom = 7;
        double lat = 41.347881d;
        double lon = -81.808503d;

        Tile tile = MapHelper.getTileNumbers(lat, lon, zoom);

        tileX = tile.tileX;
        tileY = tile.tileY;

        updateTile();
    }

    private void updateTile() {
        MapTile tile = new MapboxTile(tileX, tileY, zoom, MapboxAccessToken, true);
        image = tileServer.getTile(tile);
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getImage(int imageSize) {
        return resizeImage(image, imageSize, imageSize);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }


}
