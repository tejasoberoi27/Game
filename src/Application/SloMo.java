package Application;

import javafx.scene.image.ImageView;

public class SloMo extends Token {
    private final static String SLOMO_IMAGE 	= "application/icons8-snail-48.png";
    public SloMo(int value,GameViewManager game) {
        super(value, "SloMo", new ImageView(SLOMO_IMAGE),game);
        ImageView slomo = this.image;
        slomo.setFitHeight(25);
        slomo.setFitWidth(25);
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
