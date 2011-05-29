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

	@Before
	public void setUp() throws Exception {
		sv = new SudokuVerifier();
		file = mock(SudokuDAO.class);
		
	}
	
	@Test
	public void correctnessOfRows() throws IOException{

		//TODO readInput will not return a chain but the whole Sudoku --- me thinks! 
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

}
