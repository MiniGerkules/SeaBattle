package Game.Helpers;

import java.util.Objects;

public class Index {
    private final int column;
    private final int row;

    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }

    public Index(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return column == index.column && row == index.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
