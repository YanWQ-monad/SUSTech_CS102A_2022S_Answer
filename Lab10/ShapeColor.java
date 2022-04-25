
import java.awt.Color;

public enum ShapeColor {
    GREEN("The shape is in the Screen", Color.GREEN),
    RED("The shape is not in the Screen", Color.RED),
    GRAY("Haven't tested", Color.GRAY);

    private String desc;  // The description of instance
    private Color color;  // The color of instance

    ShapeColor(String desc, Color color) {
        this.desc = desc;
        this.color = color;
    }

    public String getDesc() {
        return this.desc;
    }

    public Color getColor() {
        return this.color;
    }
}