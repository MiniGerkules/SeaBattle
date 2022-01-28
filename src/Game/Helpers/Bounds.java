package Game.Helpers;

public class Bounds {
    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    public int getLeft() {
        return left;
    }
    public int getTop() {
        return top;
    }
    public int getRight() {
        return right;
    }
    public int getBottom() {
        return bottom;
    }

    public Bounds(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
}
