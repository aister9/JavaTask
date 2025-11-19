package Minesweeper.UIPrimitive;

import Minesweeper.Difficulty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class TopBar extends HBox {
	private Button newGameBtn;
	private ComboBox<Difficulty> difficultyCombo;
	private Label timerLabel;
	private Label bombsLabel;
	
	public TopBar() {
		super();
		newGameBtn = new Button("New Game");
		newGameBtn.setPrefHeight(34);
		
		difficultyCombo = new ComboBox<>();
		difficultyCombo.getItems().addAll(Difficulty.values());
		difficultyCombo.getSelectionModel().selectFirst();
		difficultyCombo.setPrefHeight(34);
		
		difficultyCombo.setOnAction(e->System.out.println(difficultyProperty().get()));
		
		HBox left = new HBox(8, newGameBtn, difficultyCombo);
		left.setAlignment(Pos.CENTER_LEFT);
		
		timerLabel = new Label("⏱ 000");
		bombsLabel = new Label("💣 010");

		timerLabel.setPadding(new Insets(6, 10, 6, 10));
		bombsLabel.setPadding(new Insets(6, 10, 6, 10));
		timerLabel.getStyleClass().add("Counter-Label");
		bombsLabel.getStyleClass().add("Counter-Label");
		
		HBox right = new HBox(12, bombsLabel, timerLabel);
		right.setAlignment(Pos.CENTER_RIGHT);
		
		HBox.setHgrow(left, Priority.SOMETIMES);
		HBox.setHgrow(right, Priority.ALWAYS);
		getChildren().addAll(left, new Region(), right);
		setPadding(new Insets(10, 12, 6, 12));
	}
	
	public ObjectProperty<Difficulty> difficultyProperty(){
		return difficultyCombo.valueProperty();
	}
	
	public void setBombsCount(int i) {
		bombsLabel.setText("💣 " + i);
	}
	
	public StringProperty bombsLabelProperty() {
		return bombsLabel.textProperty();
	}
	
	public StringProperty timerLabelProperty() {
		return timerLabel.textProperty();
	}
	
	public void setOnNewGameBtn(EventHandler<ActionEvent> e) {
		newGameBtn.setOnAction(e);
	}
}
