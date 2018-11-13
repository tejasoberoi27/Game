package Application;

import javafx.scene.image.ImageView;

public class Magnet extends Token{
    private final static String MAGNET_IMAGE 	= "application/icons8-magnetic-48.png";
    public Magnet(int value, String name, ImageView image,GameViewManager game) {
        super(0, "magnet", new ImageView(MAGNET_IMAGE),game);
    }
}
