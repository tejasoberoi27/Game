package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private AnchorPane lbPane;
    private Scene lbScene;
    private Stage lbStage;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private Stage menuStage;

    /*private final static int MENU_BUTTONS_START_X = 300;
    private final static int MENU_BUTTONS_START_Y = 200;
    List<Label> menuLabels; */



    public Leaderboard() {

        lbPane = new AnchorPane();
        lbScene = new Scene(lbPane,WIDTH,HEIGHT);
        lbStage = new Stage();
        lbStage.setScene(lbScene);
        lbStage.setTitle("Leaderboard");



        createBackground();
        createLogo();
        createElements();
    }

    private void createBackground() {
        Image backgroundImage = new Image("application/lbBackground.jpg",WIDTH,HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        lbPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("application/lbLogo.png");
        logo.setLayoutX(35);
        logo.setLayoutY(20);

        lbPane.getChildren().add(logo);

    }

    public void createNewLeaderboard(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createLogo();
        createElements();
        lbStage.show();
    }

    private void exitLeaderboard() {
        lbStage.hide();
        this.menuStage.show();
    }

    private void createElements() {
        MainPageButton backButton = new MainPageButton("BACK");
        backButton.setLayoutX(WIDTH/2 - 90);
        backButton.setLayoutY(HEIGHT - 60);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitLeaderboard();
            }
        });

        HBox heading = new HBox();
        MainPageButton scoreHeading = new MainPageButton("SCORE");
        MainPageButton dateHeading = new MainPageButton("DATE");
        heading.getChildren().addAll(scoreHeading,dateHeading);
        heading.setSpacing(120);

        HBox entry1 = new HBox();
        MainPageButton scoreHeading1 = new MainPageButton("EMPTY");
        MainPageButton dateHeading1 = new MainPageButton("EMPTY");
        entry1.getChildren().addAll(scoreHeading1,dateHeading1);
        entry1.setSpacing(120);

        HBox entry2 = new HBox();
        MainPageButton scoreHeading2 = new MainPageButton("EMPTY");
        MainPageButton dateHeading2 = new MainPageButton("EMPTY");
        entry2.getChildren().addAll(scoreHeading2,dateHeading2);
        entry2.setSpacing(120);

        HBox entry3 = new HBox();
        MainPageButton scoreHeading3 = new MainPageButton("EMPTY");
        MainPageButton dateHeading3 = new MainPageButton("EMPTY");
        entry3.getChildren().addAll(scoreHeading3,dateHeading3);
        entry3.setSpacing(120);

        HBox entry4 = new HBox();
        MainPageButton scoreHeading4 = new MainPageButton("EMPTY");
        MainPageButton dateHeading4 = new MainPageButton("EMPTY");
        entry4.getChildren().addAll(scoreHeading4,dateHeading4);
        entry4.setSpacing(120);

        HBox entry5 = new HBox();
        MainPageButton scoreHeading5 = new MainPageButton("EMPTY");
        MainPageButton dateHeading5 = new MainPageButton("EMPTY");
        entry5.getChildren().addAll(scoreHeading5,dateHeading5);
        entry5.setSpacing(120);

        HBox entry6 = new HBox();
        MainPageButton scoreHeading6 = new MainPageButton("EMPTY");
        MainPageButton dateHeading6 = new MainPageButton("EMPTY");
        entry6.getChildren().addAll(scoreHeading6,dateHeading6);
        entry6.setSpacing(120);

        HBox entry7 = new HBox();
        MainPageButton scoreHeading7 = new MainPageButton("EMPTY");
        MainPageButton dateHeading7 = new MainPageButton("EMPTY");
        entry7.getChildren().addAll(scoreHeading7,dateHeading7);
        entry7.setSpacing(120);

        HBox entry8 = new HBox();
        MainPageButton scoreHeading8 = new MainPageButton("EMPTY");
        MainPageButton dateHeading8 = new MainPageButton("EMPTY");
        entry8.getChildren().addAll(scoreHeading8,dateHeading8);
        entry8.setSpacing(120);

        HBox entry9 = new HBox();
        MainPageButton scoreHeading9 = new MainPageButton("EMPTY");
        MainPageButton dateHeading9 = new MainPageButton("EMPTY");
        entry9.getChildren().addAll(scoreHeading9,dateHeading9);
        entry9.setSpacing(120);

        HBox entry10 = new HBox();
        MainPageButton scoreHeading10 = new MainPageButton("EMPTY");
        MainPageButton dateHeading10 = new MainPageButton("EMPTY");
        entry10.getChildren().addAll(scoreHeading10,dateHeading10);
        entry10.setSpacing(120);


        VBox lbLayout = new VBox();
        lbLayout.getChildren().addAll(heading,entry1,entry2,entry3,entry4,entry5,entry6,entry7,entry8,entry9,entry10);
        lbLayout.setSpacing(10);
        lbLayout.setLayoutX(50);
        lbLayout.setLayoutY(125);


        lbPane.getChildren().addAll(backButton,lbLayout);



    }





}
