package Application;

import javafx.scene.image.ImageView;

public class Ball extends Token{
    private final static String BALL_IMAGE = "application/ball_bowling2.png";
    public Ball(int value,GameViewManager game) {
        super(value, "Ball", new ImageView(BALL_IMAGE),game);
        ImageView ball = this.image;
        ball.setFitHeight(25);
        ball.setFitWidth(25);
    }

//    @Override
//    public int getNextTime() {
//
//            return game.getRandomPositionGenerator().nextInt(3)+1;
//    }

    @Override
    public int computeValue() {
            return game.getRandomPositionGenerator().nextInt(3)+1;
    }
}
