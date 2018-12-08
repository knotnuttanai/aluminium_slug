package environment;

import character.Person;
import javafx.geometry.BoundingBox;

public class StairTerrain extends Terrain {
	private int priority;

	public StairTerrain(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		priority = 0;
	}

	@Override
	public void isSomeOneHitHere(Person p) {
	}

	@Override
	public void standVertical(Person p) {
		BoundingBox personBound = new BoundingBox(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
		if (p.getPosY() + p.getHeight() - 10 <= posY && box.intersects(personBound) && !p.isHasHorizontalCollision()) {
			p.setJump(false);
			if ((p.getVeloY() > 0 || !p.isStandOnMainTerrain())) {
				p.setPosY(posY - p.getHeight());
			}
			if (priority == 1) {
				p.setStandOnMainTerrain(true);
			}
			p.setHasVerticalCollition(true);
		}
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
