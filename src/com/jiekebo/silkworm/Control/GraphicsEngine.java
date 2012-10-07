package com.jiekebo.silkworm.Control;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.jiekebo.silkworm.Assets.Art;
import com.jiekebo.silkworm.Sprite.AnimationSprite;
import com.jiekebo.silkworm.Sprite.ImageSprite;
import com.jiekebo.silkworm.Sprite.Runner;


public class GraphicsEngine extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SpriteEngine se;
	private int width;
	private int height;
	protected Image offscreenImage;
	protected Graphics offscr;
//	private Thread updater;
	protected long timer, maxFrameRate = 25L;
	private Runner coptertwo;
	private Input input = new Input();
	private GraphicsConfiguration graphicsConfiguration;

	public GraphicsEngine(int width, int height) {
		this.width = width;
		this.height = height;
		offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		offscreenImage.getScaledInstance(640, 480, Image.SCALE_DEFAULT);
		this.setPreferredSize(new Dimension(width, height));
		this.setPreferredSize(super.getPreferredSize());
		this.addMouseListener(input);
		this.addKeyListener(input);
		startGame();
	}

	public synchronized void startGame() {
//		if (updater != null)
//			try {
//				updater.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		graphicsConfiguration = getGraphicsConfiguration();
//		paintOffscreenImage();
		se = new SpriteEngine(offscreenImage, 2);
		Image stableHelicopter = Art.stableHelicopter;
		Image[] helicopter = Art.helicopter;
		
		AnimationSprite copter = new AnimationSprite(helicopter, 10, 150, 100);
		ImageSprite copterthree = new ImageSprite(stableHelicopter, 200, 50);
		AnimationSprite copterfour = new AnimationSprite(helicopter, 1, 100, 50);
		coptertwo = new Runner(Art.silk, 1, 100, 50);
		se.add(copter, 1);
		se.add(coptertwo, 1);
		se.add(copterthree, 1);
		se.add(copterfour, 1);
//		updater = new Thread(this);
//		updater.start();
	}

	private void tick() {
		/*
		 * if (input.mousePressed) coptertwo.setDest(input.xMouse, input.yMouse, 3.0);
		 */
		coptertwo.move(input.keys);
	}

	@Override
	public void run() {
		while(true) {
			updateImage();
			tick();
			se.update();
			se.draw(offscr);
		}
	}

	public synchronized void updateImage() {
		repaint();
		try {
			wait();
		} catch (InterruptedException e) {
		}
		long t = System.currentTimeMillis();
		if ((timer -= t - (1000L / maxFrameRate)) > 0)
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
			}
		timer = System.currentTimeMillis();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public synchronized void paint(Graphics g) {
		g.drawImage(offscreenImage, 0, 0, this);
		paintOffscreenImage();
		notifyAll();
	}

	protected void paintOffscreenImage() {
		offscr = offscreenImage.getGraphics();
		offscr.setColor(getBackground());
		offscr.fillRect(0, 0, width, height);
	}

}
