package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class ThreeDeckShip extends Ship {
    public ThreeDeckShip(List<FieldPart> shipParts) {
        super(shipParts, 3);
    }
}
