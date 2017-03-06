package com.gflsoftware.rocketleaguedash;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.gflsoftware.rocketleaguedash.fxui.GameScene;

import javafx.scene.canvas.GraphicsContext;

public class GameManager {

	private static GameManager instance;
	
	private Player player;
	private PlayerCamera camera;
	private List<RLDObject> gameObjects;
	private PlatformFactory pFactory;
	
	private GameManager() {
		player = new Player(0,200);
		camera = new PlayerCamera( player );
		gameObjects = new ArrayList<RLDObject>();
		gameObjects.add( player );
		pFactory = PlatformFactory.getInstance();
	}
	
	public static GameManager getInstance()
	{
		if(instance == null)
			instance = new GameManager();
		return instance;
	}
	
	public void addObject( RLDObject obj )
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
			if(obj != player)
			{
				obj.setVelocityX(-player.getVelocityX());

			}
			obj.update(delta); 
		});
	}
	
	public void generateNewPlatform()
	{
		gameObjects.add(pFactory.generatePlatform( Math.random() * 100 < 12 )); //12% chance of peanut?
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public List<RLDObject> getObjects()
	{
		return gameObjects;
	}
	
	public PlayerCamera getCamera()
	{
		return camera;
	}
	
	public void createNewGame()
	{
		player = new Player(0,200);
		player.setPositionX(0);
		player.setPositionY(200);
		gameObjects.clear();
		gameObjects.add(player);
		addObject( new Platform(GameScene.WIDTH/2, 300) );
		generateNewPlatform();
		
//		gibbs = new Gibbs(0,200);
//		gameObjects.clear();
//		gameObjects.add(gibbs);
//		System.out.println("Cleared");
	}
	
	public static void compareObjects( Player s, RLDObject g )
	{
		PrintStream o = System.out;
		
		o.println("Gibbs: ( "+ ((int)s.getPositionX()) +"," + ((int)s.getPositionY()) + " )" + "\twidth: " + s.getWidth() + "\theight: "+s.getHeight());
		o.println("RLDObject: ( "+ ((int)g.getPositionX()) +"," + ((int)g.getPositionY()) + " )" + "\twidth: " + g.getWidth() + "\theight: "+g.getHeight());;
		o.println();
	}

}
