/**
 * 
 */
package com.jos.sudoku;

import java.io.IOException;

import com.jos.sudoku.data.SudokuDAO;
import com.jos.sudoku.data.SudokuFileReader;

public class SudokuClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length == 0){
			System.out.println("No arguments given; Verifying default files");
			
			String sourceFile = "solutionFiles/correct.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles/incorrect.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles/invalid_data.txt";
			verifyFile(sourceFile);
		}
		else if (args.length == 1)
			verifyFile(args[0]);
			
	}

	private static void verifyFile(String sourceFile) {
		boolean isCorrect = false;
		String sudokuAsText = "";
		SudokuDAO data = new SudokuFileReader();
		try {
			sudokuAsText = data.readInput(sourceFile);
			System.out.println("Verifying solution for file: " + sourceFile);
			Sudoku sudoku = new Sudoku(sudokuAsText);
			SudokuVerifier sv = new SudokuVerifier();
			isCorrect = sv.verifySudoku(sudoku);
			
			if (isCorrect)
				System.out.println("YAY! Your Sudoku is correct! Way to go!");
			else
				System.out.println("I'm afraid your Sudoku is incorrect. Please try again!");
			
		} catch (IOException ioe) {
			System.out.println("There has been a problem reading your sudoku file " + ioe.getMessage());
		} catch (NumberFormatException nfe) {
				System.out.println("There has been a problem parsing your sudoku file " + nfe.getMessage());
		}
	}

}
