import java.util.ArrayList;
import java.util.List;

public abstract class ChessComponent {
    public static final int[] DX = {-1, 0, 0, 1, -1, -1, 1, 1};
    public static final int[] DY = {0, -1, 1, 0, -1, 1, -1, 1};

    protected char name;
    private ChessboardPoint source;
    private ChessColor chessColor;
    protected ConcreteChessGame game;

    public ChessComponent() {}

    public static ChessComponent construct(ConcreteChessGame game, char c, int x, int y) {
        ChessComponent component = switch (Character.toUpperCase(c)) {
            case 'R' -> new RookChessComponent();
            case 'N' -> new KnightChessComponent();
            case 'B' -> new BishopChessComponent();
            case 'Q' -> new QueenChessComponent();
            case 'K' -> new KingChessComponent();
            case 'P' -> new PawnChessComponent();
            case '_' -> new EmptySlotComponent();
            default -> throw new RuntimeException("Unknown type: " + c);
        };

        component.name = c;
        component.chessColor = c == '_' ? ChessColor.NONE : (Character.isUpperCase(c) ? ChessColor.BLACK : ChessColor.WHITE);
        component.source = new ChessboardPoint(x, y);
        component.game = game;
        return component;
    }

    List<ChessboardPoint> canMoveToOneDirection(ChessboardPoint source, int dx, int dy) {
        List<ChessboardPoint> list = new ArrayList<>();
        for (int k = 1; ; k++) {
            ChessboardPoint point = source.offset(dx * k, dy * k);
            if (point == null || this.game.getPieceStatus(point, source) == PieceStatus.OWN)
                break;

            list.add(point);
            if (this.game.getPieceStatus(point, source) == PieceStatus.OPPONENT)
                break;
        }
        return list;
    }

    public abstract List<ChessboardPoint> canMoveTo();

    public ChessboardPoint getSource() {
        return source;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public void setSource(ChessboardPoint source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return String.valueOf(this.name);
    }
}
