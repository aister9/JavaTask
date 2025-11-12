package Minesweeper.UIPrimitive;

import java.util.function.BiConsumer;

import Minesweeper.Difficulty;
import Minesweeper.Model.GameBoard;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
		setStyle("-fx-background-color: #b8c6db; -fx-background-insets: 0; -fx-background-radius: 10;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 18, 0.2, 0, 6);");
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
}
