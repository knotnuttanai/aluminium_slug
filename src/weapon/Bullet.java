package weapon;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {
	
	Circle view;
	public Bullet(Gun A) {
		view = new Circle(2);
		view.setFill(Color.RED);
	}

}
