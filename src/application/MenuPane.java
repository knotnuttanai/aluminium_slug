package application;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox {
	Image menu;

	public MenuPane() {
		setPrefWidth(680);
		setPrefHeight(480);
		setAlignment(Pos.CENTER);
		// ImageView imageView = new ImageView(new
		// Image(ClassLoader.getSystemResource("file:res/images/startscreen.png").toString()));
		// this.getChildren().add(imageView);
	}

}
