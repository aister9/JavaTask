package ClassStudy;

import java.util.Random;
import java.util.Scanner;

final class Rock{
	final static String name = "바위";
	final int code = 0;
	@Override
	public String toString() {
		return "바위";
	}
	
	public String compareWith(Object object) {
		if(object instanceof Rock)
			return compareWith((Rock)object);
		else if(object instanceof Scissor)
			return compareWith((Scissor)object);
		else if(object instanceof Paper)
			return compareWith((Paper)object);
		else
			return ""; //error
	}
	
	public String compareWith(Rock rock) {
		return "무승부";
	}
	public String compareWith(Scissor scissor) {
		return "승리";
	}
	public String compareWith(Paper paper) {
		return "패배";
	}
}

final class Scissor{
	final static String name = "가위";
	final int code = 1;
	@Override
	public String toString() {
		return "가위";
	}

	public String compareWith(Object object) {
		if(object instanceof Rock)
			return compareWith((Rock)object);
		else if(object instanceof Scissor)
			return compareWith((Scissor)object);
		else if(object instanceof Paper)
			return compareWith((Paper)object);
		else
			return ""; //error
	}
	
	public String compareWith(Rock rock) {
		return "패배";
	}
	public String compareWith(Scissor scissor) {
		return "무승부";
	}
	public String compareWith(Paper paper) {
		return "승리";
	}
}

final class Paper{
	final static String name = "보";
	final int code = 2;
	@Override
	public String toString() {
		return "보";
	}

	public String compareWith(Object object) {
		if(object instanceof Rock)
			return compareWith((Rock)object);
		else if(object instanceof Scissor)
			return compareWith((Scissor)object);
		else if(object instanceof Paper)
			return compareWith((Paper)object);
		else
			return ""; //error
	}
	
	public String compareWith(Rock rock) {
		return "승리";
	}
	public String compareWith(Scissor scissor) {
		return "패배";
	}
	public String compareWith(Paper paper) {
		return "무승부";
	}
}

public class RPS {
	static Scanner consoleIO = new Scanner(System.in);
	static Object[] referencePool = {new Rock(), new Scissor(), new Paper()};
	static Random random = new Random();
		
	public static String getResult(Object A, Object B) {
		if(A instanceof Rock) {
			return ((Rock)A).compareWith(B);
		}else if(A instanceof Scissor) {
			return ((Scissor)A).compareWith(B);
		}else if(A instanceof Paper) {
			return ((Paper)A).compareWith(B);
		}
		else return ""; //error
	}
		
	public static void main(String[] args) {
		Rock r = new Rock();
		Scissor s = new Scissor();
		Paper p = new Paper();

        System.out.print("가위/바위/보를 선택하세요: ");
		String input = consoleIO.nextLine();
		
		Object playerSelect;
		
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
		
		Object computerSelect = referencePool[random.nextInt(3)];
		
		String result = getResult(playerSelect, computerSelect);
		
		System.out.println("플레이어의 선택:" + playerSelect);
		System.out.println("컴퓨터의 선택:" + computerSelect);
		System.out.println("결과: " + result);
	}
}