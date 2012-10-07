package com.jiekebo.silkworm.Sprite;

import java.awt.Image;
import java.awt.Rectangle;

import com.jiekebo.silkworm.Assets.Art;
import com.jiekebo.silkworm.Control.Input;


public class Runner extends AnimationSprite {
	static final int    NORMAL = 0, DEAD = 1, AT_DEST = 2;
 	int                 state = NORMAL;
 	double              deltaX, deltaY, destX, destY, lastX, lastY;

	public Runner (Image[] imgs, int framerate, int x, int y) { super(imgs, framerate, x, y); }

 	public void setDest (double x, double y, double speed) {
 		deltaX = ((destX = x) - this.x);
 		deltaY = ((destY = y) - this.y);
 		if (deltaX != 0  ||  deltaY != 0) {
			speed /= Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	 		deltaX *= speed;
	 		deltaY *= speed;
	 	}
 	}
 	
 	public void move(boolean[] keys){
 		if(keys[Input.RIGHT]){
 			//deltaX = ((destX = x+3) - this.x);
 			destX = this.x + 3;
 			deltaX = 3;
 		}
 		if(keys[Input.LEFT]){
 			//deltaX = ((destX = x-3) - this.x);
 			destX = this.x - 3;
 			deltaX = -3;
 		}
 		if(keys[Input.UP]){
 			destY = this.y - 3;
 			deltaY = -3;
 		}
 		if(keys[Input.DOWN]){
 			destY = this.y + 3;
 			deltaY = 3;
 		}
 	}

	public boolean updateSprite () {
		boolean changed = deltaX != 0 || deltaY != 0;
		lastX = x;  lastY = y;
		if (deltaX != 0)
			if (Math.abs(deltaX) >= Math.abs(x - destX)) {
				deltaX = 0;
				x = destX;
			}
			else
				x += deltaX;
		if (deltaY != 0)
			if (Math.abs(deltaY) >= Math.abs(y - destY)) {
				deltaY = 0;
				y = destY;
			}
			else
				y += deltaY;
		if(x > lastX){
			this.setImages(Art.silkf, 1);
			this.setImages(Art.silkff, 1);
		} else if (x < lastX){
			this.setImages(Art.silkb, 1);
		}
		if(x == lastX){
			this.setImages(Art.silk, 1);
		}
		return super.updateSprite() || changed;
	}

	private double adjust (double last, double cur, int dim, int wpos, int wdim) {
		if (cur > last)                                 // moving right or down
			return wpos - (cur + dim / 2.0);            // adjust to left or top of wall
		else if (cur < last)                            // moving left or up
			return (wpos + wdim) - (cur - dim / 2.0);   // adjust to right or bottom of wall
		return Double.MAX_VALUE;                        // moving straight at it, don't adjust
	}

	protected Rectangle slideAlong (Rectangle barrier) {
		double xadjust = adjust(lastX, x, width,  barrier.x, barrier.width );
		double yadjust = adjust(lastY, y, height, barrier.y, barrier.height);
		if (xadjust == Double.MAX_VALUE  &&  yadjust == Double.MAX_VALUE) {
			x = lastX;  y = lastY;
		}
		else if (Math.abs(xadjust) <= Math.abs(yadjust))
			x += xadjust;
		else
			y += yadjust;
		return collisionBox();
	}
}  // class Runner
