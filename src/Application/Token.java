package Application;

import javafx.scene.image.ImageView;

public class Token extends Component {
    private ImageView image;

    public Token(int value, String name, ImageView image) {
        super(value, name);
        this.image = image;
    }
}
