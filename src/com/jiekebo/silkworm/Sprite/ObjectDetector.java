package com.jiekebo.silkworm.Sprite;

import java.awt.Rectangle;

/**
 * 
 * @author Jacob Salomonsen
 *
 */
public class ObjectDetector extends SpriteObject {

	private Object target;
	
	/**
	 * The objectDetetector() detects when a specific object
	 * has collided with it and then notifies the sprite engine's observers
	 * @param x Horizontal center of sprite
	 * @param y Vertical center of sprite
	 * @param w Sprite width
	 * @param h Sprite height
	 * @param target The intended object to collide with
	 */
	public ObjectDetector(double x, double y, int w, int h, Object target) {
		super(x, y, w, h);
		this.target = target;
	}
	
	@Override
	public Rectangle collideWith(Object obj) {
		if(obj == target)
			spriteEngine.notifyObservers(this);
		return collisionBox();
	}

}
