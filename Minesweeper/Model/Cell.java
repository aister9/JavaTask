package Minesweeper.Model;

public class Cell {
	private CellState state = CellState.CLOSE;
	private CellType type = CellType.EMPTY;

	private boolean flagged = false;
	private int nearBomb = 0;

	public Cell() {}
	
	public Cell(Cell other) {
		this.state = other.state;
		this.type = other.type;
		this.flagged = other.flagged;
		this.nearBomb = other.nearBomb;
	}
	
	public boolean open() {
		if (state == CellState.OPEN)
			return false;
		if (flagged)
			return false;
		state = CellState.OPEN;
		return true;
	}

	public boolean close() {
		if (state == CellState.CLOSE)
			return false;
		state = CellState.CLOSE;
		return true;
	}

	public void setFlag() {
		flagged = !flagged;
	}

	public void setMine() {
		type = CellType.MINE;
	}

	public void setNumber(int number) {
		type = CellType.NUMBER;
		nearBomb = number;
	}

	public void addCount() {
		if (type != CellType.NUMBER)
			type = CellType.NUMBER;
		nearBomb += 1;
	}

	public boolean isOpen() {
		return state == CellState.OPEN;
	}

	public boolean isEmpty() {
		return type == CellType.EMPTY;
	}

	public boolean isMine() {
		return type == CellType.MINE;
	}
	
	public CellState getCellState() {
		return state;
	}
	
	public CellType getCellType() {
		return type;
	}

	public boolean isFlagged() {
		return flagged;
	}
	
	@Override
	public String toString() {
		if (state == CellState.CLOSE) {
			if (flagged)
				return "🚩";
			return "";
		} else
			return switch (type) {
			case MINE -> "💣";
			case NUMBER -> String.valueOf(nearBomb);
			default -> "";
			};
	}
}
