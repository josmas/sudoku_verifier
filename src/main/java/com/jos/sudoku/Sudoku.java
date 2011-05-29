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
		System.out.println("In a row: " + sudokuInARow2);
		int counterForSudokuText = 0;
		for (int row = 0; row < 9; row++) {
		    for (int column = 0; column < 9; column++) {
		    	grid[row] [column] = new Integer(sudokuInARow2.charAt(counterForSudokuText)+"").intValue();
		    	counterForSudokuText++;
		        System.out.print(grid[row] [column] + " ");
		    }
		    System.out.println();
		}
		return grid;
	}

	int[][] getSudokuGrid() {
		return sudokuGrid;
	}

}
