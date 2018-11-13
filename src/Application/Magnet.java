package Application;

import javafx.scene.image.ImageView;

public class Magnet extends Token{
    private final static String MAGNET_IMAGE 	= "application/icons8-magnetic-48.png";
    public Magnet(int value, GameViewManager game) {
        super(value, "magnet", new ImageView(MAGNET_IMAGE),game);
        ImageView magnet = this.image;
        magnet.setFitHeight(25);
        magnet.setFitWidth(25);
    }
}
