package Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TileServer {

    public BufferedImage getTile(MapTile request) {
        URL url = null;
        BufferedImage image = null;

        try {
            url = new URL(request.getURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
