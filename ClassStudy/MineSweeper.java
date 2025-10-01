package ClassStudy;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Box{
	enum State{
		OPEN, CLOSE
	}
	private State state = State.CLOSE;
	enum Value{
		BOOM{
			@Override
			public String toString() {
				return "X";
			}
		}, NUMBER{
			@Override
			public String toString() {
				return "";
			}
		}, EMPTY{
			@Override
			public String toString() {
				return "-";
			}
		}
	}
	private Value value = Value.EMPTY;
	private int nearBoom = 0;
		
	@Override
	public String toString() {
		if(state == State.CLOSE) {
			return "[ ]";
		}
		else {
            if (value == Value.BOOM) return "[X]";
            if (value == Value.EMPTY) return "[-]";
            return "[" + nearBoom + "]";  // NUMBER일 때
		}
	}
	
	public void open() {
		state = State.OPEN;
	}
	
	public void setMine() {
		value = Value.BOOM;
	}
	
	public void setNumber(int number) {
		value = Value.NUMBER;
		nearBoom = number;
	}
	
	public void addCount() {
		if(value != Value.NUMBER) value = Value.NUMBER;
		nearBoom+=1;		
	}
	
	public boolean isOpen() {
		return state == State.OPEN;
	}
	
	public boolean isEmpty() {
		return value == Value.EMPTY;
	}
	
	public boolean isMine() {
		return value == Value.BOOM;
	}
}

class RandomGenerator {
	public final static Random rand = new Random();
	
	public final static int[] randSelect(int length_of_origin, int length_of_target) {
		int[] indices = new int[length_of_origin];
		float[] randFloat = new float[length_of_origin];
		for(int i = 0; i<length_of_origin; i++) {
			indices[i] = i;
			randFloat[i] = RandomGenerator.rand.nextFloat();
		}
		
		//selection sort
		for(int i = 0; i<length_of_origin-1; i++) {
		    int minIdx = i;
		    for (int j = i + 1; j < length_of_origin; j++) {
		        if (randFloat[j] < randFloat[minIdx]) {
		            minIdx = j;
		        }
		    }
		    // swap randFloat
		    float tmpF = randFloat[i];
		    randFloat[i] = randFloat[minIdx];
		    randFloat[minIdx] = tmpF;

		    // swap indices (키에 맞게 값도 같이 이동)
		    int tmpI = indices[i];
		    indices[i] = indices[minIdx];
		    indices[minIdx] = tmpI;
		}
		
		return Arrays.copyOf(indices, length_of_target);
	}
}

class Grid{
	private Box[][] grid = new Box[9][9];
	private final int number_of_boom;
	private int countClose = 9*9;
	private boolean isFind = false;
	
	public Grid() {
		this(10);
	}
	
	public Grid(int number_of_boom) {
		this.number_of_boom = number_of_boom;
		
		int[] minePos = RandomGenerator.randSelect(9*9, number_of_boom);
		
		//Generate mine (without redundant)
		for(int yy = 0; yy<9; yy++) {
			for(int xx =0; xx<9; xx++) {
				grid[yy][xx] = new Box();
			}
		}
		
		for(int pos : minePos) {
			int yy = pos / 9;
			int xx = pos % 9;
			
			grid[yy][xx].setMine();
			
			//propagation
			for(int yi = yy-1; yi<=yy+1; yi++) {
				for(int xi = xx-1; xi<=xx+1; xi++) {
					if(yi == yy && xi == xx)
						continue;
					if(yi<0 || yi>=9) //check valid
						continue;
					if(xi<0 || xi>=9) //check valid
						continue;
					if(!grid[yi][xi].isMine())
						grid[yi][xi].addCount();
				}
			}
		}
	}
	
	public boolean open(int x, int y) {
		if(grid[y][x].isOpen()) return false;

		grid[y][x].open();
		countClose-=1;
		
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
				if(yi<0 || yi>=9) //check valid
					continue;
				if(xi<0 || xi>=9) //check valid
					continue;
				if(!grid[yi][xi].isMine()) 
					open(xi, yi);
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int yy = 0; yy<9; yy++) {
			for(int xx =0; xx<9; xx++) {
				res += grid[yy][xx].toString();
			}
			res+="\n";
		}
		return res;
	}
	
	public boolean isComplete() {
		if(isFind) System.out.println("지뢰를 눌렀습니다.");
		else if(countClose==number_of_boom) System.out.println("모든 지뢰를 찾았습니다.");
		return isFind || (countClose==number_of_boom);
	}
}

public class MineSweeper {
	static Scanner io = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid mineSweeperGrid = new Grid();
		
		while(!mineSweeperGrid.isComplete()) {
			for (int i=0; i<25; i++) System.out.println();
			System.out.println(mineSweeperGrid);
			
			int x = io.nextInt();
			int y = io.nextInt();

			mineSweeperGrid.open(x,y);
		}
		System.out.println(mineSweeperGrid);
	}

}
