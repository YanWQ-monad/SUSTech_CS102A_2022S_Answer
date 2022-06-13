import java.util.ArrayList;
import java.util.List;

public class RookChessComponent extends ChessComponent {
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> list = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            list.addAll(this.canMoveToOneDirection(this.getSource(), DX[i], DY[i]));
        return list;
    }
}
