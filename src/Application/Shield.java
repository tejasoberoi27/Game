package Application;

import javafx.scene.image.ImageView;

public class Shield extends Token {
    private final static String SHIELD_IMAGE 	= "Application/icons8-shield-48.png";
    public Shield(int value, GameViewManager game) {
        super(value, "Shield", new ImageView(SHIELD_IMAGE) ,game);
        ImageView shield = this.image;
//        if(shield==null)
//        {
//            System.out.println("why?");
//        }
        shield.setFitHeight(40);
        shield.setFitWidth(40);
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
