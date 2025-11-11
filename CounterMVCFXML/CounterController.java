package CounterMVCFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CounterController implements Initializable {
	private final CounterModel model = new CounterModel();

    @FXML private Label countLabel;
    @FXML private Button plusButton;
    @FXML private Button resetButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        countLabel.textProperty().bind(model.countProperty().asString());
                
        //plusButton.setOnAction(e -> model.increment());
        //resetButton.setOnAction(e -> model.reset());
	}
	
	@FXML
	public void handleModelIncrement() {
		model.increment();
	}
	
	@FXML
	public void handleModelReset() {
		model.reset();
	}
}
