package com.jos.sudoku;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jos.sudoku.data.SudokuDAO;

public class SudokuVerifierTest {
	SudokuVerifier sv;
	SudokuDAO file;
	static final String LN = System.getProperty("line.separator");

	@Before
	public void setUp() throws Exception {
		sv = new SudokuVerifier();
		file = mock(SudokuDAO.class);
		
	}
	
	@Test
	public void correctnessOfRows() throws IOException{

		//Mocking readInput although it will not return a chain but the whole Sudoku.
		//Handy for testing in isolation here.
		when(file.readInput("correctRow")).thenReturn("248395716");
		String correctRow = file.readInput("correctRow");
		assertTrue(sv.isChainCorrect(correctRow));
		
		when(file.readInput("notEnoughNumbersRow")).thenReturn("248");
		String notEnoughNumbersRow = file.readInput("notEnoughNumbersRow");
		assertFalse(sv.isChainCorrect(notEnoughNumbersRow));
		
		when(file.readInput("notAllowedCharactersRow")).thenReturn("248AbC716");
		String notAllowedCharactersRow = file.readInput("notAllowedCharactersRow");
		assertFalse(sv.isChainCorrect(notAllowedCharactersRow));
		
		when(file.readInput("incorrectRow")).thenReturn("248248248");
		String incorrectRow = file.readInput("incorrectRow");
		assertFalse(sv.isChainCorrect(incorrectRow));
		
		when(file.readInput("nullRow")).thenReturn(null);
		String nullRow = file.readInput("nullRow");
		assertFalse(sv.isChainCorrect(nullRow));
	}
	
	@Test
	public void verifySudoku() throws IOException {
		String fullSudoku = "248 395 716" + LN + "571 628 349" + LN + "936 741 582" + LN + "" +
				"682 539 174" + LN + "359 174 628" + LN + "714 862 953" + LN + "" +
				"863 417 295" + LN + "195 286 437" + LN + "427 953 861" + LN + "";

		Sudoku sudoku = new Sudoku(fullSudoku);
		assertTrue(sv.verifySudoku(sudoku));
	}
	
	@Test
	public void verifyInvalidSudoku() throws IOException {
		String fullSudoku = "248 397 716" + LN + "571 628 349" + LN + "936 741 582" + LN + "" +
				"682 539 174" + LN + "359 174 628" + LN + "714 862 953" + LN + "" +
				"863 417 295" + LN + "195 286 437" + LN + "427 953 861" + LN + "";

		Sudoku sudoku = new Sudoku(fullSudoku);
		assertFalse(sv.verifySudoku(sudoku));
	}
}
