package Application;

import javafx.scene.image.ImageView;

public class Multiplier extends Token{
    private final static String MULTIPLIER_IMAGE 	= "application/icons8-diamond-48.png";

    public Multiplier(int value, String name, ImageView image,GameViewManager game) {
        super(value, "Multiplier", image,game);
    }
}
