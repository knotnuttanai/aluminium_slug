package environment;

import application.GameEntity;
import character.Person;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Terrain {
	private double posX ,posY ,width ,height;
	Image terrain;
	BoundingBox b;
	private boolean trigger;
	public Terrain(double posX, double posY, double width, double height) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		trigger = false;
		b = new BoundingBox(posX, posY, width, height);
		GameEntity.createTerrain(this);
	}
	public void render(GraphicsContext gc) {
		gc.fillRect(posX, posY, width, height);
		
	}
	public void whenSomeOneStandHere(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
	if(b.intersects(personBound)) {
	
		    
			p.setHasVerticalCollition(true);
			
			
		}
	else {
		
		p.setHasVerticalCollition(false);
		
	}
		
	}
	
}
