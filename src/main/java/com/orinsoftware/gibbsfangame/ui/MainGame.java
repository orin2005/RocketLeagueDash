package com.orinsoftware.gibbsfangame.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.orinsoftware.gibbsfangame.Camera;
import com.orinsoftware.gibbsfangame.Gibbs;
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
			if( gameTick + 17 < System.currentTimeMillis() )
			{
				gibbs.move();
				//camera.setLocation(gibbs);
				gameTick = System.currentTimeMillis();
				checkCollisions();
				cam.update();
			}
			
			repaint();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g.translate(-cam.getX(), -cam.getY());
		
		g2d.fillRect((int)gibbs.getX(), (int)gibbs.getY(), (int)gibbs.getWidth(), (int)gibbs.getHeight());
		
		platforms.stream()
			.forEach( platform -> platform.paint( g ) );
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			gibbs.jump();
		}
	}

	public void keyReleased(KeyEvent e) {
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

}
