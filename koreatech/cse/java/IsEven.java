package koreatech.cse.java;

public class IsEven {

	public static void main(String[] args) {
		int n = 4;
		boolean isEven = (n%2 == 0);
		boolean isEven2 = ((n&1) == 0);
		
		System.out.println("n은 짝수 인가요? " + isEven + ", " + isEven2);
	}
}
