package CounterMVC;

public class CounterController {
	private final CounterModel model;
	private final CounterView view;

	public CounterController(CounterModel model, CounterView view) {
		this.model = model;
		this.view = view;

		// View ←(단방향 바인딩)— Model
		view.getCountLabel().textProperty().bind(model.countProperty().asString());

		// View 이벤트 → Model 조작
		view.getPlusButton().setOnAction(e -> model.increment());
		view.getResetButton().setOnAction(e -> model.reset());
	}
}
