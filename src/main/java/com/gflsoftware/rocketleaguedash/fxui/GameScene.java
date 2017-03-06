package com.gflsoftware.rocketleaguedash.fxui;

import java.util.ArrayList;
import java.util.List;

import com.gflsoftware.rocketleaguedash.BoostPeanut;
import com.gflsoftware.rocketleaguedash.GameManager;
import com.gflsoftware.rocketleaguedash.Platform;
import com.gflsoftware.rocketleaguedash.Player;
import com.gflsoftware.rocketleaguedash.RLDObject;

import javafx.scene.canvas.Canvas;

public class GameScene extends Canvas {
	
	private GameManager manager;
	
	public static final double WIDTH = 1200;
	public static final double HEIGHT = 800;
	
	private long lastNanoTime;
	
	public GameScene() {
		super(WIDTH,600);
		
		this.setVisible(true);
		manager = GameManager.getInstance();
		generateFirstTwoPlatforms();
		lastNanoTime = System.nanoTime();
	}
	
	public void update( double delta )
	{
		
		double t = (delta - lastNanoTime) / 1000000000.0;
		
		manager.updateAll( t );
		if( manager.getPlayer().getPositionY() > 800 )
		{
			GameManager.getInstance().createNewGame();
		}
		checkCollisions();
		generatePlatforms();
		
		
		
		
		manager.renderAll( this.getGraphicsContext2D() );
		
		lastNanoTime = System.nanoTime();
	}
	
	private void generateFirstTwoPlatforms()
	{
		manager.addObject( new Platform(GameScene.WIDTH/2, 300) );
		manager.generateNewPlatform();
	}
	
	private void generatePlatforms()
	{	
		if( manager.getObjects().isEmpty() )
		{
			generateFirstTwoPlatforms();
		}
		if( manager.getObjects().stream().mapToDouble( platform-> platform.getPositionX() ).max().getAsDouble() < manager.getPlayer().getPositionX()+ 2000)
			manager.generateNewPlatform();
	}
	
	private void checkCollisions()
	{
		
		Player player = manager.getPlayer();
	
		boolean shouldBeFalling = true;
		List<RLDObject> objsToRemove = new ArrayList<RLDObject>();
		for( RLDObject object : manager.getObjects() )
		{
			
			if( player.intersects(object) && player != object )
			{
				//System.out.println("Intersect with " + object);
				//GameManager.compareObjects(player, object);
				if( object instanceof Platform )
				{
					Platform platform = (Platform)object;					
					if( player.getVelocityY() >= 0)
					{
						player.setPositionY( platform.getPositionY() - player.getHeight()+1 );
						shouldBeFalling = false;
						player.setDoubleJump(true);
					}
				}
				else
				{
					BoostPeanut peanut = (BoostPeanut)object;
					peanut.setPickedUp( true );
					player.consume( peanut );
					objsToRemove.add(peanut);
				}
				
			}
		}
		
		manager.getObjects().removeAll( objsToRemove );
		player.setFalling(shouldBeFalling);
	}
	


}
