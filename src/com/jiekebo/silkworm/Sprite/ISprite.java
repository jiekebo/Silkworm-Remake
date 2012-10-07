package com.jiekebo.silkworm.Sprite;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.jiekebo.silkworm.Control.SpriteEngine;

/**
 * 
 * @author Jacob Salomonsen
 *
 */
public interface ISprite {
	
	/**
	 * The sprite engine passes this method a reference 
	 * to the engine when the sprite is added to the engine, 
	 * and calls it with <it>null</it> when the sprite is 
	 * removed from the engine. The sprite uses the 
	 * reference to call methods in the sprite engine.
	 * @param se Referenced sprite engine
	 */
	void setSpriteEngine(SpriteEngine se);
	
	/**
	 * The sprite engine calls this method periodically to
	 * tell the sprite to update it's state. You move,
	 * animate, and initiate actions in <it>updateSprite()</it>.
	 * @return Returns true if the sprite's collision box is updated, otherwise false
	 */
	boolean updateSprite();
	
	/**
	 * Method for drawing the sprite
	 * @param g Graphics context passed by the sprite engine
	 * @return Returns a Rectangle representing the region drawn to the screen or null if it didn't draw
	 */
	Rectangle drawSprite(Graphics g);
	
	/**
	 * The sprite engine uses the collision box to
	 * determine if the sprite collides with other
	 * sprites. collisionBox() returns null if this
	 * sprite doesn't collide with other sprites
	 * @return Returns a Rectangle containing the sprite's collision box
	 */
	Rectangle collisionBox();
	
	/**
	 * In the event of a collision, the sprite engine passes this method
	 * the object of the sprite that this sprite collided with.
	 * @param obj Sprite with which this sprite collided with
	 * @return Possibly the changed collision box of this sprite
	 */
	Rectangle collideWith(Object obj);
}
