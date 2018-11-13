package Application;

import javafx.scene.image.ImageView;

public class Token extends Component {
    private ImageView image;
    private boolean isActive;

    public Token(int value, String name, ImageView image) {
        super(value, name);
        this.image = image;
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggle() {
        isActive = !(isActive);
    }

    public ImageView getImage() {
        return image;
    }
}
