package ChattingApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import ChattingApp.Models.ChattingLog;
import ChattingApp.Models.ChattingMessage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;



public class MainApp extends Application{
	private ChattingLog logData;
	private final String logPath = "log.data";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientView.fxml"));
		Parent rootLayout = loader.load();
		
		logData = Files.exists(Path.of(logPath))
                ? new ChattingLog(logPath)
                : new ChattingLog();
				
		ClientController controller = loader.getController();
		controller.setLogData(logData);
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setTitle("Chatting Log Viewer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception{
		System.out.println("프로그램 종료 직전 실행");
		//
		if(!logData.isEmpty()) {
			try {
			logData.writeLog(logPath);}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		//
		super.stop();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
