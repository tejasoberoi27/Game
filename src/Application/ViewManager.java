package Application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;



public class ViewManager {

	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 300;
	private final static int MENU_BUTTONS_START_Y = 200;

	
	List<MainPageButton> menuButtons;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Snakes vs Blocks");
		//createSubScenes();
		createButtons();
		createBackground();
		createLogo();
		
		/*GameSubscene subScene = new GameSubscene();
		
		subScene.setLayoutX(100);
		subScene.setLayoutY(100);
		mainPane.getChildren().add(subScene); */
	}
	
	/*private void createSubScenes() {
		startGameSubscene = new GameSubscene();
		mainPane.getChildren().add(startGameSubscene);
		
	}*/
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void createButtons() {
		MainPageButton button1 = new MainPageButton("START GAME");		
		button1.setLayoutX(MENU_BUTTONS_START_X);
		button1.setLayoutY(MENU_BUTTONS_START_Y);
		menuButtons.add(button1);
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameViewManager gameManager = new GameViewManager();
				gameManager.createNewGame(mainStage);
			}
		});
 	
		MainPageButton button2 = new MainPageButton("RESUME GAME");		
		button2.setLayoutX(MENU_BUTTONS_START_X);
		button2.setLayoutY(MENU_BUTTONS_START_Y + 100);
		menuButtons.add(button2);
		
		MainPageButton button3 = new MainPageButton("LEADERBOARD");		
		button3.setLayoutX(MENU_BUTTONS_START_X);
		button3.setLayoutY(MENU_BUTTONS_START_Y + 200);
		menuButtons.add(button3);
		
		MainPageButton button4 = new MainPageButton("EXIT GAME");		
		button4.setLayoutX(MENU_BUTTONS_START_X);
		button4.setLayoutY(MENU_BUTTONS_START_Y + 300);
		menuButtons.add(button4);
		
		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
		
		mainPane.getChildren().addAll(button1,button2,button3,button4);
		
		
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("application/background.png",256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("application/LOGO.png");
		logo.setLayoutX(15);
		logo.setLayoutY(60);
		
		mainPane.getChildren().add(logo);
		
	}


}
