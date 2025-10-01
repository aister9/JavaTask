package koreatech.cse.java;

import java.util.StringTokenizer;

public class StringMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "apple|banna|grape";
		
		StringTokenizer tokenizer = new StringTokenizer(a, "|,");
		while(tokenizer.hasMoreTokens()) {
			System.out.println(tokenizer.nextToken());
		}
	}

}