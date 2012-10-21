package de.timweb.evolevel.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import de.timweb.evolevel.util.ImageLoader;

/**
 * Das EvoLevelCanvas ist eine Unterklasse von java.awt.Canvas und stellt die
 * <b>Leinwand</b> dar, die sich in dem Fenster befindet und dieses komplett
 * ausfüllt. <br/>
 * <br/>
 * Außerdem enthält die Klasse die Grundbestandteile des Spieles, nämlich die
 * Endlosschleife, welche periodisch nacheinander erst die <b> update(int delta)
 * </b>, dann die <b> render(Graphics g) </b> Methode aufruft. <br/>
 * <br/>
 * Weiterhin ist sie aus bequemlichkeitsgründen auch für die Benutzerinteraktion
 * mit der Tastatur verantwortlich. Das Interface KeyListener ermöglicht die
 * Abfrage, ob eine bestimmte Taste gedrückt ist. Um einen einfachen und
 * schnellen Zugriff auf diese Funktion zu haben wird das mit Hilfe der
 * Variablen VK_TASTE in der Klasse Game gesetzt.
 */
public class EvoLevelCanvas extends Canvas implements Runnable, KeyListener {
	public static int WIDTH;
	public static int HEIGHT;

	public static long currentFPS;
	public static final int FPS_TARGET = 120;
	public static final int DELTA_TARGET = 1000 / FPS_TARGET;

	public EvoLevelCanvas(int width, int height, int border) {
		WIDTH = width;
		HEIGHT = height;

		Dimension dim = new Dimension(width - border, height - border);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.addKeyListener(this);
	}

	public void start() {
		Thread t = new Thread(this);
		ImageLoader.init();
		t.start();
	}

	/**
	* Die Methode in der die Endlosschleife des Spieles abläuft
	* In der Endloschleife wird erst die update(int delta) Methode, dann die render(Graphics g) periodisch aufgerufen
	* Auch findet hier die FPS Regulierung statt.
	*
	*/
	@Override
	public void run() {
		long delta = 0;

		while (true) {
			long start = System.currentTimeMillis();

			update((int) delta);
			BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				continue;
			}
			render(bs.getDrawGraphics());

			if (bs != null)
				bs.show();

			long timepassed = System.currentTimeMillis() - start;
			if (timepassed < DELTA_TARGET) {
				try {
					Thread.sleep(DELTA_TARGET - timepassed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			delta = System.currentTimeMillis() - start;
			if (delta != 0)
				currentFPS = 1000 / delta;
		}

	}

	/**
	* die gesamte Spiellogik findet hier statt, jegliche Berechnung, Bewegungen und Effekte werden mit dem delta verrechnet
	*/
	private void update(int delta) {
		Game.g.update(delta);
	}

	/**
	* hier wird das Spiel auf das Canvas gezeichnet, alle dargestellten Entities, Hintergrundbilder und grafische Effekte werden hier dargestellt
	* es finden KEINE Berechnungen oder überhaupt veränderungen von Entities statt!
	*/
	private void render(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);

		Game.g.render(g);

		g.setColor(Color.red);
		g.setFont(getFont());
		g.drawString("FPS: " + currentFPS, WIDTH - 80, 20);

		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			Game.VK_UP = true;
			break;
		case KeyEvent.VK_DOWN:
			Game.VK_DOWN = true;
			break;
		case KeyEvent.VK_LEFT:
			Game.VK_LEFT = true;
			break;
		case KeyEvent.VK_RIGHT:
			Game.VK_RIGHT = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			Game.VK_UP = false;
			break;
		case KeyEvent.VK_DOWN:
			Game.VK_DOWN = false;
			break;
		case KeyEvent.VK_LEFT:
			Game.VK_LEFT = false;
			break;
		case KeyEvent.VK_RIGHT:
			Game.VK_RIGHT = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
