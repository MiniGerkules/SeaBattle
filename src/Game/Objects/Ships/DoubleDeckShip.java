package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class DoubleDeckShip extends Ship {
    public DoubleDeckShip(List<FieldPart> shipParts, List<FieldPart> fieldsNearby) {
        super(shipParts, fieldsNearby, 2);
    }
}
