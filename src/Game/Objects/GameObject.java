package Game.Objects;

import javax.swing.*;
import java.awt.*;

/**
 * The class describes a game object
 */
public abstract class GameObject {
    protected int xCoordinate, yCoordinate;
    protected int sizeX, sizeY;
    protected Color backgroundColor;

    public GameObject(int xCoordinate, int yCoordinate, int sizeX, int sizeY, Color color) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.backgroundColor = color;
    }
}
