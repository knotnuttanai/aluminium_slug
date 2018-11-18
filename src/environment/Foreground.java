package environment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Foreground {
	private Image fg;
	private double posX;
	private double posY;
	private double veloX;
	

	public Foreground() {
		fg = new Image("file:res/images/foreground.png");
		veloX = 0;
		posX = 0;
	}
	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(fg, posX, 0);
		
	}
	
	public void update() {
		//posX += veloX;
				
	}
	
	public void setPosX() {
		update();
	}
	public void moveScreen(int direction) {
		posX += direction;
	}

	public void stop() {
		// TODO Auto-generated method stub
		veloX = 0;
	}

	public double getVeloX() {
		return veloX;
	}

}