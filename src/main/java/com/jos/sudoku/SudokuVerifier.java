package com.jos.sudoku;

/**
 * Chain is a term to describe any type of Sudoku possible stream of chars that cannot be equal.....
 * @author jos
 *
 */
public class SudokuVerifier {

	
	public boolean verifySudoku(Sudoku sudoku) {
		
		int [][] sudokuGrid= sudoku.getSudokuGrid();
		
		return checkRows(sudokuGrid) && checkColumns(sudokuGrid) && checkSquares(sudokuGrid);
	}

	//TODO the next two methods are very similar: DRY.
	private boolean checkRows(int[][] sudokuGrid) {
		boolean correctChain = false;
		for (int i = 0; i < sudokuGrid.length; i++){
			StringBuilder chainRow = new StringBuilder();
			for (int j = 0; j < sudokuGrid.length; j++) {
				chainRow.append(sudokuGrid[i][j]);
			}
			correctChain = isChainCorrect(chainRow.toString());
			if (!correctChain)
				return false;
		}
		
		return correctChain;
	}
	
	private boolean checkColumns(int[][] sudokuGrid) {
		boolean correctChain = false;
		for (int i = 0; i < sudokuGrid.length; i++){
			StringBuilder chainColumn = new StringBuilder();
			for (int j = 0; j < sudokuGrid.length; j++) {
				chainColumn.append(sudokuGrid[j][i]);
			}
			correctChain = isChainCorrect(chainColumn.toString());
			if (!correctChain)
				return false;
		}
		
		return correctChain;
	}
	
	// We create the squares by going through the array in a different way here.
	// An example of the first two squares:
	// First Square i =0 ---> j = 0, j = 1, j = 2
	//              i =1 ---> j = 0, j = 1, j = 2
	//              i =2 ---> j = 0, j = 1, j = 2
	//
	// SecondSquare i =0 ---> j = 3, j = 4, j = 5
	//              i =1 ---> j = 3, j = 4, j = 5
	//              i =2 ---> j = 3, j = 4, j = 5
	// And the same for ranges when i and j are 3 and 6
	private boolean checkSquares(int[][] sudokuGrid) {
		boolean correctChain = false;
		for (int i = 0; i < sudokuGrid.length; i+=3){
			StringBuilder chainSquare = createSquare(sudokuGrid, i, 0);
			correctChain = isChainCorrect(chainSquare.toString());
			if (!correctChain)
				return false;
			
			chainSquare = createSquare(sudokuGrid, i, 3);
			correctChain = isChainCorrect(chainSquare.toString());
			if (!correctChain)
				return false;
			
			chainSquare = createSquare(sudokuGrid, i, 6);
			correctChain = isChainCorrect(chainSquare.toString());
			if (!correctChain)
				return false;
		}
		
		
		return correctChain;
	}
	
	private StringBuilder createSquare(int[][] sudokuGrid, int startPointX, int startPointY){
		StringBuilder chainSquare = new StringBuilder();
		for (int i = startPointX; i < startPointX + 3; i++){
			
			for (int j = startPointY; j < startPointY + 3; j++) {
				chainSquare.append(sudokuGrid[i][j]);
			}
		}
		return chainSquare;
	}

	/**
	 * <p>Verifies that a particular entry (row, column, or square) of the Sudoku is correct</p>
	 * @param row  the Sudoku chain to verify
	 * @return A boolean indicating if the chain is correct or not (a <code>null</code> input returns <code>false</code>)
	 */
	public boolean isChainCorrect(String chain) {

		if (isEmptyChain(chain) || !Sudoku.isValidChain(chain))
			return false;
		
		boolean isCorrect = true;
		
		//Search for repetition in the chain
		for (int i = 0; i < chain.length(); i++) {
			int counterMatches = countMatches(chain, chain.charAt(i)+"");
			if (counterMatches > 1){
				isCorrect = false;
				System.out.println("The number " + chain.charAt(i) + " appears " 
						+ counterMatches + " times in the chain: " + chain );
				break;
			}
		}		
		return isCorrect;
	}
	
	/**
	 * <p>Counts the number of times a sub-chain appears in chain.</p>
	 * 
	 * @param chain  the String to check, may be null
	 * @param subChain  the substring to count, may be null
	 * @return the number of occurrences, 0 if <code>null</code> is passed in.
	*/
	private int countMatches(String chain, String subChain) {
		if (isEmptyChain(chain) || isEmptyChain(subChain)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = chain.indexOf(subChain, idx)) != -1) {
			count++;
			idx += subChain.length();
		}
		return count;
	}

	/**
	 * <p>Verifies that a string is not <code>null</code> or empty returning <code>false</code> otherwise.</p> 
	 * 
	 * @param chain  the string to be verified
	 * @return true or false depending on input
	 */
	private boolean isEmptyChain(String chain) {
		return chain == null || chain.length() == 0;
	}

}
