package Game.Objects;

import java.util.List;

public class SingleDeckShip extends Ship {
    public SingleDeckShip(List<FieldPart> shipParts) {
        super(shipParts, 1);
    }
}
