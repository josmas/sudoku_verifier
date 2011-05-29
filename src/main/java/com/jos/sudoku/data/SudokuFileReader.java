package com.jos.sudoku.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class SudokuFileReader implements SudokuDAO {

	@Override
	public String readInput(String input) throws IOException {
		return readFile(input);
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	private String readFile(String input) throws IOException {
		//TODO this method does not insure against bad input
		StringBuilder contents = new StringBuilder();

		FileInputStream fstream = new FileInputStream(input);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			}
		} finally {
			in.close();
		}
		return contents.toString();
	}
}
