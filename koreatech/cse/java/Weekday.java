package koreatech.cse.java;

public enum Weekday {
	MON{
		@Override
		public String toString() {
			return "월요일";
		}
	}
	,TUE,WED,THU,FRI,SAT,
	SUN{
		@Override
		public String toString() {
			return "일요일";
		}		
	};
}
