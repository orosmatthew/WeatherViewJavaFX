package Map;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Map {

    private final TileServer tileServer;
    private final String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";
    private BufferedImage image;
    private final int tileX;
    private final int tileY;
    private final int zoom;

    public Map(double lat, double lon, int zoom) {

        tileServer = new CachedTileServer();

        this.zoom = zoom;

        Tile tile = MapHelper.getTileNumbers(lat, lon, zoom);

        tileX = tile.tileX;
        tileY = tile.tileY;

        updateTile();
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getZoom() {
        return zoom;
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


}
