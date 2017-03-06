package com.orinsoftware.gibbsfangame;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class Platform extends RLDSprite{

	public static final double WIDTH = 500;
	
	public static final double HEIGHT = 20;
	
	public static int id;
	
	private int pid;
	
	static {
		id = 0;
	}
	
	public Platform(double x, double y) {
		super(null, x, y, 0, 0, WIDTH, HEIGHT);
		pid = id++;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Paint p = gc.getFill();
		gc.setFill(Color.BLACK);
		gc.fillRect(positionX, positionY, width, height);
		gc.setFill(p);
		
	}
}
