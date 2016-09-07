//package com.orinsoftware.gibbsfangame.ui;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.orinsoftware.gibbsfangame.BoostPeanut;
//import com.orinsoftware.gibbsfangame.Camera;
//import com.orinsoftware.gibbsfangame.Gibbs;
//import com.orinsoftware.gibbsfangame.KeyboardManager.Directions;
//import com.orinsoftware.gibbsfangame.Platform;
//import com.orinsoftware.gibbsfangame.PlatformFactory;
//
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//@Deprecated
//public class MainGame extends Canvas implements KeyListener{
//	
//	private Gibbs gibbs;
//	
//
//	
//	private boolean running;
//	
//	private long gameTick;
//	
//	private List<Platform> platforms;
//	private List<BoostPeanut> peanuts;
//	private PlatformFactory pFactory;
//	
//	private Camera cam;
//	
//	
//	public MainGame() {
//		super(800, 600);
//		//gibbs = new Gibbs();
//		cam = new Camera(gibbs);
//		this.setVisible(true);
//		
//		gameTick = System.currentTimeMillis();
//		platforms = new ArrayList<Platform>();
//		peanuts = new ArrayList<BoostPeanut>();
//		pFactory = PlatformFactory.getInstance();
//		
//		generateFirstFivePlatforms();
//
//	}
//	
//	public void run()
//	{
//		//gibbs.move();
//		gameTick = System.currentTimeMillis();
//		checkCollisions();
//		cam.update();
//		List<Platform> removal = 
//				platforms.stream()
//				.filter( platform -> platform.getX() + 2000 < gibbs.getX())
//				.collect(Collectors.toList());
//		List<BoostPeanut> removePeanuts = 
//				peanuts.stream()
//				.filter(BoostPeanut::isPickedUp)
//				.collect(Collectors.toList());
//		
//		platforms.removeAll( removal );
//		peanuts.removeAll( removePeanuts );
//		generatePlatforms();
//		
//		
//		paint();
//	}
//	
//	public void paint()
//	{
//		GraphicsContext gc = this.getGraphicsContext2D();
//		gc.setFill( new Color(1, 1, 1, 1) );
//		gc.fillRect(0, 0, 800, 600);
//		gc.translate(-cam.getX(), -cam.getY());
//		
//		gibbs.draw(gc);
//		platforms.stream().forEach( platform -> platform.draw(gc));
//		peanuts.stream().forEach( peanut -> peanut.draw(gc));
//	}
//
//	public void keyTyped(KeyEvent e) {
//	}
//
//	public void keyPressed(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_SPACE)
//		{
//			gibbs.jump();
//		}
//		else
//		{
//			switch(e.getKeyCode()) 
//			{
//			case KeyEvent.VK_RIGHT:
//				gibbs.getKeyboardManager().setDirection(Directions.RIGHT);
//				break;
//			case KeyEvent.VK_LEFT:
//				gibbs.getKeyboardManager().setDirection(Directions.LEFT);
//				break;
//			case KeyEvent.VK_DOWN:
//				gibbs.getKeyboardManager().setDirection(Directions.DOWN);
//				break;
//			case KeyEvent.VK_UP:
//				gibbs.getKeyboardManager().setDirection(Directions.UP);
//				break;
//			case KeyEvent.VK_Z:
//				gibbs.setBoosting(true);
//				break;
//			}
//		}
//	}
//
//	public void keyReleased(KeyEvent e) {
//		if(gibbs.getKeyboardManager().getDirection() == Directions.LEFT && e.getKeyCode() == KeyEvent.VK_LEFT)
//		{
//			gibbs.getKeyboardManager().setDirection(Directions.NONE);
//		}
//		else if(gibbs.getKeyboardManager().getDirection() == Directions.RIGHT && e.getKeyCode() == KeyEvent.VK_RIGHT)
//		{
//			gibbs.getKeyboardManager().setDirection(Directions.NONE);
//		}
//		else if( gibbs.getKeyboardManager().getDirection() == Directions.UP && e.getKeyCode() == KeyEvent.VK_UP )
//		{
//			gibbs.getKeyboardManager().setDirection(Directions.NONE);
//		}
//		else if( gibbs.getKeyboardManager().getDirection() == Directions.DOWN && e.getKeyCode() == KeyEvent.VK_DOWN )
//		{
//			gibbs.getKeyboardManager().setDirection(Directions.NONE);
//		}
//		if(e.getKeyCode() == KeyEvent.VK_Z)
//		{
//			gibbs.setBoosting(false);
//		}
//			
//	}
//	
//	
//	public void checkCollisions()
//	{
//		platforms.stream().forEach( gibbs::checkCollision );
//		peanuts.stream().forEach( gibbs::checkCollision );
//	}
//	
//	private void generateFirstFivePlatforms()
//	{
//		pFactory.setPlatforms(platforms);
//		pFactory.setBoostPeanuts(peanuts);
//		platforms.add(new Platform(0, 300));
//		platforms.add(new Platform(400, 300));
//		
//		platforms.add(pFactory.generatePlatform(false));
//		platforms.add(pFactory.generatePlatform(false));
//		platforms.add(pFactory.generatePlatform(false));
//	}
//	
//	private void generatePlatforms()
//	{
//	    boolean shouldGenerateBoostPeanut = Math.random() * 100 < 12; //12% chance?
//		
//		if( platforms.stream().mapToInt(platform-> platform.getX()).max().getAsInt() < gibbs.getX()+ 2000)
//			platforms.add(pFactory.generatePlatform(shouldGenerateBoostPeanut));
//	}
//
//}
