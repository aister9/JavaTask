module JavaFXProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	//opens HelloFX to javafx.fxml;
	
	exports HelloFX;
	exports Minesweeper;
	exports CounterMVC;
	
	opens CounterMVCFXML to javafx.fxml;
	exports CounterMVCFXML;
}