package com.jos.sudoku;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.jos.sudoku.data.SudokuDAO;

public class SudokuVerifierTest {

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void correctnessOfRowsTest(){
		SudokuVerifier sv = new SudokuVerifier();
		SudokuDAO file = mock(SudokuDAO.class);
		
		
		//TODO readInput will not return a chain but the whole Sudoku --- me thinks! 
		when(file.readInput("correctRow")).thenReturn("248395716");
		String correctRow = file.readInput("correctRow");
		assertTrue(sv.isCorrect(correctRow));
		
		when(file.readInput("notEnoughNumbersRow")).thenReturn("248");
		String notEnoughNumbersRow = file.readInput("notEnoughNumbersRow");
		assertFalse(sv.isCorrect(notEnoughNumbersRow));
		
		when(file.readInput("notAllowedCharactersRow")).thenReturn("248AbC716");
		String notAllowedCharactersRow = file.readInput("notAllowedCharactersRow");
		assertFalse(sv.isCorrect(notAllowedCharactersRow));
		
		when(file.readInput("incorrectRow")).thenReturn("248248248");
		String incorrectRow = file.readInput("incorrectRow");
		assertFalse(sv.isCorrect(incorrectRow));	
		
		when(file.readInput("nullRow")).thenReturn(null);
		String nullRow = file.readInput("nullRow");
		assertFalse(sv.isCorrect(nullRow));
	}

}
