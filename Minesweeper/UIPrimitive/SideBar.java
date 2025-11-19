package Minesweeper.UIPrimitive;

import java.util.ArrayList;
import java.util.function.Consumer;

import Minesweeper.Model.SnapShot;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {
	private Label sideBarName;
	private ListView<SnapShot> listView;
	private Label clickCountLabel;
    
	public SideBar() {
		sideBarName = new Label("Played");
		listView = new ListView<SnapShot>();
		
		clickCountLabel = new Label("");
		
		getChildren().addAll(sideBarName,listView, clickCountLabel);
		setAlignment(Pos.CENTER);
		VBox.setMargin(this, new Insets(6));
		
		listView.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2) {
		        SnapShot selected = listView.getSelectionModel().getSelectedItem();
		        if (selected != null && onSnapshotSelected != null) {
		            onSnapshotSelected.accept(selected);
		        }
		    }
		});

	}
	
	public StringProperty clickCountProperty() {
		return clickCountLabel.textProperty();
	}
	
	public void setSnapshots(ObservableList<SnapShot> items) {
		listView.setItems(items);
	}
	
    private Consumer<SnapShot> onSnapshotSelected;	
	public void setOnSnapshotSelected(Consumer<SnapShot> handler) {
		this.onSnapshotSelected = handler;
	}
}
