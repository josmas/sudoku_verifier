package com.jos.sudoku;

import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuTest {
	static final String LN = System.getProperty("line.separator");
	
	@Test
	public void sudokuCreationTest(){
		String sudokuAsText = "248395716571628349936741582" +
		"682539174359174628714862953" +
		"863417295195286437427953861";
		
		Sudoku sudoku = new Sudoku(sudokuAsText);
		assertNotNull(sudoku);
		int sudokuGrid [][] = sudoku.getSudokuGrid();
		assertEquals(2, sudokuGrid[0][0]);
		assertEquals(3, sudokuGrid[4][0]);
		assertEquals(1, sudokuGrid[8][8]);
		
	}
	
	@Test
	public void sudokuInvalidCreationTest(){
		String sudokuAsText = "24839o716571628349936741582" +
		"682539174359174628714862953" +
		"863417295195286437427953861";
		
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
		String sudokuWithNotEnoughNumbers = "248395716571628349936741582" +
		"863417295195286437427953861";
		
		try {
			new Sudoku(sudokuWithNotEnoughNumbers);
			fail("Should have thrown and exception");
		}
		catch(NumberFormatException nfe){
			//ignore
		}
	}
}
