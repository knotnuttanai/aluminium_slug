package character;

import javafx.scene.canvas.GraphicsContext;

public interface Movable {
	public void Jump();

	void render(GraphicsContext gc);

	void Walk(int direction);

}
