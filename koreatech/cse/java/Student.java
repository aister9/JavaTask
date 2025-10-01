package koreatech.cse.java;

public class Student {
	private String name;
	private String ID;
	
	public Student(String n, String id) {
		name = n;
		ID = id;
	}
	
	@Override
	public String toString() {
		return "학번: " + ID + ", 이름: " + name;
	}
}
