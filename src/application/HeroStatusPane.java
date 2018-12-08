package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class HeroStatusPane extends HBox {
	private Button increaseStr, increaseVit, increaseAgi, increaseLuk, spawnTank;
	public boolean isEnable;
	public static int statusPoint;

	public HeroStatusPane() {
		setTranslateX(0);
		setTranslateY(445);
		setPrefWidth(680);
		statusPoint = 0;
		setAlignment(Pos.CENTER);
		isEnable = true;
		increaseStr = new Button("STR");
		increaseStr.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
			if (k.getCode() == KeyCode.SPACE) {
				k.consume();
			}
			if (k.getCode() == KeyCode.RIGHT) {
				k.consume();
			}
		});
		increaseAgi = new Button("AGI");
		increaseLuk = new Button("LUK");
		increaseVit = new Button("VIT");
		spawnTank = new Button("Do not press");
		getChildren().addAll(increaseStr, increaseAgi, increaseVit, increaseLuk, spawnTank);
		setEvent();
		this.setDisable(true);
	}

	public void update() {
		if (statusPoint < 3) {
			spawnTank.setDisable(true);
		} else {
			spawnTank.setDisable(false);
		}
		if (statusPoint > 0) {
			this.setDisable(false);
		} else {
			this.setDisable(true);
		}
	}

	public void checkPoint() {
		if (statusPoint > 0) {
			statusPoint--;
		} else {
			this.setDisable(true);
		}
	}

	public void setEvent() {
		increaseStr.setOnMouseClicked(e -> {
			GameEntity.hero.setDamage(GameEntity.hero.getDamage() + 5);
			SoundManager.play("Reload", 1);
			checkPoint();
		});
		increaseAgi.setOnMouseClicked(e -> {
			GameEntity.hero.setMoveSpeed(GameEntity.hero.getMoveSpeed() - 1);
			SoundManager.play("Reload", 1);
			checkPoint();
		});
		increaseVit.setOnMouseClicked(e -> {
			GameEntity.hero.setMaxHealth(GameEntity.hero.getMaxHealth() + 40);
			GameEntity.hero.setHealth(GameEntity.hero.getMaxHealth());
			SoundManager.play("Reload", 1);
			checkPoint();
		});
		increaseLuk.setOnMouseClicked(e -> {
			SoundManager.play("Reload", 1);
			GameEntity.spawnManager.setMachineGunSpawnrate(GameEntity.spawnManager.getMachineGunSpawnrate() + 0.001);
			checkPoint();
		});
		spawnTank.setOnMouseClicked(e -> {
			SoundManager.play("Reload", 1);
			GameEntity.spawnManager.spawnTank();
			statusPoint = statusPoint - 3;
		});
	}
}
