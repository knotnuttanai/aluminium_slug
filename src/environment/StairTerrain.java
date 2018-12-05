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
		/*if(p.getWalkDirection() == -1) {
			if(p.getPosX() <= posX +width && p.getPosX()+p.getWidth() > posX+width&&p.getPosY() + p.getHeight() >= posY+5) {
				
				p.setHasHorizontalCollision(true);
				
			}else {
			//p.setHasHorizontalCollision(false);
			}
		}*/
	}
	public void standVertical(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		
		
		if(p.getPosY() + p.getHeight()-10 <= posY&&b.intersects(personBound)&& !p.isHasHorizontalCollision()) {
				p.setJump(false);
				if((p.getVeloY() > 0 || !p.isStandOnMainTerrain()) ){
					   p.setPosY(posY - p.getHeight());
					   
					  
					  
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
