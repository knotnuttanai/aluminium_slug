package character;

import application.GameEntity;

public class TankHpBar extends HpBar {
	public void update() {
		if (GameEntity.hero.isInTheTank()) {
		}
	}
}
