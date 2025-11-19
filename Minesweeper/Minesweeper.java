package Minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Minesweeper.Model.GameBoard;
import Minesweeper.Model.SnapShot;
import Minesweeper.UIPrimitive.CellGrid;
import Minesweeper.UIPrimitive.SideBar;
import Minesweeper.UIPrimitive.SnapShotDialog;
import Minesweeper.UIPrimitive.TopBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Minesweeper extends Application {
	private Stage primaryStage;
	private Scene primaryScene;
	private BorderPane root = new BorderPane();
	private GameBoard board;

	private TopBar top;
	private CellGrid grid;
	private SideBar side;

	private ObjectProperty<Difficulty> difficultyProperty = new SimpleObjectProperty(Difficulty.NORMAL);
	private IntegerProperty clickCount = new SimpleIntegerProperty(0);

	private IntegerProperty elapsedSeconds = new SimpleIntegerProperty(0);
	private Timeline timer;

	private ObservableList<SnapShot> snapshots = FXCollections.observableArrayList();

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		top = new TopBar();
		root.setTop(top);
		top.timerLabelProperty().bind(elapsedSeconds.asString("⏱ %03d"));

		difficultyProperty.bindBidirectional(top.difficultyProperty());
		Difficulty d = difficultyProperty.get();
		top.setBombsCount(d.bombs);
		top.setOnNewGameBtn(e -> newGame());

		grid = new CellGrid(d);
		board = new GameBoard(d);
		grid.setOnLeftClicked((x, y) -> handleGameMouseEventXY(x, y));
		grid.setOnRightClicked((x, y) -> handleGameMouseEventXYRight(x, y));
		root.setCenter(grid);

		side = new SideBar();
		side.clickCountProperty().bind(clickCount.asString("클릭 수: %d"));
		side.setSnapshots(snapshots);
		side.setOnSnapshotSelected(this::openSnapShotDialog);
		root.setRight(side);

		primaryScene = new Scene(root);
		BorderPane.setMargin(root.getCenter(), new Insets(6));

		primaryScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		primaryStage = stage;
		
		double sceneW = d.cols * 40 + 280;
		double sceneH = d.rows * 40 + 120;
		root.setPrefSize(sceneW, sceneH);
		primaryStage.sizeToScene();
		
		stage.setScene(primaryScene);
		stage.setTitle("Minesweeper");
		stage.setResizable(false);
		stage.show();

		startTimer();
		initSnapshots();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	private void newGame() {
		Difficulty d = difficultyProperty.get();
		grid = new CellGrid(d);
		board = new GameBoard(d);
		grid.setOnLeftClicked((x, y) -> handleGameMouseEventXY(x, y));
		grid.setOnRightClicked((x, y) -> handleGameMouseEventXYRight(x, y));
		root.setCenter(grid); BorderPane.setMargin(root.getCenter(), new Insets(6));

		clickCount.set(0);
		top.setBombsCount(d.bombs);

		startTimer();

		initSnapshots();
		
	    double sceneW = d.cols * 40 + 280;
	    double sceneH = d.rows * 40 + 120;
	    root.setPrefSize(sceneW, sceneH);
	    root.autosize();
	    primaryStage.sizeToScene();
	}

	private void startTimer() {
		if (timer != null) {
			timer.stop();
		}
		elapsedSeconds.set(0);

		timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			elapsedSeconds.set(elapsedSeconds.get() + 1);
		}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
	}

	private void handleGameMouseEventXY(int x, int y) {
		System.out.println("Clicked cell: (" + x + ", " + y + ") LEFT");
		if (board.open(x, y)) {
			clickCount.set(clickCount.get() + 1);
			addSnapshot(x, y);
		}
		grid.updateState(board);

		if (board.isComplete()) {
			GameEndDialog();
		}
	}

	private void handleGameMouseEventXYRight(int x, int y) {
		System.out.println("Clicked cell: (" + x + ", " + y + ") RIGHT");
		if (board.setCellFlagged(x, y)) {
			clickCount.set(clickCount.get() + 1);
			addSnapshot(x, y);
		}
		grid.updateState(board);
	}

	private void GameEndDialog() {
		boolean lose = board.isFind();
		String result = lose ? "패배" : "승리";

		String difficultyText;
		try {
			Difficulty d = difficultyProperty.get();
			difficultyText = (d != null) ? d.toString() : (board.cols + "x" + board.rows);
		} catch (Exception e) {
			difficultyText = board.cols + "x" + board.rows;
		}

		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(root.getScene().getWindow());
		dialog.setTitle("게임 종료");
		dialog.setHeaderText("결과: " + result);

		Label clicks = new Label("클릭 횟수: " + clickCount.get());

		int totalSec = elapsedSeconds.get();
		int mm = totalSec / 60;
		int ss = totalSec % 60;
		String timeText = String.format("%02d:%02d", mm, ss);
		Label time = new Label("경과 시간: " + timeText + "초");

		Label diff = new Label("난이도: " + difficultyText);

		VBox box = new VBox(10, clicks, time, diff);
		box.setPadding(new Insets(8, 12, 8, 12));
		dialog.getDialogPane().setContent(box);

		ButtonType RESTART = new ButtonType("새 게임", ButtonData.OK_DONE);
		ButtonType CLOSE = new ButtonType("게임 종료", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().setAll(RESTART, CLOSE);

		dialog.getDialogPane().getScene().getWindow().sizeToScene();

		Optional<ButtonType> res = dialog.showAndWait();
		if (res.isPresent() && res.get() == RESTART) {
			newGame();
		}
		if (res.isPresent() && res.get() == CLOSE) {
			Platform.exit();
		}
	}

	private void initSnapshots() {
		snapshots.clear();
		snapshots.add(SnapShot.initial(board));
	}

	private void addSnapshot(int x, int y) {
		SnapShot shot = new SnapShot(x, y, board.clone(), clickCount.get());
		snapshots.add(shot);
	}

	private void restoreSnapShot(SnapShot shot) {
		this.board = shot.getBoard().clone();
		grid.updateState(board);

		this.clickCount.set(shot.getClickCount());
	}

	private void openSnapShotDialog(SnapShot shot) {
		SnapShotDialog.show(primaryStage, shot, difficultyProperty.get()).ifPresent(action -> {
			if (action == SnapShotDialog.Action.UNDO) {
				restoreSnapShot(shot);
			}
		});
	}
}
