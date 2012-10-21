package de.timweb.evolevel;

import java.applet.Applet;

import de.timweb.evolevel.game.EvoLevelCanvas;


public class EvoLevelApplet extends Applet{
	@Override
	public void init() {
		super.init();
		EvoLevelCanvas game = new EvoLevelCanvas(getWidth(),getHeight(),0);
		add(game);
		game.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		System.exit(0);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.exit(0);
	}
}
