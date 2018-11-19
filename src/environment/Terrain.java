package environment;

import application.GameEntity;
import character.Person;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Terrain {
	public double posX ,posY ,width ,height, veloX;
	Image terrain;
	BoundingBox b;
	private boolean trigger;
	public Terrain(double posX, double posY, double width, double height) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		veloX = 0;
		trigger = false;
		b = new BoundingBox(posX, posY, width, height);
		
		
	}
	public void walk(int direction) {
		posX += direction*2;
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(posX, posY, width, height);
		
	}
	public void update() {
		posX += veloX;
		b = new BoundingBox(posX, posY, width, height);
	}

	public boolean whenSomeOneStandHere(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		
	if(b.intersects(personBound) && p.getPosY()+p.getHeight()<= posY+20) {
		if(p.getPosY() > posY) { 
			System.out.println("hi");
			return false;
			}
		    if(!trigger) {
		    	p.setPosY(posY - p.getHeight());
		    	trigger = false;
		    	
		    }
			return true;
			
		}
	trigger = false;
	return false;
		
	}
	
}
