
package rendering;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image {

	public static final String grass_background = "grass_background.png";
	public static final String grass_sprites = "grass_sprites.png";
	
	public static BufferedImage getImage(String fileName) {
		BufferedImage img = null;
		InputStream is = Image.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
}
