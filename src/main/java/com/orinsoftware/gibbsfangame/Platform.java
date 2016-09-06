package com.orinsoftware.gibbsfangame;

import java.awt.Color;
import java.awt.Graphics;

public final class Platform {

	private int x;
	private int y;
	
	private int width = 300;
	private int height = 10;
	
	
	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
}
