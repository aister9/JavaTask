package Minesweeper.UIPrimitive;

import java.util.function.BiConsumer;

import Minesweeper.Difficulty;
import Minesweeper.Model.GameBoard;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class CellGrid extends GridPane {
	private CellBlock[][] grids;
	private BiConsumer<Integer, Integer> onLeftClicked;
	private BiConsumer<Integer, Integer> onRightClicked;

	private int rows;
	private int cols;

	public CellGrid() {
	}

	public CellGrid(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		grids = new CellBlock[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grids[i][j] = new CellBlock();
				add(grids[i][j], j, i);

				final int x = j;
				final int y = i;

				grids[i][j].setOnMouseClicked(e -> {
					switch (e.getButton()) {
					case PRIMARY:
						if (onLeftClicked != null)
							onLeftClicked.accept(x, y);
						break;
					case SECONDARY:
						if (onRightClicked != null)
							onRightClicked.accept(x, y);
						break;
					default:
						break;
					}
				});
			}
		}

		setAlignment(Pos.CENTER);
		getStyleClass().add("cell-grid");
		
		playSpawnAnimation();
	}

	public CellGrid(Difficulty d) {
		this(d.cols, d.rows);
	}

	// For binding
	public void setOnLeftClicked(BiConsumer<Integer, Integer> handler) {
		this.onLeftClicked = handler;
	}
	public void setOnRightClicked(BiConsumer<Integer, Integer> handler) {
		this.onRightClicked = handler;
	}

	public void updateState(GameBoard board) {
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				switch (board.getCellState(x, y)) {
				case OPEN:
					grids[y][x].setRevealed();
					switch (board.getCellType(x, y)) {
					case MINE:
						grids[y][x].setMine();
						break;
					case NUMBER:
						grids[y][x].setNumber(Integer.parseInt(board.getCell(x, y).toString()));
						break;
					default:
						grids[y][x].setEmpty();
						break;
					}
					break;
				case CLOSE:
					grids[y][x].setClosed();
					if (board.getCellFlagged(x, y))
						grids[y][x].setFlag();
					else
						grids[y][x].setEmpty();
					break;
				default:
					break;
				}
			}
		}
	}
	
	private void playSpawnAnimation() {
	    Platform.runLater(() -> {
	        for (int y = 0; y < rows; y++) {
	            for (int x = 0; x < cols; x++) {
	                CellBlock cell = grids[y][x];

	                cell.setOpacity(0.0);
	                cell.setScaleX(0.6);
	                cell.setScaleY(0.6);

	                int distance = x + y;
	                Duration delay = Duration.millis(75 * (distance+1));

	                Timeline tl = new Timeline(
	                    new KeyFrame(
	                        delay,
	                        new KeyValue(cell.opacityProperty(), 1.0, Interpolator.EASE_OUT),
	                        new KeyValue(cell.scaleXProperty(), 1.0, Interpolator.EASE_OUT),
	                        new KeyValue(cell.scaleYProperty(), 1.0, Interpolator.EASE_OUT)
	                    )
	                );
	                tl.play();
	            }
	        }
	    });

	}

}
