package com.jiekebo.silkworm.Assets;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

public class Art {
    
	
	public MediaTracker mt = new MediaTracker(null);
	public static Image[] silk = Art.loadSeries("silk",".png", 2);
	public static Image[] silkf = Art.loadSeries("silkf", ".png", 2);
	public static Image[] silkff = Art.loadSeries("silkff", ".png", 2);
	public static Image[] silkb = Art.loadSeries("silkb", ".png", 2);
	public static Image[] helicopter = Art.loadSeries("copter", ".gif", 2);
	public static Image stableHelicopter = Art.load("copter0.gif");
	public static Image bg = Art.load("bg1.png");
	
    private static Image[] loadSeries(String name, String fileType, int frames){
    	Image[] result = new Image[frames];
    	for(int i = 0; i < frames; i++){
    		result[i] = load(name + i + fileType);
    	}
		return result;
    }
    
    private static Image load(String name){
    	Image img = Toolkit.getDefaultToolkit().getImage("res/" + name);
    	return img;
    }
}