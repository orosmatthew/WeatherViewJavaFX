package Map;

import java.awt.image.BufferedImage;


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


}
