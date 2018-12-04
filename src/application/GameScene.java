package application;

import character.Hero;
import character.HpBar;
import environment.Foreground;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameScene {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private HpBar hpBar;
	private GraphicsContext view;
	private static Foreground fg;
	public GameScene(Canvas canvas) {
		fg = new Foreground();
	    hpBar = new HpBar();
		root = new Group();
		this.canvas = canvas;
		view = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		root.getChildren().add(hpBar);
		scene = new Scene(root, 640, 480);
		stage = new Stage();
		stage.setScene(scene);
		
	}
	public void addEntity(GraphicsContext gc) {
		gc = canvas.getGraphicsContext2D();
	}
	public void blink() {
		view.clearRect(0, 0, 1280, 720);
		
		
	}
	public GraphicsContext getView() {
		return view;
	}
	public void setView(GraphicsContext view) {
		this.view = view;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Group getRoot() {
		return root;
	}
	public void setRoot(Group root) {
		this.root = root;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public Foreground getFg() {
		return fg;
	}
	public void setFg(Foreground fg) {
		this.fg = fg;
	}
	public static double getFgSpeed() {
		return fg.getVeloX();
	}
	public HpBar getHpBar() {
		return hpBar;
	}
	public void setHpBar(HpBar hpBar) {
		this.hpBar = hpBar;
	}
	

}
