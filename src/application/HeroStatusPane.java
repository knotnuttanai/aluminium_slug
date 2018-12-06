package application;

import character.Hero;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class HeroStatusPane extends HBox {
	private Button increaseStr,increaseVit, increaseAgi, increaseLuk, spawnTank;
	public static boolean isEnable;
	public HeroStatusPane() {
		setTranslateX(0);
		setTranslateY(445);
		setPrefWidth(680);
		setAlignment(Pos.CENTER);
		isEnable = true;
		increaseStr = new Button("STR");
		increaseStr.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
	        if ( k.getCode() == KeyCode.SPACE){
	            k.consume();
	        }
	        if(k.getCode() == KeyCode.RIGHT) {
	        	k.consume();
	        	
	        }
	    });
		increaseAgi = new Button("AGI");
		increaseLuk = new Button("LUK");
		increaseVit = new Button("VIT");
		spawnTank = new Button("Hi MOtherF");
		getChildren().addAll(increaseStr,increaseAgi,increaseVit,increaseLuk,spawnTank);
		setEvent();
		//this.setDisable(true);
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
