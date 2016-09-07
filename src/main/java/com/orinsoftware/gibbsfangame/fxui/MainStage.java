package com.orinsoftware.gibbsfangame.fxui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.orinsoftware.gibbsfangame.GameManager;

public class MainStage extends Application{
	
	
	public static void main(String[] args)
	{
		launch(args);
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Gibbs RL Dash!");
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene(theScene);
		
		GameScene canvas = new GameScene();
		root.getChildren().add(canvas);
		
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
		
		
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				double t = (currentNanoTime - lastNanoTime) / 1000000000.0;
				
				canvas.update( t );
			}
		}.start();
		
		
		primaryStage.show();
	}

}
