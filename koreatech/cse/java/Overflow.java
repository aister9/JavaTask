package koreatech.cse.java;

import java.math.BigInteger;

public class Overflow {

	public static void main(String[] args) {
		int a = Integer.MAX_VALUE;
		int b = 1;
		if(a > Integer.MAX_VALUE - b) {
			System.out.println("오버플로우 발생 가능성 있음!");
		}else {
			System.out.println("결과: " + (a+b));
		}
	}
}
