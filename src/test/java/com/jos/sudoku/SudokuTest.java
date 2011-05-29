package com.jos.sudoku;

import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuTest {
	
	@Test
	public void sudokuCreationTest(){
		String sudokuAsText = "248 395 716\n571 628 349\n936 741 582\n" +
		"682 539 174\n359 174 628\n714 862 953\n" +
		"863 417 295\n195 286 437\n427 953 861\n";
		
		Sudoku sudoku = new Sudoku(sudokuAsText);
		assertNotNull(sudoku);
		int sudokuGrid [][] = sudoku.getSudokuGrid();
		assertEquals(2, sudokuGrid[0][0]);
		assertEquals(3, sudokuGrid[4][0]);
		assertEquals(1, sudokuGrid[8][8]);
		
	}
	
	@Test
	public void sudokuInvalidCreationTest(){
		String sudokuAsText = "248 39o 716\n571 628 349\n936 741 582\n" +
		"682 539 174\n359 174 628\n714 862 953\n" +
		"863 417 295\n195 286 437\n427 953 861\n";
		
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
		//TODO repetition with previous test
		String sudokuWithNotEnoughNumbers = "248 395 716\n571 628 349\n936 741 582\n" +
		"863 417 295\n195 286 437\n427 953 861\n";
		
		try {
			new Sudoku(sudokuWithNotEnoughNumbers);
			fail("Should have thrown and exception");
		}
		catch(NumberFormatException nfe){
			//ignore
		}
	}

}
