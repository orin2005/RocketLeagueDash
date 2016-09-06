package com.orinsoftware.gibbsfangame.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame() {
		// TODO Auto-generated constructor stub
		
		super("Gibbs RL Dash");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		MainFrame mf = new MainFrame();
		MainGame mg = new MainGame();
		mf.add(mg, BorderLayout.CENTER);
		mg.run();
		
	}

}
