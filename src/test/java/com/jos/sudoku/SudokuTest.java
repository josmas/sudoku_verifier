package com.jos.sudoku;

import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuTest {
	static final String LN = System.getProperty("line.separator");
	
	@Test
	public void sudokuCreationTest(){
		String sudokuAsText = "248 395 716" + LN + "571 628 349" + LN + "936 741 582" + LN + "" +
		"682 539 174" + LN + "359 174 628" + LN + "714 862 953" + LN + "" +
		"863 417 295" + LN + "195 286 437" + LN + "427 953 861" + LN + "";
		
		Sudoku sudoku = new Sudoku(sudokuAsText);
		assertNotNull(sudoku);
		int sudokuGrid [][] = sudoku.getSudokuGrid();
		assertEquals(2, sudokuGrid[0][0]);
		assertEquals(3, sudokuGrid[4][0]);
		assertEquals(1, sudokuGrid[8][8]);
		
	}
	
	@Test
	public void sudokuInvalidCreationTest(){
		String sudokuAsText = "248 39o 716" + LN + "571 628 349" + LN + "936 741 582" + LN + "" +
		"682 539 174" + LN + "359 174 628" + LN + "714 862 953" + LN + "" +
		"863 417 295" + LN + "195 286 437" + LN + "427 953 861" + LN + "";
		
		try {
			new Sudoku(sudokuAsText);
			fail("Should have thrown and exception");
		}
		catch(NumberFormatException nfe){
			//ignore
		}
	}
	
	@Test
	public void sudokuInvalidCreationNotEnoughInputInChain(){
		String sudokuWithNotEnoughNumbers = "248 395 716" + LN + "571 628 349" + LN + "936 741 582" + LN + "" +
		"863 417 295" + LN + "195 286 437" + LN + "427 953 861" + LN + "";
		
		try {
			new Sudoku(sudokuWithNotEnoughNumbers);
			fail("Should have thrown and exception");
		}
		catch(NumberFormatException nfe){
			//ignore
		}
	}

}
