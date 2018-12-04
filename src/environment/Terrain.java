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
	public BoundingBox b;
	private boolean isInteract;
	public Terrain(double posX, double posY, double width, double height) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		veloX = 0;
		
		isInteract = false;
		b = new BoundingBox(posX, posY, width, height);
		
		
	}
	public void walk(int direction) {
		posX += direction*2;
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(posX, posY, width, height);
		
	}
	public void update() {
		posX += veloX;
		b = new BoundingBox(posX, posY, width, height);
	}
	public void isSomeOneHitHere(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		if(p.getWalkDirection() == 1) {
			
			//if(p.getPosX()+p.getWidth() >= posX&&p.getPosX() < posX&&p.getPosY()+p.getHeight() > posY) {
			if(personBound.intersects(b)&&p.getPosX() + p.getWidth() >= posX && p.getPosX()+p.getWidth()-5 < posX && p.getPosY() + p.getHeight() >= posY+5) {	
			//if(personBound.intersects(b)&&p.getPosX() + p.getWidth() >= posX) {	
				p.setHasHorizontalCollision(true);
				
			    //p.setHasVerticalCollition(false);
				
				
			
			}else {
				//p.setHasHorizontalCollision(false);
			}
		}
		if(p.getWalkDirection() == -1) {
			if(p.getPosX() <= posX +width && p.getPosX()+p.getWidth() > posX+width&&p.getPosY() + p.getHeight() >= posY+5) {
				
				p.setHasHorizontalCollision(true);
				
			}else {
			//p.setHasHorizontalCollision(false);
			}
		}
	}
	public void standVertical(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		
	if(b.intersects(personBound)&&(p.getPosY() + p.getHeight()-2 <= posY || !p.isHasHorizontalCollision())) {
		   if(p.getVeloY() > 0) {
			   p.setPosY(posY - p.getHeight());
			   p.setJump(false);
			  
			  
		   }
		   
		   p.setHasVerticalCollition(true);
			
			
		}
	else {
		
	//p.setHasVerticalCollition(false);
	
	}
		
	}
	public boolean isInteract() {
		return isInteract;
	}
	public void setInteract(boolean isInteract) {
		this.isInteract = isInteract;
	}
	
	
}
