package Application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Wall extends Component{
    private Rectangle wall;
    private double[] discretePositions = {30.0,90.0,150.0,210.0,270.0,330.0,390.0,450.0,510.0,570.0};

    public Wall(GameViewManager game) {
        super(0, "WALL",game,20);
        this.wall = new Rectangle();
        wall.setWidth(8);
        wall.setFill(Color.WHITE);
        setNewWallPosition(wall);
        setNewWallDimension(wall);
    }

//    public void newWall()
//    {
//        wall.setWidth(15);
//        wall.setFill(Color.WHITE);
//
//        setNewWallPosition(wall);
//        setNewWallDimension(wall);
//    }

    private void setNewWallPosition(Rectangle image) {
        int index = game.getRandomPositionGenerator().nextInt(discretePositions.length);
        image.setLayoutX(discretePositions[index]);
        image.setLayoutY(80);
//		image.setLayoutY(randomPositionGenerator.nextInt(100));
    }

    private void setNewWallDimension(Rectangle image) {

        Random r = new Random();
        int low = 30;
        int high = game.GAME_HEIGHT/3;
        wall.setHeight(r.nextInt(high - low) + low);
    }

    public Rectangle getRectangle() {
        return this.wall;
    }

    @Override
    public void move() {
        Rectangle Token = this.getRectangle();
        System.out.println("Moving");
        Token.setLayoutY(Token.getLayoutY() +  game.getGameSpeedFactor() * 5);

    }

    @Override
    public double getY() {
        return this.getRectangle().getLayoutY();
    }


}
