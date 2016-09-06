package com.orinsoftware.gibbsfangame;

import com.orinsoftware.gibbsfangame.KeyboardManager.Directions;

public class Gibbs {

	private double x;
	private double y;
	private double width;
	private double height;
	
	private double vx;
	private double vy;
	
	private boolean inTheAir;
	private boolean doubleJump;
	
	private KeyboardManager keyboardManager;
	
	private static final double MAX_ACCELERATION = 5;
	private static final double MAX_GROUND_SPEED = 6;
	private static final double ACCELERATION = 0.00000000001;
	
	public Gibbs() {
		x = 0;
		y = 300;
		vx = 0;
		vy = 0;
		inTheAir = false;
		doubleJump = true;
		width = 50;
		height = 20;
		keyboardManager = KeyboardManager.getInstance();
	}
	
	public void move()
	{
		setVX( getVX() + ACCELERATION );
		x += vx;
		y += vy;
		
		if(inTheAir)
		{
			gravity();
		}
	}
	
	public boolean isInTheAir()
	{
		return inTheAir;
	}
	
	public void setInTheAir( boolean inTheAir )
	{
		this.inTheAir = inTheAir;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getVX()
	{
		return vx;
	}
	
	public void setVX(double vx)
	{
		this.vx += vx;
		if( this.vx > MAX_ACCELERATION )
		{
			this.vx = MAX_ACCELERATION;
		}
	}
	
	public double getVY()
	{
		return vy;
	}
	
	public void setVY(double vy)
	{
		this.vy = vy;
	}
	
	public void jump()
	{ 
		if( inTheAir && doubleJump )
		{
			doubleJump();
		}
		else if( !inTheAir && doubleJump )
		{
			inTheAir = true;
			vy = 0;
			vy -= 5;
		}
	}
	
	private void doubleJump()
	{
		System.out.println("doubleJump called");
		if( keyboardManager.getDirection() == Directions.NONE )
		{
			doubleJump = false;
			vy = 0;
			vy -= 5;
		}
		else
		{
			doJump( keyboardManager.getDirection() );
		}
	}
	
	private void gravity()
	{
		vy += 0.163;
	}
	
	public void checkCollision( Platform platform )
	{
		if( y + height >= platform.getY() 
				&& y <= platform.getY() + platform.getHeight() 
				&& x + width >= platform.getX() 
				&& x <= platform.getX() + platform.getWidth())
		{
			inTheAir = false;
			doubleJump = true;
			this.y = platform.getY() - this.getHeight();
		}
	}
	
	public KeyboardManager getKeyboardManager()
	{
		return keyboardManager;
	}
	
	private void doJump( Directions direction )
	{
		doubleJump = false;
		switch(direction)
		{
		case RIGHT:
			setVY(0);
			setVX(getVX()+5);
			break;
		case LEFT:
			break;
		case UP:
			break;
		case DOWN:
			break;
		}
	}

}
