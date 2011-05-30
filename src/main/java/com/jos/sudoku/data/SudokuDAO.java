package com.jos.sudoku.data;

import java.io.IOException;
/**
 * <p>Abstraction of input sources for the Sudoku verifier.</p>
 */
public interface SudokuDAO {

	/**
	 * <p>Reads an input source and returns a String with the contents found</p>
	 * @param input  Name in text to be used as source for input
	 * @return  A String with the contents of the input source
	 * @throws IOException
	 */
	public String readInput(String input)  throws IOException;
}
