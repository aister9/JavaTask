package Minesweeper.Model;

public class SnapShot {
	//타이머는 계속 흐르도록 설정
	private final Integer x;
	private final Integer y;
	private final GameBoard board;
	private final int clickCount;
	
	public SnapShot(Integer x, Integer y, GameBoard board, int clickCount) {
		this.x = x;
		this.y = y;
		this.board = board.clone(); // 복사 생성자 필요
		this.clickCount = clickCount;
	}
	
	public static SnapShot initial(GameBoard board) {
		return new SnapShot(null, null, board, 0);
	}
	
    public boolean isInitial() {
        return x == null || y == null;
    }
    
    public int getX() { return x == null ? -1 : x; }
    public int getY() { return y == null ? -1 : y; }

    public GameBoard getBoard() { return board; }
    public int getClickCount() { return clickCount; }
    
    @Override
    public String toString() {
        return isInitial() ? "None" : "(" + x + ", " + y + ") Click : " + clickCount;
    }

}
