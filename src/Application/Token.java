package Application;

import javafx.scene.image.ImageView;

public class Token extends Component {
    protected ImageView image;
    private boolean isActive;


    public Token(int value, String name, ImageView image,GameViewManager game) {

        super(value, name,game);
        this.image = image;
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggle() {
        isActive = !(isActive);
    }

    public ImageView getImage() {
        return image;
    }

    @Override
    public void move() {
        ImageView Token = this.image;
        if (Token != null) {
            Token.setLayoutY(Token.getLayoutY() +  game.getGameSpeedFactor() * 5);
        }
    }


}
