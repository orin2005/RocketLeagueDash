package com.orinsoftware.gibbsfangame;

import com.orinsoftware.gibbsfangame.fxui.GameScene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Gibbs extends RLDSprite {
	
	private static final double ACCELERATION = 10;
	private static final double DECELERATION = 1;
	private static final double MAX_SPEED = 800;
	private static final double MAX_GROUND_SPEED = 400;
	private static final double MAX_FALLING_SPEED = 1000;
	private static final double GRAVITY = 25;
	private static final double FLIP_MOMENTUM = 200;
	
	private static final double JUMP_FORCE = -600;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 30;
	
	private double rotationAngle;
	
	private boolean falling;
	private boolean flipping;
	private boolean doubleJump;
	
	
	public Gibbs(double x, double y) {
		super(null, x, y, 0, 0, WIDTH, HEIGHT);
		falling = true;
		doubleJump = true;
		flipping = false;
		rotationAngle = 0;
	}
	
	@Override
	public void update(double delta)
	{
		super.update(delta);
		if( getVelocityX() < MAX_GROUND_SPEED )
			applyAcceleration();
		else
			decelerate();
		
		
		if( falling && !flipping )
		{
			applyGravity();
		}
		else
		{
			this.setVelocityY(0);
		}
		
		
		
		this.positionX = GameScene.WIDTH/2-width/2;
	}

	@Override
	public void render(GraphicsContext gc) {
		Paint p = gc.getFill();
		gc.setFill( Color.BLUE );
		
		gc.translate(positionX,positionY);
		
		if( flipping )
		{
			setVelocityX(getVelocityX()+FLIP_MOMENTUM);
			System.out.println("flipping");
			rotationAngle += 20;
			gc.translate(width/2, height/2);
			gc.rotate(rotationAngle);
			//TODO replace with image.
			gc.fillRect( -width/2, -height/2, width, height );
			gc.rotate(-rotationAngle);
			gc.translate(-(width/2), -(height/2));
			
			if(rotationAngle == 360)
			{
				rotationAngle = 0;
				flipping = false;
				
			}
		}
		else
		{
			gc.fillRect(0,  0,  width,  height);
		}
			
		gc.translate(-positionX,-positionY);
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
	
	public boolean isFlipping()
	{
		return flipping;
	}
	
	public void setFlipping(boolean flipping)
	{
		this.flipping = flipping;
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
			this.setVelocityY(JUMP_FORCE);
		}
		else
		{
			System.out.println(KeyboardManager.getInstance().getDirection());
			switch( KeyboardManager.getInstance().getDirection() )
			{
			case NONE:
				this.setVelocityY(JUMP_FORCE);
				break;
			case RIGHT:
				doFlip();
				break;
			}
			
			//need to check for what direction we are pressing
		}
	}
	
	private void doFlip()
	{
		flipping = true;
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
	
	public boolean notEquals(RLDSprite obj)
	{
		return !(this == obj);
	}
	
	private void decelerate()
	{
		this.setVelocityX(getVelocityX() - DECELERATION); 
	}

}
