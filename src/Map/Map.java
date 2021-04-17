package Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Map {

    private BufferedImage image;
    private TileServer tileServer;
    private String MapboxAccessToken = "pk.eyJ1Ijoib3Jvc21hdHRoZXciLCJhIjoiY2ttczM4MGxxMGR0YjJ2bnhqa2ZpcnF3diJ9.WBoUUsXLofAZC9xM52N-oQ";
    private int tileX;
    private int tileY;
    private int zoom;
    private float speed = 10;

    double[] position;

    public Map() {

        tileServer = new CachedTileServer();

        position = new double[] {0, 0};

        zoom = 7;
        double lat = 41.347881d;
        double lon = -81.808503d;

        int[] tiles = MapHelper.getTileNumbers(lat, lon, zoom);

        tileX = tiles[0];
        tileY = tiles[1];

        displayTile();
    }

    private void displayTile() {
        MapTile tile = new MapboxTile(tileX, tileY, zoom, MapboxAccessToken, true);
        image = tileServer.getTile(tile);
    }


    public void process(double delta) {

        /*
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_W)) {
            tileY--;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_S)) {
            tileY++;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_A)) {
            tileX--;
            displayTile();
        }
        if (Engine.getInput().isKeyJustPressed(KeyEvent.VK_D)) {
            tileX++;
            displayTile();
        }
        */

    }

    public BufferedImage getImage() {
        return image;
    }


}
