package com.orinsoftware.gibbsfangame.fxui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.orinsoftware.gibbsfangame.BoostPeanut;
import com.orinsoftware.gibbsfangame.Camera;
import com.orinsoftware.gibbsfangame.Gibbs;
import com.orinsoftware.gibbsfangame.Platform;
import com.orinsoftware.gibbsfangame.PlatformFactory;
import com.sun.glass.events.KeyEvent;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameScene extends Canvas {
	
	private Gibbs gibbs;
	
	private List<Platform> platforms;
	private List<BoostPeanut> peanuts;
	private PlatformFactory pFactory;
	
	private Camera cam;
	
	
	public GameScene() {
		super(800, 600);
		gibbs = new Gibbs(0, 250);
		cam = new Camera(gibbs);
		this.setVisible(true);
		platforms = new ArrayList<Platform>();
		peanuts = new ArrayList<BoostPeanut>();
		pFactory = PlatformFactory.getInstance();
		
		this.setOnKeyPressed( event -> {
			System.out.println(event);
			if(event.getCode() == KeyCode.SPACE)
			{
				gibbs.jump();
			}
		});
		
		generateFirstFivePlatforms();

	}
	
	public void update( double delta )
	{
		gibbs.update(delta);
		cam.update();
		List<Platform> removal = 
				platforms.stream()
				.filter( platform -> platform.getPositionX() + 2000 < gibbs.getPositionX())
				.collect(Collectors.toList());
		List<BoostPeanut> removePeanuts = 
				peanuts.stream()
				.filter( BoostPeanut::isPickedUp )
				.collect(Collectors.toList());
		generatePlatforms();
		
		checkCollisions();
		
		paint();
		
		System.out.println(gibbs);
	}
	
	public void paint()
	{
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill( new Color(1, 1, 1, 1) );
		gc.fillRect(cam.getX(), cam.getY(), 800, 600);
		//gc.translate(-cam.getX(), -cam.getY());
		
		gibbs.render(gc);
		platforms.stream().forEach( platform -> platform.render(gc) );
		peanuts.stream().forEach( peanut -> peanut.render(gc) );
	}

	
	private void generateFirstFivePlatforms()
	{
		pFactory.setPlatforms(platforms);
		pFactory.setBoostPeanuts(peanuts);
		platforms.add(new Platform(0, 300));
		platforms.add(new Platform(400, 300));
		
		platforms.add( pFactory.generatePlatform(false) );
		platforms.add( pFactory.generatePlatform(false) );
		platforms.add( pFactory.generatePlatform(true) );
	}
	
	private void generatePlatforms()
	{
	    boolean shouldGenerateBoostPeanut = Math.random() * 100 < 12; //12% chance?
		
		if( platforms.stream().mapToDouble(platform-> platform.getPositionX()).max().getAsDouble() < gibbs.getPositionX()+ 2000)
			platforms.add(pFactory.generatePlatform(shouldGenerateBoostPeanut));
	}
	
	private void checkCollisions()
	{
		for( Platform platform : platforms )
		{
			if( gibbs.intersects(platform) )
			{
				if( gibbs.getPositionY() + gibbs.getHeight() > platform.getPositionY() )
				{
					//we are on top of platform.
					gibbs.setFalling(false);
				}
				else
				{
					gibbs.setFalling(true);
				}
			}
		}
	}
	


}
