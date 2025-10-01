package koreatech.cse.java;

public enum Size {
	SMALL("S"), MEDIUM("M"), LARGE("L");
	private String abbreviation;
	private Size(String abbrev) {abbreviation = abbrev;};
	public String getAbbrev() {return abbreviation;};
}
