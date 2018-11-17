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

public class Block extends StackPane{

    private AnchorPane gamePane;
    private Palette colors;
    private GameViewManager game;
    private Label text;
    private Rectangle rect;
    private Snake snake;
//    private GameRectangle(AnchorPane gamePane) {
//        this.gamePane = gamePane;
//    }

//    private static void setPane(AnchorPane gamePane)
//    {
//        this.gamePane = gamePane;
//    }

    Block(int i, AnchorPane gamePane, Palette colors,GameViewManager game,Snake snake) {
        this.gamePane = gamePane;
        this.colors = colors;
        this.game = game;
        this.snake = snake;
        setValue();
        setRectangle();


        // add to stackpane
        this.getChildren().addAll(rect,text);
        this.setLayoutY(10);
        this.setLayoutX(60 * i);
        // display stackpane
        gamePane.getChildren().add(this);
    }


    private String nextNum()
    {
        Random r = new Random();
        int low = 1;
        int high;
        int length = snake.getLength();
        if(length==0)
        {
            length+=1;
        }
        high = 3*length;

        int num = (r.nextInt(high - low) + low);
        return (num+"");
    }

    public void setValue(){
        text = new Label(nextNum());
        text.setTextFill(Color.BLACK);
//        text.setMinWidth(100);
//        text.setMinHeight(100);
        text.setFont(new Font("Cambria", 32));
        text.setStyle("-fx-font-weight: bold");

    }

    private void setRectangle(){
        rect = new Rectangle(60,60);
        rect.setFill(colors.nextColor());
        rect.setArcHeight(10.0);
        rect.setArcWidth(10.0);
    }


    public void updateValue()
    {
        this.setValue();
        this.getChildren().set(1,text);

    }


}
