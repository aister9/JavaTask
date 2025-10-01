package koreatech.cse.java;

public class TypeCasting {
	public static void main(String[] args) {
		double f = 13.75d;
		int n = 0;
		n = (int)(f*10);
		System.out.println(n);
		n = (int)f*10;
		System.out.println(n);
		
		int month = 8;
		int date = 0;
		switch(month) {
			case 4, 6, 9, 11: date = 30; break;
			case 2: date = 28; break;
			default: date = 31;
		}
		
	}
}
