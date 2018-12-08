package application;

import javafx.scene.image.Image;

public class ImageManager {

	public static Image buildImage(String name) {
		Image img = new Image(ClassLoader.getSystemResource(name).toString());
		return img;
	}

	public static Image buildImage(String name, double width, double height, boolean preservedRatio, boolean smooth) {
		Image img = new Image(ClassLoader.getSystemResource(name).toString(), width, height, preservedRatio, smooth);
		return img;
	}

}
