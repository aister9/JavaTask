package task2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

abstract class Gesture{
	protected String name;
	protected int code;
	
	public abstract String compareWith(Gesture other);
	public abstract String compareWith(Rock rock);
	public abstract String compareWith(Scissor scissor);
	public abstract String compareWith(Paper paper);
	
	@Override
	public String toString() {
		return name;
	}
}

class Rock extends Gesture {
	public Rock() {
		name = "바위";
		code = 0;
	}
	
	@Override public String compareWith(Gesture other) {
		return other.compareWith(this);
	}	
	@Override
	public String compareWith(Rock rock) {
		return "무승부";
	}
	@Override
	public String compareWith(Scissor scissor) {
		return "승리";
	}
	@Override
	public String compareWith(Paper paper) {
		return "패배";
	}
}

class Scissor extends Gesture{
	public Scissor() {
		name = "가위";
		code = 1;
	}

	@Override public String compareWith(Gesture other) {
		return other.compareWith(this);
	}	
	@Override
	public String compareWith(Rock rock) {
		return "패배";
	}
	@Override
	public String compareWith(Scissor scissor) {
		return "무승부";
	}
	@Override
	public String compareWith(Paper paper) {
		return "승리";
	}
}

class Paper extends Gesture{
	public Paper() {
		name = "보";
		code = 2;
	}

	@Override public String compareWith(Gesture other) {
		return other.compareWith(this);
	}	
	@Override
	public String compareWith(Rock rock) {
		return "승리";
	}
	@Override
	public String compareWith(Scissor scissor) {
		return "패배";
	}
	@Override
	public String compareWith(Paper paper) {
		return "무승부";
	}
}


public class RPSwithInheritance {
	static Scanner consoleIO = new Scanner(System.in);
	static Gesture[] referencePool = {new Rock(), new Scissor(), new Paper()};
	static Random random = new Random();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rock r = new Rock();
		Scissor s = new Scissor();
		Paper p = new Paper();

        System.out.print("가위/바위/보를 선택하세요: ");
		String input = consoleIO.nextLine();
		
		Gesture playerSelect;
		
		switch(input) {
			case "가위":
				playerSelect = referencePool[1];
				break;
			case "바위":
				playerSelect = referencePool[0];
				break;
			case "보":
				playerSelect = referencePool[2];
				break;
			default:
				System.out.println("가위 바위 보만 선택가능합니다.");
				return;
		}
		
		Gesture computerSelect = referencePool[random.nextInt(3)];
		
		System.out.println("플레이어의 선택:" + playerSelect);
		System.out.println("컴퓨터의 선택:" + computerSelect);
		System.out.println("결과: " + computerSelect.compareWith(playerSelect));
	}
}
