package Application;

import javafx.scene.image.ImageView;

public class Multiplier extends Token{
    private final static String MULTIPLIER_IMAGE 	= "application/icons8-diamond-48.png";

    public Multiplier(int value, GameViewManager game) {
        super(value, "Multiplier", new ImageView(MULTIPLIER_IMAGE),game);
        ImageView multiplier = this.image;
        multiplier.setFitHeight(25);
        multiplier.setFitWidth(25);
    }

    @Override
    public int computeValue() {
        return 0;
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
