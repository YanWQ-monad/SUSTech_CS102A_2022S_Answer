import java.util.Objects;

public class ChessboardPoint implements Comparable<ChessboardPoint> {
    private int x;
    private int y;

    public ChessboardPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ChessboardPoint offset(int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;

        if (!(0 <= nx && nx < 8 && 0 <= ny && ny < 8))
            return null;
        return new ChessboardPoint(nx, ny);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int compareTo(ChessboardPoint o) {
        if (x != o.x) return x - o.x;
        return y - o.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessboardPoint that = (ChessboardPoint) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
