package application;

import character.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BulletPane extends NumberPane {
	private Hero hero;

	public BulletPane(Hero hero) {
		super();
		this.hero = hero;
	}

	@Override
	public void update() {
		showText = Integer.toString(hero.getUseGunBullet());
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BEIGE);
		gc.setStroke(Color.BLACK);
		Font font1 = Font.font("Tahoma", FontWeight.BOLD, 30);
		gc.setFont(font1);
		gc.fillText("Gre: " + Integer.toString(GameEntity.hero.getMaxGrenade()), 500, 25);
		if (hero.getGun() == 1) {
			gc.setFill(Color.BEIGE);
			gc.setStroke(Color.BLACK);
			Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
			gc.setFont(font);
			gc.fillText(showText, 0, 50);
		}
	}

}
