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

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Leaderboard implements Serializable{
    private AnchorPane lbPane;
    private Scene lbScene;
    private Stage lbStage;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private Stage menuStage;

    private ArrayList<DatedScore> leaderboardList;

    /*private final static int MENU_BUTTONS_START_X = 300;
    private final static int MENU_BUTTONS_START_Y = 200;
    List<Label> menuLabels; */



    public Leaderboard() {

        lbPane = new AnchorPane();
        lbScene = new Scene(lbPane,WIDTH,HEIGHT);
        lbStage = new Stage();
        leaderboardList = new ArrayList<DatedScore>();
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

        VBox lbLayout = new VBox();
        lbLayout.setSpacing(10);
        lbLayout.setLayoutX(50);
        lbLayout.setLayoutY(125);

        HBox heading = new HBox();
        MainPageButton scoreHeading = new MainPageButton("SCORE");
        MainPageButton dateHeading = new MainPageButton("DATE");
        heading.getChildren().addAll(scoreHeading,dateHeading);
        heading.setSpacing(120);
        lbLayout.getChildren().add(heading);


        for (int i=leaderboardList.size(); i>0; i--) {
            HBox entry = new HBox();
            MainPageButton entryScore = new MainPageButton(Integer.toString(leaderboardList.get(i).getScore()));
            MainPageButton entryDate = new MainPageButton(leaderboardList.get(i).getDate().toString());
            entry.getChildren().addAll(entryScore,entryDate);
            entry.setSpacing(120);
            lbLayout.getChildren().add(entry);
        }


        lbPane.getChildren().addAll(backButton,lbLayout);



    }

    public void addNewLeaderboardEntry(DatedScore d) {
        if (leaderboardList.size() < 10) {
            leaderboardList.add(d);
            leaderboardList.sort(Comparator.comparing(DatedScore::getScore));
        }

        else {
            if (leaderboardList.get(0).getScore() < d.getScore()) {
                leaderboardList.remove(0);
                leaderboardList.add(d);
                leaderboardList.sort(Comparator.comparing(DatedScore::getScore));
            }

        }
    }

    public void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new FileOutputStream("out.txt"));
            out.writeObject(this);
        } finally {
            out.close();
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            Leaderboard l1 = (Leaderboard) in.readObject();
        } finally {
            in.close();
        }
    }





}
