package Game.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helpers {
    private Helpers() {}

    public static Directions generateDirections() {
        Random rand = new Random(System.currentTimeMillis());
        switch (rand.nextInt(4)) {
            case 0 -> { return Directions.LEFT; }
            case 1 -> { return Directions.TOP; }
            case 2 -> { return Directions.RIGHT; }
            default -> { return Directions.BOTTOM; } // 3 = Bottom
        }
    }

    public static Bounds getBounds(Directions directions, int shipSize) {
        switch (directions) {
            case LEFT -> { return new Bounds(4, 0, 9, 9); }
            case TOP -> { return new Bounds(0, 4, 9, 9); }
            case RIGHT -> { return new Bounds(0, 0, 6, 9); }
            default -> { return new Bounds(0, 0, 9, 6); } // Bottom
        }
    }

    public static List<Index> getIndices(Directions directions, int size, int startX, int startY) {
        List<Index> indices = new ArrayList<>(size);
        int additionX = 0, additionY = 0;

        switch (directions) {
            case LEFT -> additionX = -1;
            case RIGHT -> additionX = 1;
            case TOP -> additionY = -1;
            case BOTTOM -> additionY = 1;
        }

        for (int i = 0; i < size; ++i)
            indices.add(new Index(startY + additionY*i, startX + additionX*i));

        return indices;
    }
}
