import java.util.ArrayList;
import java.util.List;

public class KingChessComponent extends ChessComponent {
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ChessboardPoint point = this.getSource().offset(DX[i], DY[i]);
            if (point != null && this.game.getPieceStatus(point, this.getSource()) != PieceStatus.OWN)
                list.add(point);
        }

        return list;
    }
}
