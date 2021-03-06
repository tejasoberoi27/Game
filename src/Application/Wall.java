package Application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Wall extends Component{
    private Rectangle wall;

    public Wall(GameViewManager game) {
        super(0, "WALL",game);
        this.wall = new Rectangle();
        wall.setWidth(15);
        wall.setFill(Color.WHITE);

        setNewWallPosition(wall);
        setNewWallDimension(wall);
    }

    private void setNewWallPosition(Rectangle image) {
        image.setLayoutX(game.randomPositionGenerator.nextInt(game.GAME_WIDTH));
        image.setLayoutY(10);
//		image.setLayoutY(randomPositionGenerator.nextInt(100));
    }

    private void setNewWallDimension(Rectangle image) {

        Random r = new Random();
        int low = 20;
        int high = game.GAME_HEIGHT/2;
        wall.setHeight(r.nextInt(high - low) + low);
    }

    public Rectangle getRectangle() {
        return wall;
    }

    @Override
    public void move() {

    }


}
