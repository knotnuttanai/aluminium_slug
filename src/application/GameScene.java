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
	private HeroStatusPane heroStatusPane;
	public GameScene(Canvas canvas) {
		
	    hpBar = new HpBar();
		root = new Group();
		heroStatusPane = new HeroStatusPane();
		this.canvas = canvas;
		view = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		root.getChildren().add(hpBar);
		root.getChildren().add(heroStatusPane);
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
	public HpBar getHpBar() {
		return hpBar;
	}
	public void setHpBar(HpBar hpBar) {
		this.hpBar = hpBar;
	}
	public HeroStatusPane getHeroStatusPane() {
		return heroStatusPane;
	}
	public void setHeroStatusPane(HeroStatusPane heroStatusPane) {
		this.heroStatusPane = heroStatusPane;
	}
	

}
