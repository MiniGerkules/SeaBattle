package Game.Objects;

import Game.Game;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FieldPart extends JButton {
    private final int row;
    private final int column;

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public FieldPart(int row, int column, ActionListener listener) {
        this.row = row;
        this.column = column;

        setBackground(Game.waterColor);
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
            return ((FieldPart) other).row == row && ((FieldPart) other).column == column;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
