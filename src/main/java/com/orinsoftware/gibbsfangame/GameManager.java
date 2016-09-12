package com.orinsoftware.gibbsfangame;

import java.util.ArrayList;
import java.util.List;

import com.orinsoftware.gibbsfangame.fxui.GameScene;

import javafx.scene.canvas.GraphicsContext;

public class GameManager {

	private static GameManager instance;
	
	private Gibbs gibbs;
	private PlayerCamera camera;
	private List<RLDSprite> gameObjects;
	private PlatformFactory pFactory;
	
	private GameManager() {
		gibbs = new Gibbs(0,200);
		camera = new PlayerCamera( gibbs );
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
		gc.clearRect(0,0, GameScene.WIDTH, GameScene.HEIGHT);

		gameObjects.stream().forEach( obj -> obj.render( gc ) );
	}
	
	public void updateAll( double delta )
	{
		
		gameObjects.stream().forEach( obj -> {
			if(obj != gibbs)
			{
				obj.setVelocityX(-gibbs.getVelocityX());

			}
			obj.update(delta); 
		});
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
	
	public PlayerCamera getCamera()
	{
		return camera;
	}
	
	public void createNewGame()
	{
//		gibbs = new Gibbs(0,200);
//		gameObjects.clear();
//		gameObjects.add(gibbs);
//		System.out.println("Cleared");
	}

}
