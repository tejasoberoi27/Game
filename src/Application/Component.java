package Application;

public abstract class Component {
    int value;
    String name;
    protected GameViewManager game;

    public Component(int value, String name,GameViewManager game) {
        this.value = value;
        this.name = name;
        this.game = game;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
}
