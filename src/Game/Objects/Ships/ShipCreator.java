package Game.Objects.Ships;

import java.util.*;

import Game.Helpers.Bounds;
import Game.Helpers.Directions;
import Game.Helpers.Helpers;
import Game.Helpers.Index;
import Game.Objects.FieldPart;

public class ShipCreator {
    private final Set<Integer> inaccessibleCells;

    public ShipCreator() {
        inaccessibleCells = new HashSet<>();
    }

    public Ship createShip(List<List<FieldPart>> fieldParts, int shipSize) {
        List<Index> indices = getIndices(shipSize);
        makeCellsInaccessible(indices);

        List<FieldPart> shipParts = new ArrayList<>(shipSize);
        for (int i = 0; i < shipSize; ++i) {
            Index curIndex = indices.get(i);
            shipParts.add(fieldParts.get(curIndex.getRow()).get(curIndex.getColumn()));
        }

        Ship ship;
        switch (shipSize) {
            case 1 -> ship = new SingleDeckShip(shipParts);
            case 2 -> ship = new DoubleDeckShip(shipParts);
            case 3 -> ship = new ThreeDeckShip(shipParts);
            default -> ship = new FourDeckShip(shipParts);
        }

        return ship;
    }

    private List<Index> getIndices(int shipSize) {
        Random rand = new Random();

        while (true) {
            Directions direction = Helpers.generateDirections();
            Bounds bounds = Helpers.getBounds(direction, shipSize);

            int x = rand.nextInt(bounds.getRight()) + bounds.getLeft();
            int y = rand.nextInt(bounds.getBottom()) + bounds.getTop();

            if (isFree(direction, shipSize, x, y))
                return Helpers.getIndices(direction, shipSize, x, y);
        }
    }

    private boolean isFree(Directions direction, int size, int startCol, int startRow) {
        int cellNum = startRow*10 + startCol;
        Map.Entry<Integer, Integer> additions = Helpers.getAddition(direction);
        int additionX = additions.getKey();
        int additionY = additions.getValue();

        for (int i = 0; i < size; ++i)
            if (inaccessibleCells.contains(cellNum + additionY*10*i + additionX*i))
                return false;

        return true;
    }

    private void makeCellsInaccessible(List<Index> cells) {
        for (Index cell : cells) {
            int cellNum = cell.getRow()*10 + cell.getColumn();
            inaccessibleCells.add(cellNum);

            if (cell.getRow() != 0) {
                inaccessibleCells.add(cellNum - 10); // Up cell

                if (cell.getColumn() != 0) {
                    inaccessibleCells.add(cellNum - 1); // Left cell
                    inaccessibleCells.add(cellNum - 10 - 1); // Up and left cell
                }

                if (cell.getColumn() != 9) {
                    inaccessibleCells.add(cellNum + 1); // Right cell
                    inaccessibleCells.add(cellNum - 10 + 1); // Up and right cell
                }
            }

            if (cell.getRow() != 9) {
                inaccessibleCells.add(cellNum + 10); // Down cell

                if (cell.getColumn() != 0)
                    inaccessibleCells.add(cellNum + 10 - 1); // Down and left cell

                if (cell.getColumn() != 9)
                    inaccessibleCells.add(cellNum + 10 + 1); // Down and right cell
            }
        }
    }
}
