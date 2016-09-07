package com.orinsoftware.gibbsfangame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Gibbs extends RLDSprite {
	
	private static final double ACCELERATION = 0.3;
	private static final double MAX_SPEED = 3;
	private static final double MAX_FALLING_SPEED = 5;
	private static final double GRAVITY = 0.169;
	
	private boolean falling;
	private boolean doubleJump;
	
	
	public Gibbs(double x, double y) {
		super(null, x, y, 0, 0, 50, 30);
		falling = true;
		doubleJump = true;
	}
	
	@Override
	public void update(double delta)
	{
		super.update(delta);
		
		GameManager.getInstance().getCamera().update();
		
		applyAcceleration();
		
		if( falling )
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
		gc.setFill( Color.RED );
		gc.fillRect( positionX, positionY, width, height );
		gc.setFill( p );
		
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
	
	@Override
	public void setVelocityY( double velocityY )
	{
		super.setVelocityY(velocityY);
		if( this.velocityY > MAX_FALLING_SPEED )
		{
			this.velocityY = MAX_FALLING_SPEED;
		}
		
		if( this.velocityY < -MAX_FALLING_SPEED )
		{
			this.velocityY = -MAX_FALLING_SPEED;
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
	
	public void setDoubleJump(boolean doubleJump)
	{
		this.doubleJump = doubleJump;
	}
	
	public boolean canDoubleJump()
	{
		return doubleJump;
	}
	
	public void jump()
	{
		if( doubleJump )
		{
			System.out.println("doubleJump true");
			if( !falling )
			{
				doJump(true);
			}
			else
			{
				doJump(false);
				doubleJump = false;
			}
			
		}
	}
	
	public void doJump( boolean firstJump )
	{
		falling = true;
		if( firstJump )
		{
			this.setVelocityY(-20);
		}
		else
		{
			this.setVelocityY(-20);
			//need to check for what direction we are pressing
		}
	}
	
	public void consume( BoostPeanut bp )
	{
		//TODO implement
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder().append("Gibbs: \n").append("\t")
				.append("xcoord:"+getPositionX()+"\n").append("\t")
				.append("ycoord:"+getPositionY()+"\n").append("\t")
				.append("xvel:"+getVelocityX()+"\n").append("\t")
				.append("yvel:"+getVelocityY()+"\n").append("\t")
				.append("ycoord:"+getPositionY()+"\n").append("\t")
				.append("falling:"+falling+"\n").append("\t")
				.append("doubleJump:"+doubleJump+"\n").append("\t")
				.toString();
	}

}
