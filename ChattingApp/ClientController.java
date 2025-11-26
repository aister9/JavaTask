package ChattingApp;

import java.net.URL;
import java.util.ResourceBundle;

import ChattingApp.Models.ChattingLog;
import ChattingApp.Models.ChattingMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ClientController implements Initializable {
	@FXML private ListView<ChattingMessage> messageView = new ListView<>();
	
	@FXML private TextArea sendText;
	
	private final ObservableList<ChattingMessage> messages = FXCollections.observableArrayList();
	private ChattingLog logData;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		messageView.setItems(messages);
	}
	
	public void setLogData(ChattingLog log) {
		logData = log; // set reference value;
		messages.addAll(logData.getList());
	}

	@FXML
	public void onActionSendButton() {
		String msg = sendText.getText().trim();
		if(msg.isEmpty()) return;
		
		ChattingMessage newMessage = new ChattingMessage(msg);
		messages.add(newMessage);
		logData.add(newMessage);
		
		sendText.clear();
		messageView.scrollTo(messages.size() - 1);
	}
}
