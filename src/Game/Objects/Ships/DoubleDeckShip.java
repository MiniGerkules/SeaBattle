package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class DoubleDeckShip extends Ship {
    public DoubleDeckShip(List<FieldPart> shipParts) {
        super(shipParts, 2);
    }
}
