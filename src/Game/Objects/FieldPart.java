package Game.Objects;

import Game.Game;
import javax.swing.*;
import java.awt.event.ActionListener;

public class FieldPart extends JButton {
    private final int indexX;
    private final int indexY;

    public int getIndexX() {
        return indexX;
    }

    public int getIndexY() {
        return indexY;
    }

    public FieldPart(int indexX, int indexY, ActionListener listener) {
        this.indexX = indexX;
        this.indexY = indexY;

        setBackground(Game.filedColor);
        addActionListener(listener);
    }

    public void itWasShipPart () {
        setBackground(Game.hitColor);
    }

    public void itWasWater() {
        setBackground(Game.missColor);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        else if (other instanceof FieldPart)
            return ((FieldPart) other).indexX == indexX && ((FieldPart) other).indexY == indexY;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return indexX*indexY + 5*indexX - 7*indexY;
    }
}
