public enum ChessColor {
    BLACK, WHITE, NONE;

    public ChessColor getOpponent() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            case NONE -> NONE;
        };
    }
}
