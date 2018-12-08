package character;

import application.GameEntity;
import javafx.scene.control.ProgressBar;

public class HpBar extends ProgressBar {
	private Hero hero;

	public HpBar() {
		super(1);
		setPrefWidth(200);
		hero = GameEntity.hero;
		setProgress(100);
	}

	public void update() {
		setProgress((double) hero.getHealth() / (double) hero.getMaxHealth());
	}
}