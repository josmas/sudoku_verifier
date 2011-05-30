/**
 * 
 */
package com.jos.sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.jos.sudoku.data.SudokuDAO;
import com.jos.sudoku.data.SudokuFileReader;

public class SudokuClient {
	
	static final String QUIT = "quit";
	//TODO don't like the statics
	private static BufferedReader in;
	private static BufferedWriter out;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length == 0){
			System.out.println("No arguments given; Verifying default files");
			
			String sourceFile = "solutionFiles" + System.getProperty("file.separator") + "correct.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "incorrect.txt";
			verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "invalid_data.txt";
			verifyFile(sourceFile);
		}
		else if (args.length >= 1){
			if (args[0].equals("-i")){
				//Run interactive
				System.out.println("Going interactive!");
				try {
					runInteractiveClient();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	private static void runInteractiveClient() throws IOException {
		String line;
		setupStreams();
		do {
			write("Please enter a file name: ");
			line = in.readLine();
			if (!line.equals(QUIT)){
				verifyFile(line);
				writeln("");
				writeln("----------------");
				writeln("");
			} else {
				writeln("");
				writeln("----------------");
				writeln("Thanks for using the Sudoku Verifier! BYEZ!!!");
			}
				
			
		}while (!line.equals(QUIT));
		
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
