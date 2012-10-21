package de.timweb.evolevel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.timweb.evolevel.game.EvoLevelCanvas;

public class EvoLevelMain {
	
	public static void main(String[] args) {
		EvoLevelCanvas evocanvas = new EvoLevelCanvas(1024, 600, 10);
		JFrame frame = new JFrame("Evolevel");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(evocanvas);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		evocanvas.start();
	}
	
}
