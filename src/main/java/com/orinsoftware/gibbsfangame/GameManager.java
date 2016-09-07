package com.orinsoftware.gibbsfangame;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class GameManager {

	private static GameManager instance;
	
	private Gibbs gibbs;
	private Camera camera;
	private List<RLDSprite> gameObjects;
	private PlatformFactory pFactory;
	
	private GameManager() {
		gibbs = new Gibbs(0,200);
		camera = new Camera( gibbs );
		gameObjects = new ArrayList<RLDSprite>();
		gameObjects.add( gibbs );
		pFactory = PlatformFactory.getInstance();
	}
	
	public static GameManager getInstance()
	{
		if(instance == null)
			instance = new GameManager();
		return instance;
	}
	
	public void addObject( RLDSprite obj )
	{
		gameObjects.add(obj);
	}
	
	public void renderAll(GraphicsContext gc)
	{
		gc.clearRect(camera.getX(),camera.getY(), 800, 800);
		gameObjects.stream().forEach( obj -> obj.render( gc ) );
	}
	
	public void updatePlayer( double delta )
	{
		gibbs.update(delta);
	}
	
	public void generateNewPlatform()
	{
		gameObjects.add(pFactory.generatePlatform( Math.random() * 100 < 12 )); //12% chance of peanut?
	}
	
	public Gibbs getPlayer()
	{
		return gibbs;
	}
	
	public List<RLDSprite> getObjects()
	{
		return gameObjects;
	}
	
	public Camera getCamera()
	{
		return camera;
	}

}
