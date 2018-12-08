package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class NumberPane {
	protected String showText;

	public NumberPane() {
		showText = "";
	}

	public abstract void update();

	public void render(GraphicsContext gc) {
		gc.setFill(Color.BEIGE);
		gc.setStroke(Color.BLACK);
		Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
		gc.setFont(font);
		gc.fillText(showText, 0, 300);
	}

}