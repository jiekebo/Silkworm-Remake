package com.jiekebo.silkworm.Sprite;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.jiekebo.silkworm.Control.SpriteEngine;

/**
 * 
 * @author Jacob Salomonsen
 *
 */
public class SpriteObject implements ISprite {

	protected double 			x,y;
	protected int				width, height;
	protected SpriteEngine		spriteEngine;
	protected double			deltaX, deltaY;
	
	/**
	 * Create a new sprite
	 * @param x Horizontal center of sprite
	 * @param y Vertical center of sprite
	 * @param w Sprite width
	 * @param h Sprite height
	 */
	public SpriteObject(double x, double y, int w, int h) {
		this.x = x; this.y = y; width = w; height = h;
	}
	
	@Override
	public void setSpriteEngine(SpriteEngine se) {
		spriteEngine = se;
	}

	@Override
	public boolean updateSprite() {
		x += deltaX;
		y += deltaY;
		return true;
	}

	@Override
	public Rectangle drawSprite(Graphics g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle collisionBox() {
		int x0 = (int)(x - width / 2.0);
		int y0 = (int)(y - height / 2.0);
		return new Rectangle(x0, y0, width, height);
	}

	@Override
	public Rectangle collideWith(Object obj) {
		return collisionBox();
	}

}
