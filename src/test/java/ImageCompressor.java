import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageCompressor {
    public static void main(String[] args) throws IOException {
        URL url = ImageCompressor.class.getClassLoader().getResource("images/dark.png");
        BufferedImage image = ImageIO.read(url);
        Thumbnails.of(image)
                .size(image.getWidth(), image.getHeight())
                .outputFormat("jpg")
                .toFile("test.jpg");
    }
}
