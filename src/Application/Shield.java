package Application;

import javafx.scene.image.ImageView;

public class Shield extends Token {
    private final static String SHIELD_IMAGE 	= "application/icons8-shield-48.png";
    public Shield(int value, String name, ImageView image,GameViewManager game) {
        super(value, "Shield", image,game);
    }
}
