package com.orinsoftware.gibbsfangame.fxui;

import com.orinsoftware.gibbsfangame.GameManager;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;

public class MainStage extends Application{
	
	public static void main(String[] args)
	{
		launch(args);
		
	}


	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gibbs RL Dash!");
		
		primaryStage.setResizable(false);
	
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene(theScene);
		
		GameScene canvas = new GameScene();
		root.getChildren().add(canvas);
		primaryStage.sizeToScene();
		
		final long lastNanoTime = System.nanoTime();
		
		theScene.setOnKeyPressed( evt -> {
			switch(evt.getCode())
			{
			case SPACE:
				GameManager.getInstance().getPlayer().jump();
				break;
			default:
				break;
				
			}
		});
		
		//theScene.setCamera(new PerspectiveCamera());
		
		
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - lastNanoTime) / 1000000000.0;
				
				//theScene.getCamera().setTranslateX(GameManager.getInstance().getCamera().getX());
				//theScene.getCamera().setTranslateY(GameManager.getInstance().getCamera().getY());
				
				//((PerspectiveCamera)theScene.getCamera()).setVerticalFieldOfView(false);
				//((PerspectiveCamera)theScene.getCamera()).setFieldOfView(GameManager.getInstance().getCamera().getX());;
				
				canvas.update( t );
			}
		}.start();
		
		
		primaryStage.show();
	}

}
