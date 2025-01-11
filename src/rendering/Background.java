package rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static main.Panel.TILES_IN_WIDTH;
import static main.Panel.TILES_IN_HEIGHT;
import static main.Panel.TILE_SIZE;

public class Background {
	
	BufferedImage grass;	            
	
	public Background() {
		initSprites();
	}

	private void initSprites() {
		BufferedImage originalImg = Image.getImage(Image.grass_background);
		grass = originalImg;
		
	}
	
	public void draw(Graphics g) {
		
		for(int j = 0; j < TILES_IN_HEIGHT; j++) {
			for(int i = 0; i < TILES_IN_WIDTH; i++) {
				g.drawImage(grass, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
}
