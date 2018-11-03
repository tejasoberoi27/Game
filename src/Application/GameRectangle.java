package Application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javax.swing.text.StyledEditorKit;

public class GameRectangle extends Rectangle{

    private AnchorPane gamePane;

    private GameRectangle(double width, double height, AnchorPane gamePane) {
        super(width, height);
        this.gamePane = gamePane;
    }

    GameRectangle() {

    }




    public void createRectangle(String name,AnchorPane gamePane) {
        StackPane pane = new StackPane();
        Label text = new Label(name);
        text.setTextFill(Color.WHITE);
//        text.setMinWidth(100);
//        text.setMinHeight(100);
        text.setFont(new Font("Cambria", 32));
        text.setStyle("-fx-font-weight: bold");
        GameRectangle rect = new GameRectangle(60,60,gamePane);
        rect.setFill(Color.BLUE);
        // add to stackpane
        pane.getChildren().addAll(rect,text);
        pane.setAlignment(Pos.BASELINE_CENTER);
        // display stackpane
        gamePane.getChildren().add(pane);
    }

}
