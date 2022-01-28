package Game.Objects;

import java.util.ArrayList;
import java.util.List;

import Game.Game;
import Game.Helpers.Shoots;

public abstract class Ship {
    protected List<FieldPart> shipParts;
    protected final int size;

    public int getSize() {
        return size;
    }

    protected Ship(List<FieldPart> shipParts, int size) {
        if (shipParts.size() != size)
            throw new IllegalArgumentException("The ship is larger than necessary!");

        this.size = size;
        this.shipParts = new ArrayList<>(size);

        for (FieldPart part : shipParts) {
            this.shipParts.add(part);
            part.setBackground(Game.shipColor);
        }
    }

    public Shoots shipWasHit(FieldPart fieldPart) {
        for (FieldPart part : shipParts) {
            if (part == fieldPart) {
                shipParts.remove(part);
                part.setEnabled(false);

                return shipParts.size() > 0 ? Shoots.Hit : Shoots.Destroy;
            }
        }

        return Shoots.Miss;
    }
}
