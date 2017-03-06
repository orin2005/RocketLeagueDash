package com.gflsoftware.rocketleaguedash.ui;
//package com.orinsoftware.gibbsfangame.ui;
//
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
///**
// * Deprecated to support JavaFX instead of Swing
// * @author Trentin Thomas
// *
// */
//@Deprecated
//public class MainFrame extends Application{
//	
//	
//	public static void main(String[] args)
//	{
//		launch(args);
//		
//	}
//
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		primaryStage.setTitle("Gibbs RL Dash!");
//		
//		Group root = new Group();
//		Scene theScene = new Scene( root );
//		primaryStage.setScene(theScene);
//		
//		MainGame canvas = new MainGame();
//		root.getChildren().add(canvas);
//		
//		
//		final long startNanoTime = System.nanoTime();
//		
//		new AnimationTimer()
//		{
//			public void handle(long currentNanoTime)
//			{
//				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//				
//				canvas.run();
//			}
//		}.start();
//		
//		
//		primaryStage.show();
//	}
//
//}
