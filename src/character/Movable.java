package character;

import javafx.scene.canvas.GraphicsContext;

public interface Movable {
	public void jump();

	void render(GraphicsContext gc);

	void walk(int direction);

}
