package Application;

import javafx.scene.image.ImageView;

public class SloMo extends Token {
    private final static String SLOMO_IMAGE 	= "application/icons8-snail-48.png";
    public SloMo(int value, String name, ImageView image,GameViewManager game) {
        super(value, "SloMo", image,game);
    }
}
