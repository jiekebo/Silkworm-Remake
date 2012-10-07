package com.jiekebo.silkworm.Control;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Vector;

import com.jiekebo.silkworm.Sprite.ISprite;

/**
 * 
 * @author Jacob Salomonsen
 * 
 */
class SpriteBorder {
}

public class SpriteEngine extends Observable {
	public static final SpriteBorder NORTH = new SpriteBorder();
	public static final SpriteBorder SOUTH = new SpriteBorder();
	public static final SpriteBorder EAST = new SpriteBorder();
	public static final SpriteBorder WEST = new SpriteBorder();

	private Vector<ISprite>[] sprites, addPending;
	private Vector<ISprite> removePending = new Vector<ISprite>();
	private int width, height;
	private boolean updating;
	
	private Image       bgImage;
	private Rectangle[] erase = new Rectangle[0];

	public SpriteEngine(Image background, int depth) {
		this.width = background.getWidth(null);
		this.height = background.getHeight(null);
		sprites = new Vector[depth];
		addPending = new Vector[depth];
		while (--depth >= 0) {
			sprites[depth] = new Vector();
			addPending[depth] = new Vector();
		}
		bgImage = background;
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	/**
	 * Adjusts r so that it is contained within the play field
	 * 
	 * @param r
	 *            Play field rectangle
	 */
	public final void placeInBounds(Rectangle r) {
		if (r != null) {
			int w = Math.min(width, r.width);
			int h = Math.min(height, r.height);
			int x = Math.min(Math.max(0, r.x), width - w);
			int y = Math.min(Math.max(0, r.y), height - h);
			r.setBounds(x, y, w, h); // instead of reshape
		}
	}

	/**
	 * Adds a sprite
	 * 
	 * @param sprite
	 *            Sprite to be added
	 * @param depth
	 *            Layer the sprite is added to
	 */
	public void add(ISprite sprite, int depth) {
		depth = Math.min(Math.max(0, depth), sprites.length - 1);
		if (updating) { // Add sprite later
			if (!addPending[depth].contains(sprite))
				addPending[depth].addElement(sprite);
		} else { // Add sprite now
			sprites[depth].addElement(sprite);
			sprite.setSpriteEngine(this);
		}
	}

	/**
	 * Remove sprite
	 * 
	 * @param sprite
	 *            Sprite to be removed
	 */
	public void remove(ISprite sprite) {
		if (updating) {
			if (!removePending.contains(sprite))
				removePending.addElement(sprite);
		} else {
			for (int depth = sprites.length; --depth >= 0;) { // TODO: make more
																// effective
				if (sprites[depth].contains(sprite)) {
					sprites[depth].removeElement(sprite);
					sprite.setSpriteEngine(null);
					return;
				}
			}
			throw new NoSuchElementException();
		}
	}

	/**
	 * Draws all the sprites in the play field to the graphics context g from
	 * the deepest (highest numbered)sprite layer, to the closest (lowest
	 * numbered) so that deeper sprites appear to be behind closer sprites.
	 * 
	 * @param g
	 *            Graphics context to draw to
	 * @return Returns a Rectangle[] array containing all the regions in the
	 *         play field where sprites draw themselves
	 */
	public Rectangle[] draw(Graphics g) {
		
		for (int ii = erase.length;  --ii >= 0; ) {
			Rectangle r = erase[ii];
			Graphics clip = g.create(r.x, r.y, r.width, r.height);
			clip.drawImage(bgImage, -r.x, -r.y, null);
			clip.dispose();
		}
		
		Vector<Rectangle> drawRects = new Vector<Rectangle>();
		for (int depth = sprites.length; --depth >= 0;) {
			synchronized (sprites[depth]) {
				Enumeration<ISprite> spritesEnum = sprites[depth].elements();
				while (spritesEnum.hasMoreElements()) {
					Rectangle r = spritesEnum.nextElement().drawSprite(g);
					if (r != null)
						drawRects.addElement(r);
				}
			}
		}
		Rectangle[] result = new Rectangle[drawRects.size()];
		drawRects.copyInto((Object[]) result);
		return result;
	}

	/**
	 * Calls the updateSprite() method for all the sprites in the engine and
	 * then detects and resolves any resulting collisions
	 */
	public synchronized void update() {
		Vector<ISprite> moved = new Vector<ISprite>();
		try {
			updating = true;
			// call update for all the sprites
			for (int depth = sprites.length; --depth >= 0;) {
				Enumeration<ISprite> spritesEnum = sprites[depth].elements();
				while (spritesEnum.hasMoreElements()) {
					ISprite s = spritesEnum.nextElement();
					if (s.updateSprite())
						moved.addElement(s); // collision rect. changed
				}
			}
			// Check for overlapping collision boxes and resolve collisions
			Enumeration<ISprite> spritesEnum = moved.elements();
			while (spritesEnum.hasMoreElements()) {
				ISprite movedSprite = spritesEnum.nextElement();
				Rectangle cb = movedSprite.collisionBox();
				if (cb != null && cb.x < 0)
					cb = movedSprite.collideWith(WEST);
				if (cb != null && cb.x + cb.width > width)
					cb = movedSprite.collideWith(EAST);
				if (cb != null && cb.y < 0)
					cb = movedSprite.collideWith(NORTH);
				if (cb != null && cb.y + cb.height > height)
					cb = movedSprite.collideWith(SOUTH);
				for (int depth = 0; cb != null && depth < sprites.length; depth++) {
					Enumeration<ISprite> spritesEnum2 = sprites[depth].elements();
					while (spritesEnum2.hasMoreElements()) {
						ISprite s = spritesEnum2.nextElement();
						if (s != movedSprite) {
							Rectangle cb2 = s.collisionBox();
							if (cb2 != null && cb2.intersects(cb)) {
								cb = movedSprite.collideWith(s);
								s.collideWith(movedSprite);
							}
						}
					}
				}
			}
		} finally {
			updating = false;
			// Remove pending sprites
			for (int index = removePending.size(); --index >= 0;) {
				try {
					remove(removePending.elementAt(index));
				} catch (NoSuchElementException e) {

				}
			}
			removePending.removeAllElements();
			// Add pending sprites
			for (int depth = sprites.length; --depth >= 0;) {
				Vector<ISprite> ap = addPending[depth];
				for (int index = ap.size(); --index >= 1;)
					add(ap.elementAt(index), depth);
				ap.removeAllElements();
			}
		}
	}
}
