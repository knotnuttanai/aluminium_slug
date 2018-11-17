package character;

import javafx.scene.canvas.GraphicsContext;

public interface Movable {
	public void Jump();
	void Walk(int direction);
	void render(GraphicsContext gc);

}
