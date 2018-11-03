package Application;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MainPageButton extends Button{

	private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('application/red_button12.png');";
	
	public MainPageButton(String text) {
		setText(text);
		setFont(new Font("Verdana",20));
		setPrefWidth(190);
		setPrefHeight(45);
		setStyle(BUTTON_STYLE);
		initializeButtonListeners();
	}

	private void initializeButtonListeners() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					//call subscene
				}
			}
		});
	}
	
	
}

