package com.jiekebo.silkworm.Sprite;

import java.awt.Image;

public class AnimationSprite extends ImageSprite {

	protected int		curImage, delay, framerate;
	protected Image[]	images;
	
	/**
	 * Create an animated sprite
	 * @param images Array of images
	 * @param framerate Amount of frames between animation
	 * @param x Horizontal center of the sprite
	 * @param y Vertical center of the sprite
	 */
	public AnimationSprite(Image[] images, int framerate, double x, double y) {
		super(images[0],x,y);
		setImages(images, framerate);
		curImage = 0;
	}

	/**
	 * Sets the sprite's image. Used by updateSprite to perform the animation
	 * @param imageIndex Index of the image in the image array to display
	 */
	public void setImage(int imageIndex) {
		curImage = imageIndex;
		delay = framerate;
		setImage(images[imageIndex]);
	}
	
	/**
	 * Sets the images for the sprite animation
	 * @param images Array containing the images for the animation
	 * @param framerate Amount of frames between animation
	 */
	public void setImages(Image[] images, int framerate) {
		this.images = images;
		this.framerate = framerate;
	}
	
	@Override
	public boolean updateSprite() {
		int w = width, h = height;
		if(--delay <= 0)
			setImage((curImage + 1) % images.length); // loop through images in array
		return w != width || h != height;
	}

}
