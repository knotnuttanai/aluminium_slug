package application;

import character.Hero;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class HeroStatusPane extends HBox {
	private Button increaseStr,increaseVit, increaseAgi, increaseLuk, spawnTank;
	public boolean isEnable;
	public HeroStatusPane() {
		setTranslateX(0);
		setTranslateY(450);
		setPrefWidth(680);
		setAlignment(Pos.CENTER);
		isEnable = false;
		increaseStr = new Button("STR");
		increaseAgi = new Button("AGI");
		increaseLuk = new Button("LUK");
		increaseVit = new Button("VIT");
		spawnTank = new Button("Hi MOtherF");
		getChildren().addAll(increaseStr,increaseAgi,increaseVit,increaseLuk,spawnTank);
		setEvent();
		this.setDisable(true);
	}
	
	public void update() {
		if(isEnable) {
			this.setDisable(false);
		}else {
			this.setDisable(true);
		}
	}
	public void setEvent() {
		increaseStr.setOnMouseClicked(e->{
			for(Hero h : GameEntity.hero) {
				h.setDmg(h.getDmg()+5);
			}
			isEnable = false;
		});
		increaseAgi.setOnMouseClicked(e->{
			for(Hero h : GameEntity.hero) {
				h.setMoveSpeed(h.getMoveSpeed()-1);
			}
			isEnable = false;
		});
		increaseVit.setOnMouseClicked(e->{
			for(Hero h : GameEntity.hero) {
				h.setMaxHealth(h.getMaxHealth()+20);
			}
			isEnable = false;
		});
	
	}
}
