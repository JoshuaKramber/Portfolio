/** Represents a 4x4, 6x6, or 9x9 Sudoku board.
*/
public class Board {

	/** nxn Sudoku board. Blanks are filled with 0's. */
	// It is public for easy modification to the values.
	public Integer[][] board;

	/** Either 4, 6, or 9 */
	private int size;

	/** 4x4 boards have 2x2 grids. 6x6 have 2x3 grids. 9x9 have 3x3 */
	private int rowsPerGrid;
	private int colsPerGrid;

	/** Constructor with an initial board value.
	* @param initial 2-dimensional array either 4x4. 6x6, or 9x9
	*/
	public Board(Integer[][] initial) {
		// Use array size to determine puzzle size
		size = initial.length;

		// Set values based on initial array.
		board = new Integer[size][size];
		for (int row=0; row<size; row++) {
			for (int col=0; col<size; col++) {
				board[row][col]= initial[row][col];
			}
		}

		// Establish grid dimensions
		if (size==4) {
			rowsPerGrid = 2;
			colsPerGrid = 2;
		} else if (size==6){
			rowsPerGrid = 2;
			colsPerGrid = 3;
		} else if (size==9) {
			rowsPerGrid = 3;
			colsPerGrid = 3;
		}
	} // end Board constructor

	/** Copy Constructor
	* @param toCopy This new board is based on the values of toCopy
	*/
	public Board(Board toCopy) {
		// Call the other constructor using the board values passed
		this(toCopy.board);
	}

	@Override
	public String toString() {
		String result = "";
		for (int row=0; row<size; row++) {
			for (int col=0; col<size; col++) {
				result += "  " + board[row][col];
			}
			result += "\n";
		}
		return result;
	} // end toString

	/** Find the first location on the board that is empty. Search row by row.
	* @return Row of first location found on board that is empty, or -1 if none.
	*/
	public int rowFirstEmpty() {
		for (int row=0; row<size; row++) {
			for (int col=0; col<size; col++) {
				if (board[row][col] == 0) {
					return row;
				};
			}
		}
		// If we got here, there are no empty spaces
		return -1;
	}

	/** Find the first locatin on the board that is empty. Search row by row.
	* @return Column of first empty location found on the board, or -1 if none.
	*/
	public int colFirstEmpty() {
		for (int row=0; row<size; row++) {
			for (int col=0; col<size; col++) {
				if (board[row][col] == 0) {
					return col;
				};
			}
		}
		// If we got here, there are no empty spaces.
		return -1;
	}

	/** Determines if values in board do not violate any of the Sudoku rules.
	* @return true if all rows, cols, and grids are valid (no repeats)
	*/
	public boolean isValid() {
		// check rows and columns
		for (int i=0; i<size; i++) {
			if (!isValid(row(i))) {
				return false;
			}
			if (!isValid(col(i))) {
				return false;
			}
		}

		// check grids
		for (int i=0; i<size; i++) {
			if (!isValid(grid(i))) {
				return false;
			}
		}
		// if we got here, everything is valid.
		return true;
	}

	/** Determines if all values are filled. It assumes all values are valid.
	* @return true if all rows, cols, and grids are filled.
	*/
	public boolean isComplete() {
		// check rows and columns
		for (int i=0; i<size; i++) {
			if (!isComplete(row(i))) {
				return false;
			}
			if (!isComplete(col(i))) {
				return false;
			}
		}

		// check grids
		for (int i=0; i<size; i++) {
			if (!isComplete(grid(i))) {
				return false;
			}
		}
		// if we got here, everything is filled in.
		return true;
	}

	// size getter
	public int size() {
		return size;
	}

	/** Determine if any values are repeated.
	* @param values Represent row, column, or grid grouping.
	* @return true if no duplicates, else false.
	*/
	private boolean isValid(Integer[] values) {
		// Build the histogram, counting frequency of 0, 1, ..., size
		int[] histo = new int[values.length+1];
		for (Integer value : values) {
			histo[value] += 1;
		}
		// Check if any frequency from 1,2,..,size is more than 1.
		for (int i=1;i<=values.length; i++) {
			if (histo[i]>1) {
				return false;
			}
		}
		// if we got here, there are no duplicates.
		return true;
	}

	/** Determine if all squares in the given grouping are filled.
		* If all rows, columns, and grids have been determined to be valid
		* AND complete, then the puzzle is solved.
	*/
	private boolean isComplete(Integer[] values) {
		int count = values.length;
		for (Integer val : values) {
			if (val != 0) {
				count--;
			}
		}
		return count==0;
	}

	/** Create an array from the indicated grid. Grids are number 0 to size-1.
		* Grid 0 is in the upper left, 1 is upper right, and so on...
		* These values are used to check validity of board.
	*/
	private Integer[] grid(int grid) {
		// Values included in the grid
		Integer[] values = new Integer[size];
		// Create row and column boundaries for the grid
		int row = (int)(grid/rowsPerGrid)*rowsPerGrid;
		int col = (grid%rowsPerGrid)*colsPerGrid;
		int overflow = col+colsPerGrid;		// when column is off the grid.
		// Get the numbers and place in array
		for (int i=0; i<size; i++) {
			values[i] = board[row][col];
			col++;
			// Check if need to "modulo" back to first column, advance the row
			if (col==overflow) {
				row++;
				col = (grid%rowsPerGrid)*colsPerGrid;
			}
		}
		return values;
	}


	/** As with grid, get the values from the board that correspond to the row.
	  * These values are used to check validity of board.
	*/
	private Integer[] row(int r) {
		Integer[] row = new Integer[size];
		for (int i=0; i<size; i++) {
			row[i] = board[r][i];
		}
		return row;
	}

	/** As with row, get the values from the board that correspond to column.
	  * These values are used to check validity of board.
	*/
	private Integer[] col(int c) {
		Integer[] col = new Integer[size];
		for (int i=0; i<size; i++) {
			col[i] = board[i][c];
		}
		return col;
	}
} // end class Board