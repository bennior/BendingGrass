package updating;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseMotionListener {
		
	public static int xPos;
	public static int yPos;
	private static boolean dragged;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		dragged = true;
		
		xPos = e.getX();
		yPos = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dragged = false;
	}
	
	public static boolean getDragged() {
		return dragged;
	}
}
