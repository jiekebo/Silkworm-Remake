package com.jiekebo.silkworm.Sprite;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class ImageSprite extends SpriteObject {

	protected Image image;
	
	/**
	 * Creates a sprite from a loaded image
	 * @param image Image to be loaded
	 * @param x Horizontal center of image
	 * @param y Vertical center of image
	 */
	public ImageSprite(Image image, double x, double y) {
		super(x, y, 0, 0);
		setImage(image);
	}
	
	/**
	 * The method setImage sets the width and height 
	 * of the sprite to be the same as the width 
	 * and height of the image
	 * @param image Image to be loaded
	 */
	public void setImage(Image image) {
		this.image = image;
		/*width = image.getWidth(null);
		height = image.getHeight(null);*/
	}
	
	@Override
	public Rectangle drawSprite(Graphics g) {
		width = image.getWidth(null);
		height = image.getHeight(null);
		g.drawImage(image, (int) (x-width/2.0), (int) (y-height/2.0), null);
		return collisionBox();
	}
}
