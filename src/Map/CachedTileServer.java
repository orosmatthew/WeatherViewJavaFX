package Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CachedTileServer extends TileServer {

    private final Path cachePath;

    public CachedTileServer() {
        this(".cache");
    }

    public CachedTileServer(String cachePath) {
        this.cachePath = Paths.get(cachePath);
    }

    private static String getCachedTileName(MapTile request) {
        return String.format("%d_%d_%d", request.getTileX(), request.getTileY(), request.getZoom());
    }

    @Override
    public BufferedImage getTile(MapTile request) {

        if (!Files.exists(cachePath)) {
            try {
                Files.createDirectory(cachePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(cachePath.toString(), getCachedTileName(request) + ".jpg");
        BufferedImage image;

        if (Files.exists(Paths.get(cachePath.toString(), getCachedTileName(request) + ".jpg"))) {
            try {
                image = ImageIO.read(file);
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            image = super.getTile(request);
            try {
                ImageIO.write(image, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

    }
}
