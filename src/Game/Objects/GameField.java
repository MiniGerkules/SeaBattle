package Game.Objects;

import Game.Shot;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.util.List;

public class GameField extends GameObject {
    private final List<Shape> gridParts;
    private final List<Shot> shots;

    private final int columnWidth;
    private final int rowHeight;

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graph = (Graphics2D) g;

        graph.setPaint(color);
        for (Shape s : gridParts)
            graph.draw(s);

        for (Shot shot : shots) {
            graph.setPaint(shot.shotColor);
            graph.fill(shot.shot);
        }
    }

    public void addShot(int row, int column, Color colorToFill) {
        int x = xCoordinate + column*columnWidth;
        int y = yCoordinate + row*rowHeight;

        Shot shot = new Shot(new Rectangle2D.Float(x, y, columnWidth, rowHeight), colorToFill);
        shots.add(shot);
        repaint();
    }

    public GameField(int numColumnsAndRows, int xCoordinate, int yCoordinate, int sizeX, int sizeY, Color color) {
        super(xCoordinate, yCoordinate, sizeX, sizeY, color);
        gridParts = new ArrayList<>();
        shots = new ArrayList<>();

        // Create the game grid
        gridParts.add(new Rectangle2D.Float(xCoordinate, yCoordinate, sizeX, sizeY));
        columnWidth = sizeX / numColumnsAndRows;
        for (int i = 1; i < numColumnsAndRows; ++i) {
            int currentX = columnWidth*i + xCoordinate;
            gridParts.add(new Line2D.Float(currentX, yCoordinate, currentX, yCoordinate + sizeY));
        }

        rowHeight = sizeY / numColumnsAndRows;
        for (int i = 1; i < numColumnsAndRows; ++i) {
            int currentY = rowHeight*i + yCoordinate;
            gridParts.add(new Line2D.Float(xCoordinate, currentY, xCoordinate + sizeX, currentY));
        }
    }
}
