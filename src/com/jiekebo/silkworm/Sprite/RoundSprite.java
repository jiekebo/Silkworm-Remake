package com.jiekebo.silkworm.Sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RoundSprite extends SpriteObject {

	protected Color color;
	
	/**
	 * Creates a sprite consisting of a circle.
	 * @param x Horizontal center of circle
	 * @param y Vertical center of circle
	 * @param w Width of circle
	 * @param h Height of circle
	 * @param c Color of circle
	 */
	public RoundSprite(double x, double y, int w, int h, Color c) {
		super(x, y, w, h);
		color = c;
	}
	
	@Override
	public Rectangle drawSprite(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x-width/2.0),
				   (int) (y-height/2.0), width, height);
		return collisionBox();
	}

}
