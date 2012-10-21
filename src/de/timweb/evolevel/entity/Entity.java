package de.timweb.evolevel.entity;

import java.awt.Graphics;

import de.timweb.evolevel.util.Vector2d;

public abstract class Entity {
	// Wenn das Entity nicht mehr lebt, kann es gel�scht werden
	protected boolean isAlive = true;

	// Die Position (X,Y) des Entities
	protected Vector2d pos;

	public Entity(Vector2d pos) {
		this.pos = pos.copy();
	}

	/**
	 * Bewegt ein Entity um die angegeben Werte
	 * 
	 * @param x
	 * @param y
	 */
	public void move(double x, double y) {
		pos.add(x, y);
	}

	public Vector2d getPos() {
		return pos;
	}

	public void kill() {
		onKilled();
		isAlive = false;
	}

	/**
	 * Wird aufgerufen, wenn das Entity gekillt wird. Die Methode kann bei
	 * Bedarf �berschrieben werden um Aktionen beim Tod des Entities
	 * auszuf�hren, z.B. eine Explosion, ein Sound oder �hnliches abzuspielen
	 */
	protected void onKilled() {
	}

	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Muss �berschrieben werden, hier finden die jegliche Berechnungen,
	 * Bewegungen... statt.
	 * 
	 * @param delta
	 *            Die Zeit seit dem letzten Update (siehe FPS)
	 */
	public abstract void update(int delta);

	/**
	 * Muss �berschrieben werden, hier wird das Entity und alle zugeh�rigen
	 * visuellen Dinge gezeichnet. Typischweise hat ein Entity ein
	 * BufferedImage, welches gezeichnet wird
	 * 
	 * @param g
	 *            Das Graphic Object, auf das gezeichnet wird
	 */
	public abstract void render(Graphics g);
}
