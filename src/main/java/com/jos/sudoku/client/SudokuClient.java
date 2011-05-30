/**
 * 
 */
package com.jos.sudoku.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.jos.sudoku.Sudoku;
import com.jos.sudoku.SudokuVerifier;
import com.jos.sudoku.data.SudokuDAO;
import com.jos.sudoku.data.SudokuFileReader;

public class SudokuClient {
	
	static final String QUIT = "quit";
	private static BufferedReader in;
	private static BufferedWriter out;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length == 0){
			System.out.println("No arguments given; Verifying default files\n\n");
			
			String sourceFile = "solutionFiles" + System.getProperty("file.separator") + "correct.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "incorrect.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "invalid_data.txt";
			verifyFile(sourceFile);
		}
		else if (args.length >= 1){
			if (args[0].equals("-i")){
				System.out.println("\nGoing interactive!\nType 'quit' to exit the verifier.\n");
				try {
					runInteractiveClient();
				} catch (IOException e) {
					System.out.println("There has been a problem reading/writing from/to the console. Exiting!");
				}
			}
			else
				verifyFile(args[0]);
		}	
	}

	private static void verifyFile(String sourceFile) {
		boolean isCorrect = false;
		String sudokuAsText = "";
		SudokuDAO data = new SudokuFileReader();
		try {
			sudokuAsText = data.readInput(sourceFile);
			printHeader(sourceFile);
			Sudoku sudoku = new Sudoku(sudokuAsText);
			SudokuVerifier sv = new SudokuVerifier();
			isCorrect = sv.verifySudoku(sudoku);
			
			if (isCorrect){
				System.out.println("YAY! Your Sudoku is correct! Way to go!\n\n");
			} else {
				System.out.println("I'm afraid your Sudoku is incorrect. Please try again!\n\n");
			}
		} catch (IOException ioe) {
			System.out.println("There has been a problem reading your sudoku file: " + ioe.getMessage());
		} catch (NumberFormatException nfe) {
				System.out.println("There has been a problem parsing your sudoku file: " + nfe.getMessage());
		}
	}

	private static void printHeader(String sourceFile) {
		System.out.println("Verifying solution for file: " + sourceFile);
		System.out.println("---------------------------------------------------------------------");
	}

	private static void runInteractiveClient() throws IOException {
		String line;
		setupStreams();
		do {
			write("Please enter a file name: ");
			line = in.readLine();
			writeln("");
			writeln("----------------");
			if (!line.equals(QUIT)){
				verifyFile(line);
				writeln("");
			} else {
				writeln("Thanks for using the Sudoku Verifier! BYEZ!!!");
			}
		} while (!line.equals(QUIT));
	}
	
	private static void setupStreams(){
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));		
	}
	
	private static void write(String line) throws IOException{
		out.write(line);
		out.flush();
	}
	
	private static void writeln(String line) throws IOException{
		out.write(line);
		out.newLine();
		out.flush();
	}
}
