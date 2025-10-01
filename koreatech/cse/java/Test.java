package koreatech.cse.java;

import java.util.Scanner;

public class Test {
	public static int member = 4;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("이름은?: ");
		String name = in.next();
		System.out.println("학번은?: ");
		String id = in.next();
		
		Student me = new Student(name, id);
		System.out.println(me);
		in.close();
	}
}
