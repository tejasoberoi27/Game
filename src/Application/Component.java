package Application;

import javafx.scene.control.Label;

public abstract class Component {
    int value;
    String name;
    protected GameViewManager game;
    protected static int Radius;

    public Component(int value, String name,GameViewManager game,int Radius) {
        this.value = value;
        this.name = name;
        this.game = game;
        this.Radius = Radius;
    }

    public int getValueInt() {
        return value;
    }

    public void setValueInt(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void move();

    public abstract double getY();

    @Override
    public String toString() {
        return "Component{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", game=" + game +
                '}';
    }

    public static int getRadius() {
        return Radius;
    }



    

}
