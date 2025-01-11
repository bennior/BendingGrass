package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import rendering.Background;
import rendering.Grass;
import rendering.Player;
import updating.MouseInputs;

public class Panel extends JPanel{
	
	public static final int GAME_WIDTH = 1088;
	public static final int GAME_HEIGHT = 576;
	public static final int TILE_SIZE = 32;
	public static final int TILES_IN_WIDTH = GAME_WIDTH / TILE_SIZE;
	public static final int TILES_IN_HEIGHT = GAME_HEIGHT / TILE_SIZE;
	
	private Background background;
	private Grass grass;
	private MouseInputs mouseInputs;
	private Player player;
	
	public Panel() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		init();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Rendering
		background.draw(g);
		grass.draw(g);
//		player.draw(g);
	}
	
	private void init() {
		
		//initialise
		background = new Background();
		grass = new Grass();
		mouseInputs = new MouseInputs();
		addMouseMotionListener(mouseInputs);
		player = new Player();
	}
}
