package Map;

public class MapboxTile implements MapTile {

    private final String accessToken;
    private final boolean isDoubleResolution;
    private final int zoom;
    private final int tileX;
    private final int tileY;

    public MapboxTile(int tileX, int tileY, int zoom, String accessToken, boolean isDoubleResolution) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.zoom = zoom;
        this.accessToken = accessToken;
        this.isDoubleResolution = isDoubleResolution;
    }

    public MapboxTile(int tileX, int tileY, int zoom, String accessToken) {
        this(tileX, tileY, zoom, accessToken, false);
    }

    public String getURL() {

        String doubleResolutionString;

        if (isDoubleResolution) {
            doubleResolutionString = "@2x";
        } else {
            doubleResolutionString = "";
        }

        return String.format("https://api.mapbox.com/v4/mapbox.satellite/"
                + zoom + "/" + tileX + "/" + tileY
                + doubleResolutionString
                + ".jpg?access_token="
                + accessToken);
    }

    @Override
    public int getTileX() {
        return tileX;
    }

    @Override
    public int getTileY() {
        return tileY;
    }

    @Override
    public int getZoom() {
        return zoom;
    }

}
