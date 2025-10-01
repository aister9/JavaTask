package ClassStudy;

import Geometry.Circle;

class A{
	public String foo() {
		return "A";
	}
}

class B{
	public String foo() {
		return "B";
	}
}


public class Sample {
	
	public static String objectTypeTest(Object object) {
		String returnString = "";
		if(object instanceof A) {
			returnString=((A)object).foo();
		}else if(object instanceof B) {
			returnString=((B)object).foo();
		}
		return returnString;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TestClass A = new TestClass(); // error
		
		Object data1 = new A();
		Object data2 = new B();
		
		A data3 = new A();
		data3.getClass();
		
		String temp = ((A) data1).foo();
		System.out.println(data1.getClass().getTypeName());
		
		System.out.println(objectTypeTest(data1));
		System.out.println(objectTypeTest(data2));
		
	}
}
