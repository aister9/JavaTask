package Minesweeper.UIPrimitive;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {
	private Label sideBarName;
	private ListView listView;
	private Label clickCountLabel;
		
	public SideBar() {
		sideBarName = new Label("Played");
		listView = new ListView<>();
		ArrayList<String> list = new ArrayList<>();
		list.add("(0,0)");
		list.add("(1,1)");

		listView.setItems(FXCollections.observableArrayList(list));
		
		String s = "Click: "+list.size();
		clickCountLabel = new Label(s);
		
		getChildren().addAll(sideBarName,listView, clickCountLabel);
		setAlignment(Pos.CENTER);
		VBox.setMargin(this, new Insets(6));
	}
	
	public StringProperty clickCountProperty() {
		return clickCountLabel.textProperty();
	}
}
