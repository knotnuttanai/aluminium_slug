package weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gun extends GameObject{
	
	private Image Item;

	public Gun(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		Item = new Image("file:res/images/H.png");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Item, posX, posY + 4);
	}

}
