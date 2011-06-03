package com.jos.sudoku.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>Implementation of the SudokuDAO interface to read from Files.</p>
 */
public class SudokuFileReader implements SudokuDAO {

	@Override
	public String readInput(String input) throws IOException {
		return readFileInPlain(input);
	}

	/**
	 *<p>The format to be read is expected as follows: 
	 *
	 * 		248 395 716
	 * 		571 628 349
	 * 		936 741 582
	 * 
	 * 		682 539 174
	 * 		359 174 628
	 * 		714 862 953
	 * 
	 * 		863 417 295
	 * 		195 286 437
	 * 		427 953 861
	 *
	 * <p>This method does not insure against bad input (it is verified in other parts of the application)</p>
	 * @param input  the name of the file
	 * @return a String with the lines read (NOT keeping the structure but as a long line of numbers)
	 * @throws IOException in the case of problems with the file
	 */
	private String readFileInPlain(String input) throws IOException {
		//this method does not insure against bad input
		StringBuilder contents = new StringBuilder();

		FileInputStream fstream = new FileInputStream(input);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					contents.append(line);
				}
			}
		} finally {
			br.close();
			in.close();
		}
		return contents.toString().replace(" ", "");
	}
}
