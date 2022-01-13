package Game.Objects;

import java.awt.*;

public class Boat extends GameObject {
    private int size;

    @Override
    public void paintComponent(Graphics g) {

    }

    public Boat(int xCoordinate, int yCoordinate, int sizeX, int sizeY, Color color) {
        super(xCoordinate, yCoordinate, sizeX, sizeY, color);
    }
}
