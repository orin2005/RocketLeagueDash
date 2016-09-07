package com.orinsoftware.gibbsfangame;

import java.util.List;

public class PlatformFactory {

	private static PlatformFactory instance;

	
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
		
		double maxOfX = GameManager.getInstance().getObjects().stream().mapToDouble( platform -> platform.getPositionX() ).max().getAsDouble();
		
		RLDSprite p = GameManager.getInstance().getObjects().stream().filter( platform -> platform.getPositionX() == maxOfX).findFirst().get();
		
		double xCoordinate = maxOfX + 500;
		
		double yCoordinate = randomize(p.getPositionY());
		
		if(shouldGeneratePeanut)
		{
			BoostPeanut bp = new BoostPeanut(xCoordinate + p.getWidth()/2, yCoordinate - 40);
			GameManager.getInstance().addObject(bp);
		}
		
		return new Platform( xCoordinate, yCoordinate );
		
	}
	
	private double randomize( double y )
	{
		int min = 50;
		int max = 550;
		
		return  (Math.random()* (max-min) + min);
		
			
	}

}
