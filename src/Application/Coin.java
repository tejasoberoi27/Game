package Application;

import javafx.scene.image.ImageView;

public class Coin extends Token{
    private final static String COIN_IMAGE 	= "application/coin.png";
    public Coin(int value, GameViewManager game) {
        super(value, "coin", new ImageView(COIN_IMAGE),game);
    }
}
