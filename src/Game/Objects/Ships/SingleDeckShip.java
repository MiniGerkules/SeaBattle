package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class SingleDeckShip extends Ship {
    public SingleDeckShip(List<FieldPart> shipParts, List<FieldPart> fieldsNearby) {
        super(shipParts, fieldsNearby, 1);
    }
}
