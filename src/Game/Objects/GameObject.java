package Game.Objects;

import javax.swing.*;
import java.awt.*;

/**
 * The class describes a game object
 */
public abstract class GameObject extends JComponent {
    protected int xCoordinate, yCoordinate;
    protected int sizeX, sizeY;
    protected Color color;

    public Color getColor() {
        return color;
    }

    @Override
    public abstract void paintComponent(Graphics g);

    public GameObject(int xCoordinate, int yCoordinate, int sizeX, int sizeY, Color color) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
    }
}
