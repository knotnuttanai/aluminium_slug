package environment;

import character.Person;
import javafx.geometry.BoundingBox;

public class StairTerrain extends Terrain {
	private int priority;
	public StairTerrain(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		priority = 0;
		// TODO Auto-generated constructor stub
	}
	public void isSomeOneHitHere(Person p) {
		
	}
	public void standVertical(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		
	if(b.intersects(personBound)&&(p.getPosY() + p.getHeight()-2 <= posY || !p.isHasHorizontalCollision())) {
		   if((p.getVeloY() > 0 || !p.isStandOnMainTerrain()) ){
			   p.setPosY(posY - p.getHeight());
			   p.setJump(false);
			  
			  
		   }
		   if(priority==1) {
			   p.setStandOnMainTerrain(true);
		   }
		   
		   p.setHasVerticalCollition(true);
			
			
		}
	else {
		
	//p.setHasVerticalCollition(false);
	
		}
		
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
