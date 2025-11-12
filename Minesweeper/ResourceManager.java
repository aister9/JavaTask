package Minesweeper;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public final class ResourceManager {
		
	private static final Map<String, Color> COLOR_MAP = new HashMap<>() {{
        put("1", Color.web("#1976D2")); // blue
        put("2", Color.web("#388E3C")); // green
        put("3", Color.web("#D32F2F")); // red
        put("4", Color.web("#512DA8")); // deep purple
        put("5", Color.web("#7B1FA2")); // purple
        put("6", Color.web("#0097A7")); // teal-ish
        put("7", Color.web("#5D4037")); // brown
        put("8", Color.web("#455A64")); // blue grey
        put("Close", Color.LIGHTGRAY);
        put("Open", Color.WHITESMOKE);
    }};
	
	private ResourceManager() {}
	
	public static Color getColor(int n) {
		return COLOR_MAP.getOrDefault(String.valueOf(n), Color.BLACK);
	}
	
	public static Color getColor(String s) {
		return COLOR_MAP.getOrDefault(s, Color.BLACK);
	}
}
