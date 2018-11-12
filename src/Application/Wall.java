package Application;

import javafx.scene.shape.Rectangle;

public class Wall extends Component{
    private Rectangle wall;

    public Wall(int value, String name, Rectangle wall) {
        super(value, name);
        this.wall = wall;
    }
}
