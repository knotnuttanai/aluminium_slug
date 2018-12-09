package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScorePane {
	public static int score;
	private String showScore;

	public ScorePane() {
		score = 0;
		showScore = "";
	}

	public void update() {
		score += GameEntity.getCurrentFg().getVeloX() * -1;
		showScore = Integer.toString(score);
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.BEIGE);
		gc.setStroke(Color.BLACK);
		Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
		gc.setFont(font);
		gc.fillText(showScore, 320, 25);
		if (MenuPane.running) {
			gc.setFill(Color.BEIGE);
			gc.setStroke(Color.BLACK);
			Font font1 = Font.font("Tahoma", FontWeight.BOLD, 30);
			gc.setFont(font1);
			String level = "LVL: " + Integer.toString(ExpBar.level);
			gc.fillText(level, 0, 95);
			gc.setFill(Color.BEIGE);
			gc.setStroke(Color.BLACK);
			gc.setFont(font1);
			String status = "Stat: " + Integer.toString(HeroStatusPane.statusPoint);
			gc.fillText(status, 0, 140);
		}
	}

	public static void addScore(int point) {
		score += point;
	}
}
