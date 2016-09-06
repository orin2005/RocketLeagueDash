package com.orinsoftware.gibbsfangame;

import java.util.List;
import java.util.OptionalInt;

public class PlatformFactory {

	private static PlatformFactory instance;
	
	private List<Platform> platforms;
	
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
	
	public Platform generatePlatform()
	{
		
		System.out.println("num platforms: " + platforms.size());
		int maxOfX = platforms.stream().mapToInt( platform -> platform.getX() ).max().getAsInt();
		
		Platform p = platforms.stream().filter( platform -> platform.getX() == maxOfX).findFirst().get();
		
		return new Platform( maxOfX + 500, randomize(p.getY()) );
		
	}
	
	public void setPlatforms(List<Platform> platforms)
	{
		this.platforms = platforms;
	}
	
	private int randomize( int y )
	{
		int min = 50;
		int max = 550;
		
		return (int) (Math.random()* (max-min) + min);
		
			
	}

}
