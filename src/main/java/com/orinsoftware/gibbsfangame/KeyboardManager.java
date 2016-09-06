package com.orinsoftware.gibbsfangame;

public class KeyboardManager 
{

	private static KeyboardManager instance;
	
	private Directions direction;
	
	public enum Directions { UP, DOWN, LEFT, RIGHT, NONE };
	
	private KeyboardManager() {

		direction = Directions.NONE;
	}
	
	public void setDirection( Directions direction )
	{
		this.direction = direction;
	}
	
	public Directions getDirection()
	{
		return direction;
	}
	
	public static KeyboardManager getInstance()
	{
		if(instance == null)
			instance = new KeyboardManager();
		return instance;
	}

}
