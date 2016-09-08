package com.orinsoftware.gibbsfangame.fxui;

import com.orinsoftware.gibbsfangame.BoostPeanut;
import com.orinsoftware.gibbsfangame.GameManager;
import com.orinsoftware.gibbsfangame.Gibbs;
import com.orinsoftware.gibbsfangame.Platform;
import com.orinsoftware.gibbsfangame.RLDSprite;

import javafx.scene.canvas.Canvas;

public class GameScene extends Canvas {
	
	private GameManager manager;
	
	public GameScene() {
		super(800,600);
		
		this.setVisible(true);
		manager = GameManager.getInstance();
		generateFirstTwoPlatforms();

	}
	
	public void update( double delta )
	{
		generatePlatforms();
		
		checkCollisions();
		
		this.setTranslateX(-GameManager.getInstance().getCamera().getX());
		this.setTranslateY(-GameManager.getInstance().getCamera().getY());
		
		manager.renderAll( this.getGraphicsContext2D() );
		
		manager.updatePlayer( delta );
		
	}
	
	private void generateFirstTwoPlatforms()
	{
		manager.addObject( new Platform(0, 300) );
		manager.generateNewPlatform();
	}
	
	private void generatePlatforms()
	{	
		if( manager.getObjects().stream().mapToDouble( platform-> platform.getPositionX() ).max().getAsDouble() < manager.getPlayer().getPositionX()+ 2000)
			manager.generateNewPlatform();
	}
	
	private void checkCollisions()
	{
		
		
		Gibbs gibbs = manager.getPlayer();
		
		System.out.println(manager.getCamera());
		
		
		for( RLDSprite object : manager.getObjects() )
		{
			
			if( gibbs.intersects(object) && gibbs != object )
			{
				if( object instanceof Platform)
				{
					Platform platform = (Platform)object;
					if( gibbs.getPositionY() + gibbs.getHeight() > platform.getPositionY() )
					{
						//we are on top of platform.
						gibbs.setFalling(false);
						gibbs.setDoubleJump(true);
						gibbs.setPositionY(platform.getPositionY() - gibbs.getHeight());
					}
					else
					{
						gibbs.setFalling(true);
					}
				}
				else
				{
					BoostPeanut peanut = (BoostPeanut)object;
					peanut.setPickedUp( true );
					gibbs.consume(peanut);
				}
			}
		}
	}
	


}
