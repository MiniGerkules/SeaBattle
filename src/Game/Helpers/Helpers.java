package Game.Helpers;

import java.util.*;

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
            case LEFT -> { return new Bounds(shipSize, 0, 9, 9); }
            case TOP -> { return new Bounds(0, shipSize, 9, 9); }
            case RIGHT -> { return new Bounds(0, 0, 10 - shipSize, 9); }
            default -> { return new Bounds(0, 0, 9, 10 - shipSize); } // Bottom
        }
    }

    public static List<Index> getIndices(Directions direction, int size, int startX, int startY) {
        List<Index> indices = new ArrayList<>(size);
        Map.Entry<Integer, Integer> additions = getAddition(direction);
        int additionX = additions.getKey();
        int additionY = additions.getValue();

        for (int i = 0; i < size; ++i)
            indices.add(new Index(startY + additionY*i, startX + additionX*i));

        return indices;
    }

    public static Map.Entry<Integer, Integer> getAddition(Directions direction) {
        int additionX = 0, additionY = 0;

        switch (direction) {
            case LEFT -> additionX = -1;
            case RIGHT -> additionX = 1;
            case TOP -> additionY = -1;
            case BOTTOM -> additionY = 1;
        }

        return new AbstractMap.SimpleEntry<>(additionX, additionY);
    }
}
