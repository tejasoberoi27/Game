package Application;

import javafx.scene.image.ImageView;

public class BlockDestroyer extends Token{

    private final static String BLOCK_DESTROYER_IMAGE 	= "application/icons8-bulldozer-48.png";

    public BlockDestroyer(int value,GameViewManager game) {
        super(value, "BlockDestroyer", new ImageView(BLOCK_DESTROYER_IMAGE),game);
        ImageView block_destroyer = this.getImage();
        block_destroyer.setFitHeight(40);
        block_destroyer.setFitWidth(40);
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
