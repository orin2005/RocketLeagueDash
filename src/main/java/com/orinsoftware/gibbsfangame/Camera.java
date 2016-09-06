package com.orinsoftware.gibbsfangame;

public class Camera {

	private double x;
	private double y;
	
	private Gibbs gibbs;
	
	
	public Camera( Gibbs gibbs ) {
		// TODO Auto-generated constructor stub
		this.gibbs = gibbs;
		x = gibbs.getX() - 800/2;
		y = gibbs.getY() - 600/2;
	}
	
	public void update()
	{
		x = gibbs.getX() - 800/2;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}

}
