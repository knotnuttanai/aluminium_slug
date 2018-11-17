package environment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Foreground {
	private Image fg;
	private static double posX = 0;
	private double posY;

	public Foreground() {
		fg = new Image("file:res/images/foreground.png");
	}
	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(fg, posX, 0);
		
	}
	
	public static void update() {
		posX -= 2;
	}
	
	public void setPosX() {
		update();
	}

}