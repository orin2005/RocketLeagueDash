package com.orinsoftware.gibbsfangame;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class Platform extends RLDSprite{

	public static final double WIDTH = 300;
	
	public static final double HEIGHT = 20;
	
	
	public Platform(double x, double y) {
		super(null, x, y, 0, 0, WIDTH, HEIGHT);
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
