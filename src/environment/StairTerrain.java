package environment;

import character.Person;
import javafx.geometry.BoundingBox;

public class StairTerrain extends Terrain {

	public StairTerrain(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}
	public void standVertical(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		
	if(b.intersects(personBound)&&(p.getPosY() + p.getHeight()-2 <= posY || !p.isHasHorizontalCollision())) {
		   if((p.getVeloY() > 0 || p.getPosY() + p.getHeight() - posY < 5 )) {
			   p.setPosY(posY - p.getHeight());
			   p.setJump(false);
			  
			  
		   }
		   
		   p.setHasVerticalCollition(true);
			
			
		}
	else {
		
	//p.setHasVerticalCollition(false);
	
	}
		
	}
}
