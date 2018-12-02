package Application;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Coin extends Token implements Serializable{
    private final static String COIN_IMAGE 	= "application/coin.png";
    public Coin(int value, GameViewManager game) {
        super(value, "coin", new ImageView(COIN_IMAGE),game);
//        Radius = 12;
        ImageView coin = this.image;
//        coin.setFitHeight(25);
//        coin.setFitWidth(25);
        coin.setFitHeight(40);
        coin.setFitWidth(40);
//        game.getGamePane().getChildren().add(getValue());
    }

    @Override
    public int computeValue() {
        return game.findNextInt(1,5);
    }
}
