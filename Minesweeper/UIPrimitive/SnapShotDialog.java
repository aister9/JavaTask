package Minesweeper.UIPrimitive;

import java.util.Optional;

import Minesweeper.Difficulty;
import Minesweeper.Model.GameBoard;
import Minesweeper.Model.SnapShot;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

public class SnapShotDialog extends Dialog<SnapShotDialog.Action> {
	public enum Action {
		UNDO, CLOSE
	}
	public SnapShotDialog(Window owner, SnapShot shot, Difficulty difficulty) {
		initOwner(owner);
		setTitle("스냅샷 " + shot.toString());

		String header = shot.isInitial() ? "초기 상태" : shot.toString();
		setHeaderText(header);

		CellGrid previewGrid = new CellGrid(difficulty);
		GameBoard snapBoard = shot.getBoard();
		previewGrid.updateState(snapBoard);
		previewGrid.setDisable(true);

		getDialogPane().setContent(previewGrid);

		ButtonType UNDO = new ButtonType("이 상태로 되돌리기", ButtonData.OK_DONE);
		ButtonType CLOSE = new ButtonType("닫기", ButtonData.CANCEL_CLOSE);

		getDialogPane().getButtonTypes().setAll(UNDO, CLOSE);

		setResultConverter(bt -> {
			if (bt == UNDO) {
				return Action.UNDO;
			} else {
				return Action.CLOSE;
			}
		});

	}

	public static Optional<Action> show(Window owner, SnapShot shot, Difficulty difficulty) {
		SnapShotDialog dialog = new SnapShotDialog(owner, shot, difficulty);
		return dialog.showAndWait();
	}
}
