package com.jiekebo.silkworm.Control;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, FocusListener, MouseListener,
		MouseMotionListener {

	public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int TURN_LEFT = 4;
    public static final int TURN_RIGHT = 5;

    public boolean[] keys = new boolean[10];
    public boolean mousePressed;
    public int xMouse, yMouse;
    public int click = 0;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mousePressed = true;
		xMouse = arg0.getX();  yMouse = arg0.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mousePressed = true;
		xMouse = arg0.getX();  yMouse = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mousePressed = false;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		toggleKey(arg0.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		toggleKey(arg0.getKeyCode(), false);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
    private void toggleKey(int keyCode, boolean b) {
        if (keyCode == KeyEvent.VK_W) keys[UP] = b;
        if (keyCode == KeyEvent.VK_S) keys[DOWN] = b;
        if (keyCode == KeyEvent.VK_A) keys[LEFT] = b;
        if (keyCode == KeyEvent.VK_D) keys[RIGHT] = b;
        if (keyCode == KeyEvent.VK_Q) keys[TURN_LEFT] = b;
        if (keyCode == KeyEvent.VK_E) keys[TURN_RIGHT] = b;

        if (keyCode == KeyEvent.VK_Y) keys[UP] = b;
        if (keyCode == KeyEvent.VK_H) keys[DOWN] = b;
        if (keyCode == KeyEvent.VK_G) keys[LEFT] = b;
        if (keyCode == KeyEvent.VK_J) keys[RIGHT] = b;
        if (keyCode == KeyEvent.VK_T) keys[TURN_LEFT] = b;
        if (keyCode == KeyEvent.VK_U) keys[TURN_RIGHT] = b;

        if (keyCode == KeyEvent.VK_UP) keys[UP] = b;
        if (keyCode == KeyEvent.VK_DOWN) keys[DOWN] = b;
        if (keyCode == KeyEvent.VK_LEFT) keys[LEFT] = b;
        if (keyCode == KeyEvent.VK_RIGHT) keys[RIGHT] = b;
        if (keyCode == KeyEvent.VK_HOME) keys[TURN_LEFT] = b;
        if (keyCode == KeyEvent.VK_PAGE_UP) keys[TURN_RIGHT] = b;


        if (keyCode == KeyEvent.VK_NUMPAD8) keys[UP] = b;
        if (keyCode == KeyEvent.VK_NUMPAD5) keys[DOWN] = b;
        if (keyCode == KeyEvent.VK_NUMPAD4) keys[LEFT] = b;
        if (keyCode == KeyEvent.VK_NUMPAD6) keys[RIGHT] = b;
        if (keyCode == KeyEvent.VK_NUMPAD7) keys[TURN_LEFT] = b;
        if (keyCode == KeyEvent.VK_NUMPAD9) keys[TURN_RIGHT] = b;
    }

}
