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
		OptionalInt maxOfX = platforms.stream().mapToInt( platform -> platform.getX() ).max();
		
		return new Platform( maxOfX.getAsInt() + 400, 300 );
		
	}
	
	public void setPlatforms(List<Platform> platforms)
	{
		this.platforms = platforms;
	}

}
