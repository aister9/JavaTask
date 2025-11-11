package CounterMVC;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CounterView extends HBox {
	private final Label countLabel = new Label();
	private final Button plusButton = new Button("+");
	private final Button resetButton = new Button("reset");

	public CounterView() {
		setSpacing(40);
		setAlignment(Pos.CENTER);

		countLabel.setMinWidth(120);
		countLabel.setAlignment(Pos.CENTER);
		countLabel.setFont(Font.font(32));

		plusButton.setFont(Font.font(32));
		plusButton.setPrefSize(100, 60);

		resetButton.setFont(Font.font(24));
		resetButton.setPrefSize(140, 60);

		getChildren().addAll(countLabel, plusButton, resetButton);
	}

	public Label getCountLabel() {
		return countLabel;
	}

	public Button getPlusButton() {
		return plusButton;
	}

	public Button getResetButton() {
		return resetButton;
	}
}
