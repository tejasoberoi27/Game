package Application;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Ball extends Token {

    private static int image_height =40;
    private static int image_width = 40;

    private final static String BALL_IMAGE = "application/ball_bowling2.png";
    public Ball(int value,GameViewManager game) {
        super(value, "Ball", new ImageView(BALL_IMAGE),game);
        ImageView ball = this.image;
        ball.setFitHeight(image_height);
        ball.setFitWidth(image_width);
//        game.getGamePane().getChildren().add(getValue());
    }

//    @Override
//    public int getNextTime() {
//
//            return game.getRandomPositionGenerator().nextInt(3)+1;
//    }

    @Override
    public int computeValue() {
            return game.findNextInt(1,5);
    }

    public static int getImage_height() {
        return image_height;
    }

    public static int getImage_width() {
        return image_width;
    }
}
