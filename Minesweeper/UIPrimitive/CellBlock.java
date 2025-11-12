package Minesweeper.UIPrimitive;

import Minesweeper.ResourceManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CellBlock extends StackPane {
	private Rectangle bg = new Rectangle();
	private Label text = new Label();
	
	public CellBlock(){
		setMinSize(40,40);
		setMaxSize(40,40);
		getChildren().addAll(bg, text);
		setAlignment(Pos.CENTER);
		text.setFont(Font.font("Inter", FontWeight.BOLD, 16));
		bg.setWidth(38);
		bg.setHeight(38);
		bg.setStroke(Color.GRAY);
		setClosed();
		
		setOnMouseEntered(e->{bg.setScaleX(1.1f); bg.setScaleY(1.1f); bg.setStroke(Color.ROSYBROWN);});
		setOnMouseExited(e->{bg.setScaleX(1.0f);bg.setScaleY(1.0f); bg.setStroke(Color.GRAY);});
	}
	
	public void setRevealed() {
		bg.setFill(ResourceManager.getColor("Open"));
	}
	
	public void setClosed() {
		bg.setFill(ResourceManager.getColor("Close"));
	}
	
	public void setEmpty() {
		text.setText("");
	}
	
	public void setMine() {
		text.setText("💣");
        text.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 16));
	}
	
	public void setFlag() {
		text.setText("🚩");
        text.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 16));
	}
	
	public CellBlock(String text) {
		this();
		this.text.setText(text);
		this.text.setTextFill(ResourceManager.getColor(text));
	}
	
	public void setNumber(int i) {
		String numberText = String.valueOf(i);
		text.setText(numberText);
		text.setTextFill(ResourceManager.getColor(numberText));
	}
}
