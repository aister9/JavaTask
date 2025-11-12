package Minesweeper.Model;

import Minesweeper.Difficulty;
import Minesweeper.RandomGenerator;
import javafx.beans.property.IntegerProperty;

public class GameBoard {
	private Cell[][] grid;
	private int number_of_bomb;
	private int notOpened;
	private boolean isFind = false;
	private int flagCount = 0;
	public final int rows;
	public final int cols;
	
	public GameBoard(){
		this(9,9,10);
	}
	
	public GameBoard(int cols, int rows, int bombs) {
		this.rows = rows;
		this.cols = cols;
		number_of_bomb = bombs;
		notOpened = rows*cols;
		grid = new Cell[rows][cols];
		
		for(int r = 0; r<rows; r++) {
			for(int c = 0; c<cols; c++) {
				grid[r][c] = new Cell();
			}
		}
		
		//지뢰 배치
		int[] minePos = RandomGenerator.computeRandomArray(notOpened, bombs);
		for(int pos : minePos) {
			int yy = pos / cols;
			int xx = pos % cols;
			
			grid[yy][xx].setMine();
			
			//propagation
			for(int yi = yy-1; yi<=yy+1; yi++) {
				for(int xi = xx-1; xi<=xx+1; xi++) {
					if(yi == yy && xi == xx)
						continue;
					if(yi<0 || yi>=rows) //check valid
						continue;
					if(xi<0 || xi>=cols) //check valid
						continue;
					if(!grid[yi][xi].isMine())
						grid[yi][xi].addCount();
				}
			}
		}
	}
	
	public GameBoard(Difficulty d) {
		this(d.cols, d.rows, d.bombs);
	}
	
	public boolean open(int x, int y) {
		if(grid[y][x].isOpen()) return false;

		if(grid[y][x].open())
			notOpened-=1;
		else return false;
		
	    if (grid[y][x].isMine()) {
	    	isFind = true;
	        return false;
	    }

	    if ((!grid[y][x].isEmpty()) && !grid[y][x].isMine()) {
	        return true;
	    }

		for(int yi = y-1; yi<=y+1; yi++) {
			for(int xi = x-1; xi<=x+1; xi++) {
				if(yi == y && xi == x)
					continue;
				if(yi<0 || yi>=rows) //check valid
					continue;
				if(xi<0 || xi>=cols) //check valid
					continue;
				if(!grid[yi][xi].isMine() || !grid[yi][xi].isFlagged()) 
					open(xi, yi);
			}
		}
		return true;
	}
	
	public CellState getCellState(int x, int y) {
		return grid[y][x].getCellState();
	}
	
	public CellType getCellType(int x, int y) {
		return grid[y][x].getCellType();
	}
	
	public boolean getCellFlagged(int x, int y) {
		return grid[y][x].isFlagged();
	}
	
	public boolean setCellFlagged(int x, int y) {
		if(!grid[y][x].isOpen()) {
			grid[y][x].setFlag();
			if(grid[y][x].isFlagged()) flagCount++;
			else flagCount--;
			return true;
		}
		return false;
	}
	
	public Cell getCell(int x, int y) {
		return grid[y][x];
	}
	
	public static GameBoard buildEasyBoard() {
		return new GameBoard();
	}
	
	public static GameBoard buildMediumBoard() {
		return new GameBoard(16,16,40);
	}
	
	public static GameBoard buildInsaneBoard() {
		return new GameBoard(30,16,99);
	}
		
	public boolean isComplete() {
		//if(isFind) System.out.println("지뢰를 눌렀습니다.");
		//else if(notOpened==number_of_bomb) System.out.println("모든 지뢰를 찾았습니다.");
		return isFind || (notOpened==number_of_bomb);
	}
	
	public boolean isFind() {
		return isFind;
	}
}
