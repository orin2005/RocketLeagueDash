package com.orinsoftware.gibbsfangame;

import java.awt.Color;
import java.awt.Graphics;

public class BoostPeanut {
	
	private double x;
	private double y;
	private double width;
	private double height;
	
	private static double WIDTH = 5;
	private static double HEIGHT = 5;

	public BoostPeanut(int x, int y) {
		this.x = x;
		this.y = y;
		width = WIDTH;
		height = HEIGHT;
	}
	
	public void draw(Graphics g)
	{
		Color color = g.getColor();
		
		g.setColor(Color.yellow);
		g.drawOval((int)x, (int)y, (int)width, (int)height);
		
		g.setColor(color);
	}

}
