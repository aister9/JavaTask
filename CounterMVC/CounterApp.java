package CounterMVC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CounterApp extends Application {

	@Override
	public void start(Stage stage) {
		// TODO Auto-generated method stub
		CounterModel model = new CounterModel();
		CounterView view = new CounterView();
		CounterController controller = new CounterController(model, view); // 연결

		stage.setScene(new Scene(view, 480, 240));
		stage.setTitle("MVC Counter");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
