package com.orinsoftware.gibbsfangame;

import java.util.List;

public class PlatformFactory {

	private static PlatformFactory instance;
	
	private List<Platform> platforms;
	
	private List<BoostPeanut> boostPeanuts;
	
	private PlatformFactory() {
		
	}
	
	public static PlatformFactory getInstance()
	{
		if(instance == null)
		{
			instance = new PlatformFactory();
		}
		return instance;
	}
	
	public Platform generatePlatform(boolean shouldGeneratePeanut)
	{
		
		System.out.println("num platforms: " + platforms.size());
		System.out.println("num peanuts: " + boostPeanuts.size());
		double maxOfX = platforms.stream().mapToDouble( platform -> platform.getPositionX() ).max().getAsDouble();
		
		Platform p = platforms.stream().filter( platform -> platform.getPositionX() == maxOfX).findFirst().get();
		
		double xCoordinate = maxOfX + 500;
		
		double yCoordinate = randomize(p.getPositionY());
		
		if(shouldGeneratePeanut)
		{
			BoostPeanut bp = new BoostPeanut(xCoordinate + p.getWidth()/2, yCoordinate - 40);
			boostPeanuts.add(bp);
		}
		
		return new Platform( xCoordinate, yCoordinate );
		
	}
	
	public void setPlatforms(List<Platform> platforms)
	{
		this.platforms = platforms;
	}
	
	public void setBoostPeanuts(List<BoostPeanut> boostPeanuts)
	{
		this.boostPeanuts = boostPeanuts;
	}
	
	private double randomize( double y )
	{
		int min = 50;
		int max = 550;
		
		return  (Math.random()* (max-min) + min);
		
			
	}

}
