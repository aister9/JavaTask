package koreatech.cse.java;

public class CountNumberOfDigits {

	public static void main(String[] args) {
		int digit = 25535;
		
		System.out.println("1의 자리 수 : " + digit%10);
		System.out.println("10의 자리 수 : " + (digit/10)%10);
		System.out.println("100의 자리 수 : " + (digit/100)%10);
		System.out.println("1000의 자리 수 : " + (digit/1000)%10);
		System.out.println("10000의 자리 수 : " + (digit/10000)%10);
	}
}
