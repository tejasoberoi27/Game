package Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label {
	
	public SmallInfoLabel(String text) {
		setPrefWidth(130);
		setPrefHeight(50);
		BackgroundImage backgroundImage = new BackgroundImage(new Image("application/infolabel.png",120,50,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10,10,10,10));
		setText(text);
		setFont(new Font("Verdana",15));
		
	}
}
