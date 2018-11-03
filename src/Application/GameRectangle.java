package Application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javax.swing.text.StyledEditorKit;
import java.util.Random;

public class GameRectangle extends StackPane{

    private AnchorPane gamePane;
    private Palette colors;

//    private GameRectangle(AnchorPane gamePane) {
//        this.gamePane = gamePane;
//    }

//    private static void setPane(AnchorPane gamePane)
//    {
//        this.gamePane = gamePane;
//    }
    GameRectangle(int i, AnchorPane gamePane, Palette colors) {
        this.gamePane = gamePane;
        this.colors = colors;
        StackPane pane = new StackPane();
        Label text = new Label(nextNum());
        text.setTextFill(Color.BLACK);
//        text.setMinWidth(100);
//        text.setMinHeight(100);
        text.setFont(new Font("Cambria", 32));
        text.setStyle("-fx-font-weight: bold");
        Rectangle rect = new Rectangle(60,60);
        rect.setFill(colors.nextColor());
        rect.setArcHeight(10.0);
        rect.setArcWidth(10.0);
        // add to stackpane
        pane.getChildren().addAll(rect,text);
        pane.setLayoutY(100);
        pane.setLayoutX(60 * i);
        // display stackpane
        gamePane.getChildren().add(pane);
    }




    private String nextNum()
    {
        Random r = new Random();
        int low = 1;
        int high = 10;
        int num = (r.nextInt(high - low) + low);
        return (num+"");
    }


}
