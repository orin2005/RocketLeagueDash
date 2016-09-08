package com.orinsoftware.gibbsfangame;

public class PlayerCamera {

	private double x;
	private double y;
	
	private Gibbs gibbs;
	
	
	public PlayerCamera( Gibbs gibbs ) {
		// TODO Auto-generated constructor stub
		this.gibbs = gibbs;
		x = this.gibbs.getPositionX() - 800/2;
		y = this.gibbs.getPositionY() - 600/2;
	}
	
	public void update()
	{
		x = gibbs.getPositionX() - 800/2;
		y = -100;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Camera: \n")
				.append("\txCoordinate:"+getX()+"\n")
				.append("\tyCoordinate:"+getY()+"\n")
				.append("\tgibbxCoordinate:"+gibbs.getPositionX()+"\n")
				.append("\tgibbyCoordinate:"+gibbs.getPositionY()+"\n")
				.toString();
	}

}
