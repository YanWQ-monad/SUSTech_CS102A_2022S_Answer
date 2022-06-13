import java.util.Arrays;
import java.util.List;

public class ConcreteChessGame implements ChessGame {
    private ChessComponent[][] chessComponents;
    private ChessColor currentPlayer;

    public ConcreteChessGame() {
        this.chessComponents = new ChessComponent[8][8];
        this.currentPlayer = ChessColor.WHITE;
    }

    @Override
    public void loadChessGame(List<String> chessboard) {
        for (int y = 0; y < 8; y++)
            for (int x = 0; x < 8; x++)
                this.chessComponents[x][y] = ChessComponent.construct(this, chessboard.get(x).charAt(y), x, y);

        this.currentPlayer = chessboard.get(8).equals("w") ? ChessColor.WHITE : ChessColor.BLACK;
    }

    @Override
    public ChessColor getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public ChessComponent getChess(int x, int y) {
        return this.chessComponents[x][y];
    }

    public ChessComponent getChess(ChessboardPoint point) {
        return this.getChess(point.getX(), point.getY());
    }

    @Override
    public String getChessboardGraph() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++)
                sb.append(this.chessComponents[x][y].name);
            sb.append('\n');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private int getCapturedChessCountByName(char name) {
        return (int) Arrays.stream(chessComponents).flatMap(Arrays::stream).filter(c -> c.name == name).count();
    }

    @Override
    public String getCapturedChess(ChessColor player) {
        final char[] names = new char[]{'K', 'Q', 'R', 'B', 'N', 'P'};
        final int[] total = new int[]{1, 1, 2, 2, 2, 8};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char name = player == ChessColor.WHITE ? Character.toLowerCase(names[i]) : names[i];
            int left = total[i] - this.getCapturedChessCountByName(name);
            if (left > 0)
                sb.append(name).append(" ").append(left).append('\n');
        }

        return sb.toString();
    }

    @Override
    public List<ChessboardPoint> getCanMovePoints(ChessboardPoint source) {
        List<ChessboardPoint> list = this.chessComponents[source.getX()][source.getY()].canMoveTo();
        list.sort(null);
        return list;
    }

    @Override
    public boolean moveChess(int sourceX, int sourceY, int targetX, int targetY) {
        ChessComponent chess = this.getChess(sourceX, sourceY);
        ChessboardPoint target = new ChessboardPoint(targetX, targetY);
        if (chess.getChessColor() != this.currentPlayer || !chess.canMoveTo().contains(target))
            return false;

        this.chessComponents[sourceX][sourceY] = ChessComponent.construct(this, '_', sourceX, sourceY);
        this.chessComponents[targetX][targetY] = chess;
        chess.setSource(target);
        this.currentPlayer = this.currentPlayer.getOpponent();
        return true;
    }

    public PieceStatus getPieceStatus(ChessboardPoint point, ChessboardPoint origin) {
        ChessComponent dst = this.getChess(point);
        if (dst instanceof EmptySlotComponent)
            return PieceStatus.EMPTY;
        if (dst.getChessColor() != this.getChess(origin).getChessColor())
            return PieceStatus.OPPONENT;
        return PieceStatus.OWN;
    }
}
