package Game.Objects;

import java.util.*;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.*;

public class GameObjectsContainer extends GameObject {
    private final List<GameObject> allObjects;
    private final List<Shape> containerParts;
    private final Color boundsColor;

    public List<GameObject> getAllObjects() {
        return Collections.unmodifiableList(allObjects);
    }

    public void addObject(GameObject newObject) {
        allObjects.add(newObject);
    }

    public List<Shape> getContainerParts() {
        return Collections.unmodifiableList(containerParts);
    }

    public Color getBoundsColor() {
        return boundsColor;
    }

    public GameObjectsContainer(int xCoordinate, int yCoordinate, int sizeX, int sizeY, Color color) {
        super(xCoordinate, yCoordinate, sizeX, sizeY, color);
        boundsColor = new Color(color.getRGB() + 5);

        // Init lists
        allObjects = new ArrayList<>(10);
        containerParts = new ArrayList<>();

        final int numOfColumns = 10;
        final int numOfRows = 10;

        // Create the game grid
        containerParts.add(new Rectangle2D.Float(xCoordinate, yCoordinate, sizeX, sizeY));
        for (int i = 0; i < (numOfColumns - 1); ++i) {
            int currentX = sizeX / numOfColumns * i;
            containerParts.add(new Line2D.Float(currentX, 0, currentX, sizeY));
        }
        for (int i = 0; i < (numOfRows - 1); ++i) {
            int currentY = sizeY / numOfRows * i;
            containerParts.add(new Line2D.Float(0, currentY, sizeX, currentY));
        }
    }
}
