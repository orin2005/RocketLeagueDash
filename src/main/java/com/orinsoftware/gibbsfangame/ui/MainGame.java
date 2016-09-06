package com.orinsoftware.gibbsfangame.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import com.orinsoftware.gibbsfangame.Camera;
import com.orinsoftware.gibbsfangame.Gibbs;
import com.orinsoftware.gibbsfangame.KeyboardManager.Directions;
import com.orinsoftware.gibbsfangame.Platform;
import com.orinsoftware.gibbsfangame.PlatformFactory;

public class MainGame extends JPanel implements KeyListener{
	
	private Gibbs gibbs;
	

	
	private boolean running;
	
	private long gameTick;
	
	private List<Platform> platforms;
	private PlatformFactory pFactory = PlatformFactory.getInstance();
	
	private Camera cam;
	
	
	public MainGame() {
		super();
		this.addKeyListener(this);
		gibbs = new Gibbs();
		cam = new Camera(gibbs);
		this.setVisible(true);
		this.setFocusable(true);
		
		gameTick = System.currentTimeMillis();
		platforms = new ArrayList<Platform>();
		
		generateFirstFivePlatforms();

	}
	
	public void run()
	{
		running = true;
		this.grabFocus();
		while( running )
		{
			if( gameTick + 16 < System.currentTimeMillis() )
			{
				gibbs.move();
				gameTick = System.currentTimeMillis();
				checkCollisions();
				cam.update();
				List<Platform> removal = 
						platforms.stream().filter( platform -> platform.getX() + 2000 < gibbs.getX()).collect(Collectors.toList());
				platforms.removeAll( removal );
				generatePlatforms();
				
				
				
				
				repaint();
				
				
			}
			
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g.translate(-cam.getX(), -cam.getY());
		
		gibbs.draw(g);
		
		platforms.stream()
			.forEach( platform -> platform.draw( g ) );
		
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			gibbs.jump();
		}
		else
		{
			switch(e.getKeyCode()) 
			{
			case KeyEvent.VK_RIGHT:
				gibbs.getKeyboardManager().setDirection(Directions.RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				gibbs.getKeyboardManager().setDirection(Directions.LEFT);
				break;
			case KeyEvent.VK_DOWN:
				gibbs.getKeyboardManager().setDirection(Directions.DOWN);
				break;
			case KeyEvent.VK_UP:
				gibbs.getKeyboardManager().setDirection(Directions.UP);
				break;
			case KeyEvent.VK_Z:
				gibbs.setBoosting(true);
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(gibbs.getKeyboardManager().getDirection() == Directions.LEFT && e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			gibbs.getKeyboardManager().setDirection(Directions.NONE);
		}
		else if(gibbs.getKeyboardManager().getDirection() == Directions.RIGHT && e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			gibbs.getKeyboardManager().setDirection(Directions.NONE);
		}
		else if( gibbs.getKeyboardManager().getDirection() == Directions.UP && e.getKeyCode() == KeyEvent.VK_UP )
		{
			gibbs.getKeyboardManager().setDirection(Directions.NONE);
		}
		else if( gibbs.getKeyboardManager().getDirection() == Directions.DOWN && e.getKeyCode() == KeyEvent.VK_DOWN )
		{
			gibbs.getKeyboardManager().setDirection(Directions.NONE);
		}
		if(e.getKeyCode() == KeyEvent.VK_Z)
		{
			gibbs.setBoosting(false);
		}
			
	}
	
	
	public void checkCollisions()
	{
		platforms.stream().forEach( gibbs::checkCollision );
	}
	
	private void generateFirstFivePlatforms()
	{
		pFactory.setPlatforms(platforms);
		platforms.add(new Platform(0, 300));
		platforms.add(new Platform(400, 300));
		
		platforms.add(pFactory.generatePlatform());
		platforms.add(pFactory.generatePlatform());
		platforms.add(pFactory.generatePlatform());
	}
	
	private void generatePlatforms()
	{
		if( platforms.stream().mapToInt(platform-> platform.getX()).max().getAsInt() < gibbs.getX()+ 2000)
			platforms.add(pFactory.generatePlatform());
	}

}
