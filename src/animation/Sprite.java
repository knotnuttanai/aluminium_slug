package animation;

import javafx.scene.image.Image;

public class Sprite {
	Image[] image;

	public Sprite(Image[] image, int n) {
		for(int i = 0; i < n; i++) {
			this.image[i] = image[i];
		}
	}
	
	

}
