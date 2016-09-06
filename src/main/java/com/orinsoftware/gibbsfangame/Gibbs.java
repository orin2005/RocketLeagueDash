package com.orinsoftware.gibbsfangame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

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
	
	private Directions flipDirection;
	
	private KeyboardManager keyboardManager;
	
	private static final double MAX_GROUND_SPEED = 6;
	private static final double MAX_ACCELERATION_SPEED = 12;
	private static final double SLOW_DOWN_SPEED = 0.25;
	private static final double ACCELERATION = 0.1;
	
	private double rotationAngle;
	
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
		
		flipDirection = Directions.NONE;
		rotationAngle = 0;
	}
	
	public void move()
	{
		setVX( getVX() + ACCELERATION );
		x += vx;
		y += vy;
		
		if(inTheAir)
		{
			applyGravity();
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
	
	public void setVX( double vx )
	{
		this.vx = vx;
		if( this.vx > MAX_ACCELERATION_SPEED )
		{
			this.vx = MAX_ACCELERATION_SPEED;
		}
		if( this.vx > MAX_GROUND_SPEED )
			this.vx -= SLOW_DOWN_SPEED;
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
	
	private void applyGravity()
	{
		vy += 0.163;
	}
	
	public boolean isFalling()
	{
		return vy > 0;
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
		switch( direction )
		{
		case RIGHT:
			setVY(0);
			setVX(getVX()+5);
			rotate(Directions.RIGHT);
			rotationAngle = 0.1;
			break;
		case LEFT:
			break;
		case UP:
			break;
		case DOWN:
			break;
		case NONE:
			break;
		}
	}
	
	private void rotate( Directions direction )
	{
		if( direction == Directions.RIGHT )
		{
			flipDirection = direction;
			
		}
		else
			flipDirection = Directions.NONE;
	}
	
	public Directions getFlipDirection( )
	{
		return flipDirection;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public double getRotationAngle(){
		return rotationAngle;
	}
	
	public void setRotationAngle(int rotationAngle)
	{
		this.rotationAngle = rotationAngle;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		if(rotationAngle == 0)
		{
			g2d.draw(getRectangle());
			g2d.fill(getRectangle());
		}
		else
		{
			vy = 0;
			g2d.rotate(rotationAngle, x+width/2, y+height/2);
			g2d.draw(getRectangle());
			g2d.fill(getRectangle());
			rotationAngle += 0.3;
			if(rotationAngle >= 6.3)
			{
				rotationAngle = 0;
			}
		}
		g2d.dispose();
	}

}
