package com.gflsoftware.rocketleaguedash.fxui;

import com.gflsoftware.rocketleaguedash.GameManager;
import com.gflsoftware.rocketleaguedash.KeyboardManager;
import com.gflsoftware.rocketleaguedash.KeyboardManager.Directions;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application{
	
	public static void main(String[] args)
	{
		launch(args);
		
	}


	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jon Sandman's RL Dash!");
		
		primaryStage.setResizable(false);
	
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene(theScene);
		
		GameScene canvas = new GameScene();
		root.getChildren().add(canvas);
		
		theScene.setOnKeyPressed( evt -> {
			switch(evt.getCode())
			{
			case SPACE:
				GameManager.getInstance().getPlayer().jump();
				break;
			case RIGHT:
				KeyboardManager.getInstance().setDirection(Directions.RIGHT);
				break;
			default:
				break;
				
			}
		});
		
		theScene.setOnKeyReleased( evt -> {
			
			switch( evt.getCode() )
			{
				case RIGHT:
					KeyboardManager.getInstance().setDirection(Directions.NONE);
					break;
			}
		});
		
		final long lastNanoTime = System.nanoTime();
		
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{
				
				canvas.update( currentNanoTime );
			}
		}.start();
		
		
		primaryStage.show();
	}

}
