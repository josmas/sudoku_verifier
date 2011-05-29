package com.jos.sudoku;

public class Sudoku {
	
	private int sudokuGrid [][] = new int [9][9];
	
	public Sudoku(String sudokuAsText){
		this.sudokuGrid = initialiseGrid(sudokuAsText);
	}

	private int[][] initialiseGrid(String sudokuAsText) {
		int grid [][] = new int [9][9];
		
		//TODO change these awful names!
		String sudokuInARow = sudokuAsText.replaceAll("\n", "");
		String sudokuInARow2 = sudokuInARow.replaceAll(" ", "");
//		System.out.println("In a row: " + sudokuInARow2);
		if (Sudoku.isValidChain(sudokuInARow2)){
			throw new NumberFormatException("Illegal characters in the sudoku string");
		}
		if (!isTheRightAmountOFNumbers(sudokuInARow2)){
			throw new NumberFormatException("Not Enough characters in the sudoku string");
		}
		
		
		int counterForSudokuText = 0;
		for (int row = 0; row < 9; row++) {
		    for (int column = 0; column < 9; column++) {
		    	grid[row] [column] = new Integer(sudokuInARow2.charAt(counterForSudokuText)+"").intValue();
		    	counterForSudokuText++;
//		        System.out.print(grid[row] [column] + " ");
		    }
//		    System.out.println();
		}
		return grid;
	}

	int[][] getSudokuGrid() {
		return sudokuGrid;
	}
	
	/**
	 * <p>Validity through RegExp: all numbers from 1 to 9 appear in the chain, just once</p>
	 * @param row  the Sudoku chain to verify
	 * @return A boolean indicating if the chain is valid
	 */
	static boolean isValidChain(String chain) {
		return chain.matches("[1-9]{9}");
	}
	
	private boolean isTheRightAmountOFNumbers(String chain){
		return (chain.length() == 81);
	}

}
