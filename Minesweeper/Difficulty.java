package Minesweeper;

public enum Difficulty {
	EASY(9, 9, 10, "쉬움 (9x9)"),
    NORMAL(16, 16, 40, "보통 (16x16)"),
    HARD(30, 16, 99, "어려움 (30x16)");

    public final int cols, rows, bombs;
    private final String label;
    Difficulty(int cols, int rows, int bombs, String label){
        this.cols = cols; this.rows = rows; this.bombs = bombs; this.label = label;
    }
    @Override public String toString(){ return label; }
}
