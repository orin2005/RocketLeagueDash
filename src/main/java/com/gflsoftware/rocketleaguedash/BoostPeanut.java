package com.orinsoftware.gibbsfangame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BoostPeanut extends RLDSprite 
{
	
	private static double WIDTH = 20;
	private static double HEIGHT = 20;
	
	private boolean pickedUp;

	public BoostPeanut(double x, double y) {
		super(null,x, y, 0, 0,WIDTH, HEIGHT );
		pickedUp = false;
	}
	
	public boolean isPickedUp()
	{
		return pickedUp;
	}
	
	public void setPickedUp(boolean pickedUp)
	{
		this.pickedUp = pickedUp;
	}

	@Override
	public void render(GraphicsContext gc) {
		Paint p = gc.getFill();
		gc.setFill(Color.YELLOW);
		gc.fillOval( positionX, positionY, width, height );
		gc.setFill(p);
		
	}
	
	
	

}
