package environment;

import application.GameEntity;
import application.GameScene;
import character.Person;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bomb;
import weapon.GameObject;

public class Terrain {
	public double posX ,posY ,width ,height, veloX;
	Image terrain;
	public BoundingBox b;
	private boolean isInteract;
	private boolean isDead;
	public Terrain(double posX, double posY, double width, double height) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		veloX = 0;
		isDead = false;
		isInteract = false;
		b = new BoundingBox(posX, posY, width, height);
		
		
	}
	public void walk(int direction) {
		posX += direction*2;
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(posX, posY, width, height);
		
	}
	public void update() {
		checkSelfDelete();
		veloX = GameEntity.getCurrentFg().getVeloX();
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
		
	if(b.intersects(personBound)&&(p.getPosY() + p.getHeight()-20 <= posY )) {
		   if(p.getVeloY() > 0) {
			   p.setPosY(posY - p.getHeight());
			   p.setJump(false);
			  
		   }
		   p.setStandOnMainTerrain(true);
		   p.setHasVerticalCollition(true);
			
			
		}
		
	}
	public void gunStandVertical(GameObject g) {
		BoundingBox personBound = new BoundingBox(g.getPosX(), g.getPosY(), g.getWidth(), g.getHeight());
		
	if(b.intersects(personBound)&&(g.getPosY() + g.getHeight()-2 <= posY || !g.isHasHorizontalCollision())) {
		   if(g.getVeloY() > 0) {
			   if(g instanceof Bomb) {
				   g.setPosY(posY -100);
			   }else {
				   g.setPosY(posY - g.getHeight());
			   }
		   }
		   
		   g.setHasVerticalCollition(true);
			
			
		}
		
	}
	public boolean isInteract() {
		return isInteract;
	}
	public void setInteract(boolean isInteract) {
		this.isInteract = isInteract;
	}
	public void checkSelfDelete() {
		
		if(this.posX +this.width < -10) {
			
			setDead();
		}
	}
	public void setDead() {
		isDead = true;
	}
	public boolean isDead() {
		return isDead;
	}
}
