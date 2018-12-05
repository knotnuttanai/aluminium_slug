package application;

import character.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BulletPane extends NumberPane{
	Hero hero;
	public BulletPane(Hero hero) {
		super();
		this.hero = hero;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		showText = Integer.toString(hero.getUsedGun1Bullet());
	}
	public void render(GraphicsContext gc) {
		if(hero.getGun()==1) {
			gc.setFill(Color.BEIGE);
			gc.setStroke(Color.BLACK);
			Font font =  Font.font("Tahoma",FontWeight.BOLD, 30);
			gc.setFont(font);
			gc.fillText(showText, 0, 50);
		}
	}

}
