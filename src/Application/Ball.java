package Application;

import javafx.scene.image.ImageView;

public class Ball extends Token{
    private final static String BALL_IMAGE = "application/ball_bowling2.png";
    public Ball(int value,GameViewManager game) {
        super(value, "Ball", new ImageView(BALL_IMAGE),game);
        ImageView ball = this.image;
        ball.setFitHeight(40);
        ball.setFitWidth(40);
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
}
