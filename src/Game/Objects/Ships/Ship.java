package Game.Objects.Ships;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Game.Game;
import Game.Helpers.Shoots;
import Game.Objects.FieldPart;

public abstract class Ship {
    protected List<FieldPart> shipParts;
    protected List<FieldPart> fieldsNearby;
    protected final int size;

    public List<FieldPart> getFieldsNearby() {
        return Collections.unmodifiableList(fieldsNearby);
    }

    protected Ship(List<FieldPart> shipParts, List<FieldPart> fieldsNearby, int size) {
        if (shipParts.size() != size)
            throw new IllegalArgumentException("The ship is larger than necessary!");

        this.size = size;
        this.shipParts = new ArrayList<>(size);
        this.shipParts.addAll(shipParts);

        this.fieldsNearby = new ArrayList<>(size);
        this.fieldsNearby.addAll(fieldsNearby);
    }

    public Shoots isShipWasHit(FieldPart fieldPart) {
        for (FieldPart part : shipParts) {
            if (part == fieldPart) {
                shipParts.remove(part);
                part.setEnabled(false);

                return shipParts.size() > 0 ? Shoots.Hit : Shoots.Destroy;
            }
        }

        return Shoots.Miss;
    }

    public void show(boolean visible) {
        Color currentColor = visible ? Game.shipColor : Game.waterColor;
        for (FieldPart shipPart : shipParts)
                shipPart.setBackground(currentColor);
    }

    public void shipWasDestroyed() {
        for (FieldPart field : fieldsNearby)
            field.itWasWater();
    }
}
