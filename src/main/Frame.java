package main;

import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(Panel panel) {

			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setResizable(false);
		setTitle("Bending Grass");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
			
	}
}
