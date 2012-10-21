package de.timweb.evolevel.game;

import java.awt.Graphics;

public class Game {
	public static final Game g = new Game();

	// Über diese Variablen kann abgefragt werden ob Tasten gedrückt wurden
	// Siehe dazu auch EvoLevelCanvas.keyPressed(KeyEvent) (Pfeiltasten)
	public static boolean VK_UP;
	public static boolean VK_DOWN;
	public static boolean VK_LEFT;
	public static boolean VK_RIGHT;

	private Game() {

	}

	/**
	 * In der update(int delta) Methode findet sämtliche Gamelogik statt,
	 * typischerweise werden hier die Welt und alle Entities geupdatet. Alle
	 * Bewegungen und sonstige Berechnungen müssen mit dem delta multiplizert
	 * werden.
	 */
	public void update(int delta) {

		World.w.update(delta);
	}

	/**
	 * In der render(Grahpics g) Methode wird das Spiel gezeichnet, typischweise
	 * werden erst Hintergrundbilder der Welt dargestellt und einzelne Sprites
	 * (Images) von Entities drauf gezeichnet
	 */
	public void render(Graphics g) {

		World.w.render(g);
	}

}
