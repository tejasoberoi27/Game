package Application;

import javafx.scene.image.ImageView;


public class SpeedUp extends Token {
    private final static String SPEEDUP_IMAGE 	= "Application/icons8-voltage-64.png";
    public SpeedUp(int value, GameViewManager game) {
        super(value, "SpeedUp", new ImageView(SPEEDUP_IMAGE),game);
        ImageView speedup = this.image;
        speedup.setFitHeight(40);
        speedup.setFitWidth(40);
//        this.image = speedup;
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

