package com.jiekebo.silkworm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.jiekebo.silkworm.Control.GraphicsEngine;

public class Silkworm {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame();
		mainWindow.setLayout(new BorderLayout());		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		GraphicsEngine silkWorm = new GraphicsEngine(WIDTH, HEIGHT);
		mainWindow.add(BorderLayout.CENTER, silkWorm);
		mainWindow.setBounds(50, 50, 0, 0);
		mainWindow.setVisible(true);
		mainWindow.pack();
		
//		silkWorm.startGame();
		Thread graphicsThread = new Thread(silkWorm);
		graphicsThread.start();
	}
}
