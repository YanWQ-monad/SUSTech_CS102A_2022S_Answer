import java.util.ArrayList;
import java.util.List;

public class QueenChessComponent extends ChessComponent {
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            list.addAll(this.canMoveToOneDirection(this.getSource(), DX[i], DY[i]));
        return list;
    }
}
