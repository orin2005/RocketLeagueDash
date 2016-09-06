package com.orinsoftware.gibbsfangame;

public class Gibbs {

	private double x;
	private double y;
	private double width;
	private double height;
	
	private double vx;
	private double vy;
	
	private boolean inTheAir;
	private boolean doubleJump;
	
	private long lastTick;
	
	public Gibbs() {
		x = 0;
		y = 300;
		vx = 4;
		vy = 0;
		inTheAir = false;
		doubleJump = true;
		width = 50;
		height = 20;
		lastTick = System.currentTimeMillis();
	}
	
	public void move()
	{
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
	
	public void jump()
	{ 
		System.out.println(inTheAir);
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
		doubleJump = false;
		vy = 0;
		vy -= 5;
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

}
