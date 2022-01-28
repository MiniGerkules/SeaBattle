package Game.Helpers;

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
}
