package Game.Objects.Ships;

import java.util.*;

import Game.Helpers.Bounds;
import Game.Helpers.Directions;
import Game.Helpers.Helpers;
import Game.Helpers.Index;
import Game.Objects.FieldPart;

public class ShipCreator {
    private final List<Index> inaccessibleCells;

    public ShipCreator() {
        inaccessibleCells = new ArrayList<>();
    }

    public Ship createShip(List<List<FieldPart>> fieldParts, int shipSize) {
        List<Index> indices = getIndices(shipSize);
        List<Index> inaccessible = getInaccessibleCells(indices);

        inaccessibleCells.addAll(inaccessible);
        inaccessible.removeAll(indices);

        List<FieldPart> shipParts = getParts(fieldParts, indices);
        List<FieldPart> partsNearby = getParts(fieldParts, inaccessible);

        Ship ship;
        switch (shipSize) {
            case 1 -> ship = new SingleDeckShip(shipParts, partsNearby);
            case 2 -> ship = new DoubleDeckShip(shipParts, partsNearby);
            case 3 -> ship = new ThreeDeckShip(shipParts, partsNearby);
            default -> ship = new FourDeckShip(shipParts, partsNearby);
        }

        return ship;
    }

    private List<FieldPart> getParts(List<List<FieldPart>> fieldParts, List<Index> indices) {
        List<FieldPart> parts = new ArrayList<>(indices.size());

        for (Index index : indices)
            parts.add(fieldParts.get(index.getRow()).get(index.getColumn()));

        return parts;
    }

    private List<Index> getIndices(int shipSize) {
        Random rand = new Random();

        while (true) {
            Directions direction = Helpers.generateDirections();
            Bounds bounds = Helpers.getBounds(direction, shipSize);

            int x = rand.nextInt(bounds.getRight() - bounds.getLeft() + 1) + bounds.getLeft();
            int y = rand.nextInt(bounds.getBottom() - bounds.getTop() + 1) + bounds.getTop();

            if (isFree(direction, shipSize, x, y))
                return Helpers.getIndices(direction, shipSize, x, y);
        }
    }

    private boolean isFree(Directions direction, int size, int startCol, int startRow) {
        Map.Entry<Integer, Integer> additions = Helpers.getAddition(direction);
        int additionX = additions.getKey();
        int additionY = additions.getValue();

        for (int i = 0; i < size; ++i) {
            Index indexToCheck = new Index(startRow + additionY*i, startCol + additionX*i);
            if (inaccessibleCells.contains(indexToCheck))
                return false;
        }

        return true;
    }

    private List<Index> getInaccessibleCells(List<Index> cells) {
        List<Index> inaccessible = new ArrayList<>();

        for (Index cell : cells) {
            int cellRow = cell.getRow();
            int cellCol = cell.getColumn();
            inaccessible.add(cell);

            if (cellCol != 0)
                inaccessible.add(new Index(cellRow, cellCol - 1)); // Left cell
            if (cellCol != 9)
                inaccessible.add(new Index(cellRow, cellCol + 1)); // Right cell

            if (cellRow != 0) {
                inaccessible.add(new Index(cellRow - 1, cellCol)); // Up cell
                if (cellCol != 0)
                    inaccessible.add(new Index(cellRow - 1, cellCol - 1)); // Up and left cell
                if (cellCol != 9)
                    inaccessible.add(new Index(cellRow - 1, cellCol + 1)); // Up and right cell
            }

            if (cellRow != 9) {
                inaccessible.add(new Index(cellRow + 1, cellCol)); // Down cell
                if (cellCol != 0)
                    inaccessible.add(new Index(cellRow + 1, cellCol - 1)); // Down and left cell
                if (cellCol != 9)
                    inaccessible.add(new Index(cellRow + 1, cellCol + 1)); // Down and right cell
            }
        }

        return inaccessible;
    }
}
