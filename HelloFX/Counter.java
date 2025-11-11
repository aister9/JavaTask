package HelloFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Counter extends Application {
	private IntegerProperty count = new SimpleIntegerProperty(0);
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Label countLabel = new Label();
		countLabel.textProperty().bind(
			count.asString()
		);
		
		Button plusButton = new Button("+");
		plusButton.setOnAction(e->count.set(count.get()+1));
		Button resetButton = new Button("reset");
		resetButton.setOnAction(e->count.set(0));
		
		HBox root = new HBox(30, countLabel, plusButton, resetButton);
		root.setAlignment(Pos.CENTER);
		
		countLabel.setFont(Font.font(32));
		plusButton.setFont(Font.font(32));
		resetButton.setFont(Font.font(32));

		Scene scene = new Scene(root, 400, 200);
		
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
