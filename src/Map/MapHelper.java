package Map;

public class MapHelper {

    private MapHelper() {

    }

    // helper method obtained from openstreetmap.org
    public static Tile getTileNumbers(Coordinate coordinate, final int zoom) {
        int xtile = (int) Math.floor((coordinate.getLon() + 180) / 360 * (1 << zoom));
        int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(coordinate.getLat())) + 1 / Math.cos(Math.toRadians(coordinate.getLat()))) / Math.PI) / 2 * (1 << zoom));
        if (xtile < 0)
            xtile = 0;
        if (xtile >= (1 << zoom))
            xtile = ((1 << zoom) - 1);
        if (ytile < 0)
            ytile = 0;
        if (ytile >= (1 << zoom))
            ytile = ((1 << zoom) - 1);

        Tile tile = new Tile();
        tile.tileX = xtile;
        tile.tileY = ytile;
        tile.zoom = zoom;

        return tile;
    }

    // helper method obtained from openstreetmap.org
    public static BoundingBox tile2boundingBox(final int x, final int y, final int zoom) {
        BoundingBox bb = new BoundingBox();
        bb.north = tile2lat(y, zoom);
        bb.south = tile2lat(y + 1, zoom);
        bb.west = tile2lon(x, zoom);
        bb.east = tile2lon(x + 1, zoom);
        return bb;
    }

    // helper method obtained from openstreetmap.org
    public static double tile2lon(int x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180;
    }

    // helper method obtained from openstreetmap.org
    public static double tile2lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }

}
