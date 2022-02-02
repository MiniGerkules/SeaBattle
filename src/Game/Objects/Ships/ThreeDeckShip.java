package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class ThreeDeckShip extends Ship {
    public ThreeDeckShip(List<FieldPart> shipParts, List<FieldPart> fieldsNearby) {
        super(shipParts, fieldsNearby, 3);
    }
}
