package application;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MenuPane extends StackPane {

	public static boolean running = false;
	public static ImageView imageView0 = new ImageView(ImageManager.buildImage("presstostart.png"));
	public static ImageView imageView1 = new ImageView(ImageManager.buildImage("startscreen.png"));
	public static ImageView imageView2 = new ImageView(ImageManager.buildImage("pause.png"));

	public MenuPane() {
		setPrefWidth(640);
		setPrefHeight(480);
		setAlignment(Pos.CENTER);

		this.getChildren().add(imageView1);
		this.getChildren().add(imageView0);
		this.getChildren().add(imageView2);
		imageView2.setVisible(false);
		run();

	}
	public static void run() {
		Thread thread = new Thread(() -> {

			try {

				while (!running) {
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
		thread.setDaemon(true);
		thread.start();
	}

}
