package com.jos.sudoku.data;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.jos.sudoku.data.SudokuDAO;
import com.jos.sudoku.data.SudokuFileReader;

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
		//TODO this test is too brittle : if the file does not exist we are in trouble
		//It also touches the file system so should be separated to an integration suite.
		String result = reader.readInput("solutionFiles" + System.getProperty("file.separator") + "correct.txt");
		assertEquals("248395716571628349936741582682539174359174628714862953863417295195286437427953861", result);
	}
	
	@Test
	public void testReadFromFileWithInvalidData(){
		try {
			reader.readInput("invalid_data.txt");
			fail("File does not exist; Exception expected");
		} catch (IOException e) {
			// Ignore
		}
	}

}
