package Application;

import javafx.scene.image.ImageView;

public class Magnet extends Token{
    private final static String MAGNET_IMAGE 	= "application/icons8-magnetic-48.png";
    public Magnet(int value, GameViewManager game) {
        super(value, "magnet", new ImageView(MAGNET_IMAGE),game);
        ImageView magnet = this.image;
        if (isActive())
        {
            isActive = true;
        }
        magnet.setFitHeight(25);
        magnet.setFitWidth(25);
    }

    @Override
    public int computeValue() {
        return game.findNextInt(5,7);
    }

    private static boolean isActive;

    public static void setIsActiveTrue() {
        isActive = true;
    }

    public static void setIsActiveFalse() {
        isActive = false;
    }

    public static boolean isActive() {
        return isActive;
    }

}
