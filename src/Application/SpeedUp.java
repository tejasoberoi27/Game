package Application;

import javafx.scene.image.ImageView;

public class SpeedUp extends Token {
    private final static String SPEEDUP_IMAGE 	= "application/icons8-voltage-64.png";
    public SpeedUp(int value, String name, ImageView image,GameViewManager game) {
        super(5, "SpeedUp", new ImageView(SPEEDUP_IMAGE),game);
    }
}
