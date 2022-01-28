package Game.Objects.Ships;

import java.util.List;
import Game.Objects.FieldPart;

public class FourDeckShip extends Ship {
    public FourDeckShip(List<FieldPart> shipParts) {
        super(shipParts, 4);
    }
}
