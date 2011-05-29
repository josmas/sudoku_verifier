package com.jos.sudoku;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.jos.sudoku.data.SudokuDAO;

public class SudokuFileReaderTest {

	SudokuDAO reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new SudokuFileReader();
	}
	
	@Test
	public void testReadFromInexistentFile(){
		try {
			reader.readInput("thisFileDoesNotExit.txt");
			fail("File does not exist; Exception expected");
		} catch (IOException e) {
			// Ignore
		}
	}
	
	@Test
	public void testReadFromFile() throws IOException{
		String expected = "248 395 716\n571 628 349\n936 741 582\n" +
				"682 539 174\n359 174 628\n714 862 953\n" +
				"863 417 295\n195 286 437\n427 953 861\n";

		//TODO this test is too brittle : if the file does not exist we are in trouble
		//It also touches the file system so should be separated to an integration suite.
		String result = reader.readInput("solutionFiles/correct.txt");
		assertEquals(expected, result);
		//TODO delete this printout
		System.out.println(result);
	}

}
