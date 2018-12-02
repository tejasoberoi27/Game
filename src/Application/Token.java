package Application;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Token extends Component {
    protected ImageView image;

    private Label Value;


    public Token(int value, String name, ImageView image,GameViewManager game) {

        super(value, name,game,48);
        this.image = image;

    }






    public ImageView getImage() {
        return image;
    }

    @Override
    public void move() {
        ImageView Token = this.image;
//        System.out.println("Moving");
        Token.setLayoutY(Token.getLayoutY() +  game.getGameSpeedFactor() * 5);
        if (Value!=null)
        this.Value.setLayoutY(Value.getLayoutY()+ game.getGameSpeedFactor()*5);
    }

    @Override
    public double getY() {
        return this.getImage().getLayoutY();
    }

    public void setValue(Label label) {
        this.Value = label;
    }

    public Label getValue() {
        return Value;
    }

    public int getTime()
    {
        return Integer.parseInt(Value.getText());
    }

    public void setNextValue()
    {
        /* Sets value of label*/
        Label text;
        text = new Label(Integer.toString(computeValue()));
        text.setTextFill(Color.WHITE);
//        text.setMinWidth(100);
//        text.setMinHeight(100);
        text.setFont(new Font("Cambria", 18));
        text.setStyle("-fx-font-weight: bold");
        setValue(text);
    }

    public abstract int computeValue();



//    public void setTimeValue(Label timeValue) {
//        this. = timeValue;
//    }
}


