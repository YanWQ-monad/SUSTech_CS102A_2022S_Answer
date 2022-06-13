import java.util.ArrayList;
import java.util.List;

public class PawnChessComponent extends ChessComponent {
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();

        int delta = this.getChessColor() == ChessColor.BLACK ? 1 : -1;
        int base = this.getChessColor() == ChessColor.BLACK ? 1 : 6;

        // move
        ChessboardPoint src = this.getSource();
        ChessboardPoint point = src.offset(delta, 0);
        if (point != null && this.game.getPieceStatus(point, src) == PieceStatus.EMPTY) {
            list.add(point);

            point = src.offset(delta * 2, 0);
            if (src.getX() == base && point != null && this.game.getPieceStatus(point, src) == PieceStatus.EMPTY)
                list.add(point);
        }

        // attack
        final int[] DY = new int[]{-1, 1};
        for (int i = 0; i < 2; i++) {
            point = src.offset(delta, DY[i]);
            if (point != null && this.game.getPieceStatus(point, src) == PieceStatus.OPPONENT)
                list.add(point);
        }

        return list;
    }
}
