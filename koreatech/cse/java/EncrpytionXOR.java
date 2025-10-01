package koreatech.cse.java;

public class EncrpytionXOR {
	public static void main(String[] args) {
		int A = 1000;
		int password = 0x3B3C;
		
		int encoded = A^password;
		
		System.out.println("데이터 : " + encoded);
		System.out.println("원본 데이터 : " + (encoded^password));
	}
}
