package rendering;

import java.awt.Color;
import java.awt.Graphics;

import updating.MouseInputs;

public class Player {
	
	private int width = 32, height = 32;
	
	public void draw(Graphics g) {
		
		if(MouseInputs.getDragged()) {
			g.setColor(new Color(139,69,19));
			g.fillRect(MouseInputs.xPos - width / 2, MouseInputs.yPos - height / 2, width, height);
		}
	}
	
	
}
