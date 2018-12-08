package application;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class MenuPane extends StackPane{

	public static boolean running = false;
	public static ImageView imageView0 = new ImageView(new Image(ClassLoader.getSystemResource("presstostart.png").toString()));
	public static ImageView imageView1 = new ImageView(new Image(ClassLoader.getSystemResource("startscreen.png").toString()));


	public MenuPane() {
		setPrefWidth(680);
		setPrefHeight(480);
		setAlignment(Pos.CENTER);

		
		this.getChildren().add(imageView1);
	    this.getChildren().add(imageView0);
	    
	   Thread thread = new Thread(()->{
		   
		   try {
	    		
	    		while(!running) {
	    		imageView0.setVisible(true);
				Thread.sleep(500);
				imageView0.setVisible(false);
				Thread.sleep(500);
	    		}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	   thread.start();
	   

	}

	
	
}
 