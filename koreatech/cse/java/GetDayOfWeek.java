package koreatech.cse.java;

import java.time.LocalDate;

public class GetDayOfWeek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.now();
		
		//1은 월요일, 7은 일요일이다.
		int dayOfWeekNumber = date.getDayOfWeek().getValue();
		
		Weekday custom = Weekday.valueOf("MON");
		System.out.println(custom);
		
		Weekday today = Weekday.values()[dayOfWeekNumber];
		switch(today) {
			case SUN, SAT:
				System.out.println("주말입니다.");
				break;
			default:
				System.out.println("평일입니다.");	
		}
	}

}
