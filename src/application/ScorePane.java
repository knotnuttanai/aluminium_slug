package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScorePane {
	private static int score;
	private String showScore;
	public ScorePane() {
		int score = 0;
		showScore = "";
		
	}
	public void update(){
		score += GameEntity.getCurrentFg().getVeloX()*-1;
		showScore = Integer.toString(score);
		
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BEIGE);
		gc.setStroke(Color.BLACK);
		Font font =  Font.font("Tahoma",FontWeight.BOLD, 30);
		gc.setFont(font);
		gc.fillText(showScore, 0, 200);
	}
	public static void addScore(int point) {
		score += point;
	}
}
