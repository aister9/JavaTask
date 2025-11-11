package HelloFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class HelloFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Slider slider = new Slider(0, 100, 100);
        TextField textField = new TextField("할인률");
        Label label1 = new Label("1000");
        Label label2 = new Label("할인금액");
        
        textField.textProperty().bindBidirectional(
        		slider.valueProperty(), new StringConverter<Number>() {
        			@Override public String toString(Number value) {
        				return String.valueOf(Math.round(value.doubleValue()));
        			}
        			@Override public Number fromString(String str) {
        				try {
        					return Double.parseDouble(str);
        				}catch(Exception e) {
        					return 0.0;
        				}
        			}
        		}
        		);
        
        label2.textProperty().bind(Bindings.createStringBinding(()->{
        	return Double.parseDouble(label1.getText()) * (1-slider.getValue()/100.) + "원";
        }, label1.textProperty(), slider.valueProperty()) );

        HBox root = new HBox(10, slider, textField,label1,label2);

        Button btn = new Button("Okay");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		System.out.println("Okay");
        	}
        });
        
		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.setTitle("Hello JavaFX");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
