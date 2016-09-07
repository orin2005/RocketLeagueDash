package com.orinsoftware.gibbsfangame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Gibbs extends RLDSprite {
	
	private static final double ACCELERATION = 0.1;
	private static final double MAX_SPEED = 1;
	private static final double GRAVITY = 0.169;
	
	private boolean falling;

	
	
	public Gibbs(double x, double y) {
		super(null, x, y, 0, 0, 50, 30);
		falling = true;
	}
	
	@Override
	public void update(double delta)
	{
		super.update(delta);
		applyAcceleration();
		if(falling)
		{
			applyGravity();
		}
		else
		{
			this.setVelocityY(0);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		Paint p = gc.getFill();
		gc.setFill(Color.RED);
		gc.fillRect(positionX, positionY, width, height);
		gc.setFill(p);
		
	}
	
	private void applyAcceleration()
	{
		this.setVelocityX(this.getVelocityX() + ACCELERATION);
	}
	
	@Override
	public void setVelocityX( double velocityX)
	{
		super.setVelocityX(velocityX);
		if(this.velocityX > MAX_SPEED)
		{
			this.velocityX = MAX_SPEED;
		}
	}
	
	public void applyGravity()
	{
		this.setVelocityY(this.getVelocityY() + GRAVITY);
	}
	
	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}
	
	public boolean isFalling()
	{
		return falling;
	}
	
	public void jump()
	{
		if( !falling )
		{
			setVelocityY(-5);
		}
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder().append("Gibbs: \n").append("\t")
				.append("xcoord:"+getPositionX()+"\n").append("\t")
				.append("ycoord:"+getPositionY()+"\n").append("\t")
				.toString();
	}

}
