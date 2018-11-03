package Application;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubscene extends SubScene {
	
	private final static String BACKGROUND_IMAGE = "application/gamebg.jpg";
	
	private boolean isHidden;
	
	public GameSubscene() {
		super(new AnchorPane(),600,400);
		prefWidth(600);
		prefHeight(400);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		AnchorPane root = (AnchorPane)this.getRoot();
		root.setBackground(new Background(image));
		isHidden = true;
		
		setLayoutX(1024);
		setLayoutY(180);
		
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.2));
		transition.setNode(this);
		
		if (isHidden) {
			transition.setToX(-676);
			isHidden = false;
		}
		else {
			transition.setToX(0);
			isHidden = true;
		}
 		
		
		transition.play();
		
	}
}
