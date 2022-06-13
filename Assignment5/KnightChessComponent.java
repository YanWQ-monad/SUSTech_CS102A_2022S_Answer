import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnightChessComponent extends ChessComponent {
    public static final int[] DX = {-1, 1, -2, 2, -2, 2, -1, 1};
    public static final int[] DY = {-2, -2, -1, -1, 1, 1, 2, 2};

    @Override
    public List<ChessboardPoint> canMoveTo() {
        // goto `KingChessComponent.canMoveTo()` to see the equivalent version
        return IntStream.range(0, 8)
                .mapToObj(i -> this.getSource().offset(DX[i], DY[i]))
                .filter(p -> p != null && this.game.getPieceStatus(p, this.getSource()) != PieceStatus.OWN)
                .collect(Collectors.toList());
    }
}
