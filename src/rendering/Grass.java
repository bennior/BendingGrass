package rendering;

import static main.Panel.TILES_IN_HEIGHT;
import static main.Panel.TILES_IN_WIDTH;
import static main.Panel.TILE_SIZE;
import static main.GameLoop.UPS_SET;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import updating.MouseInputs;

public class Grass {
	
	private BufferedImage grass_sprites, down, up, right, left;
	private ArrayList<Rectangle2D.Float> rectangles;
	private int currentx = MouseInputs.xPos, currenty =  MouseInputs.yPos, lastx =  MouseInputs.xPos, lasty =  MouseInputs.yPos;
	
	public Grass() {
		init();
	}
	
	public void draw(Graphics g) {
		
		calculate();
		
		for(Rectangle2D.Float rectangle : rectangles) {
			
			timer((int) rectangle.x, (int) rectangle.y, (int) rectangle.width, (int) rectangle.height);
//			System.out.println(rectangle.height);
			
			switch((int) rectangle.width) { //in two dimensional arrays x (row) and y (column) are weird/swapped
						
			case 0: g.drawImage(up, (int) rectangle.x, (int) rectangle.y, TILE_SIZE, TILE_SIZE, null);
				break;
			case 1: g.drawImage(left, (int) rectangle.x, (int) rectangle.y, TILE_SIZE, TILE_SIZE, null);
				break;
			case 2: g.drawImage(down, (int) rectangle.x, (int) rectangle.y, TILE_SIZE, TILE_SIZE, null);
				break;
			case 3: g.drawImage(right, (int) rectangle.x, (int) rectangle.y, TILE_SIZE, TILE_SIZE, null);
				break;
			}
			
		}
		
	}
	
	public void calculate() {
		
		if(MouseInputs.getDragged()) {
			float fx = MouseInputs.xPos / (float) (TILE_SIZE);
			float fy = MouseInputs.yPos / (float) (TILE_SIZE);

			
			int ix = MouseInputs.xPos / TILE_SIZE;
			int iy = MouseInputs.yPos / TILE_SIZE;
			
			if(ix >= 0 && ix < TILES_IN_WIDTH && iy >= 0 && iy < TILES_IN_HEIGHT) { //if in raster
				if(rectangles.get(ix * TILES_IN_HEIGHT + iy).width == 0) { //if normal grass
				
					if(movement()) { //if horizontal movement
						
						horizontalmov(fy, ix, iy);
						
					}else { //if vertical movement
						
						verticalmov(fx, ix, iy);

					}
				}
			}
		}
	}  
	
	public void timer(int x, int y, int state, int timer) {
				
		if(state != 0) {
			rectangles.get((x / TILE_SIZE) * TILES_IN_HEIGHT + y / TILE_SIZE).height = (float) (rectangles.get((x / TILE_SIZE) * TILES_IN_HEIGHT + y / TILE_SIZE).height + 1.0);
			
			if(timer == 8 * UPS_SET) { //4s
				rectangles.get((x / TILE_SIZE) * TILES_IN_HEIGHT + y / TILE_SIZE).width = 0;
				rectangles.get((x / TILE_SIZE) * TILES_IN_HEIGHT + y / TILE_SIZE).height = 0;
			}
		}
	}
	
	public boolean movement() {
		currentx = MouseInputs.xPos;
		currenty = MouseInputs.yPos;
		
		if(Math.abs(currentx - lastx) > Math.abs(currenty - lasty)) {
			lastx = currentx;
			lasty = currenty;
			return true; //horizontal
		}else {
			lastx = currentx;
			lasty = currenty;
			return false; //vertical
		}
	}
	
	private void verticalmov(float fx, int ix, int iy) {
		
		if(fx < ix + 1.0 / 3) {  //left flat
			rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 1;
			
			if(ix - 1 > 0 && rectangles.get((ix - 1) * TILES_IN_HEIGHT + iy).width == 0) {
			rectangles.get((ix - 1) * TILES_IN_HEIGHT + iy).width = 3;
			}
		}
		
		else if(ix + 1.0 / 3 < fx && fx < ix + 2.0 / 3) {  //middle
		rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 2;
		}
		
		else if(ix + 2.0 / 3 < fx) { //right flat
			rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 3;
			
			if(ix + 1 < TILES_IN_WIDTH && rectangles.get((ix + 1) * TILES_IN_HEIGHT + iy).width == 0) {
			rectangles.get((ix + 1) * TILES_IN_HEIGHT + iy).width = 1;
			}
		}
	}
	
	private void horizontalmov(float fy, int ix, int iy) {
		
		if(fy < iy + 1.0 / 3) {  //left flat
			rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 2;
			
			if(iy - 1 > 0 && rectangles.get(ix * TILES_IN_HEIGHT + (iy - 1)).width == 0) {
			rectangles.get(ix * TILES_IN_HEIGHT + (iy - 1)).width = 2;
			}
		}
		
		else if(iy + 1.0 / 3 < fy && fy < iy + 2.0 / 3) {  //middle
		rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 2;
		}
		
		else if(iy + 2.0 / 3 < fy) { //right flat
			rectangles.get(ix * TILES_IN_HEIGHT + iy).width = 2;
			
			if(iy + 1 < TILES_IN_HEIGHT && rectangles.get(ix * TILES_IN_HEIGHT + (iy + 1)).width == 0) {
			rectangles.get(ix * TILES_IN_HEIGHT + (iy + 1)).width = 2;
			}
		}
	}
	
	private void init() {
		grass_sprites = Image.getImage(Image.grass_sprites);
		
		up = grass_sprites.getSubimage(0 * 32, 0 * 32, 32, 32);
		down = grass_sprites.getSubimage(1 * 32, 0 * 32, 32, 32);
		left = grass_sprites.getSubimage(1 * 32, 1 * 32, 32, 32);
		right = grass_sprites.getSubimage(0 * 32, 1 * 32, 32, 32);
	
		rectangles = new ArrayList<>();
		for(int i = 0; i < TILES_IN_WIDTH; i++) {
			for(int j = 0; j < TILES_IN_HEIGHT; j++) {
				rectangles.add(new Rectangle2D.Float(i * TILE_SIZE, j * TILE_SIZE, 0, 0));
			}
		}
		
	}
}
