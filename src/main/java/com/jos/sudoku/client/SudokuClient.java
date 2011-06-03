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

/**
 * <p>Main client for the Sudoku Verifier. It can be run in 3 modes plus an additional '-h' file for help.</p>
 * <p>Modes are:</br>
 * 		- No arguments: it will verify the 3 files provided in the project (make sure files are available)</br>
 * 		- A file argument: it will verify that particular Sudoku solution file</br>
 * 		- '-i' argument: will run the interactive mode and accept file names from console</p>
 * @author jos
 *
 */
public class SudokuClient {
	
	static final String QUIT = "quit";
	private static BufferedReader in;
	private static BufferedWriter out;

	/**
	 * Main client for the Sudoku Verifier.
	 */
	public static void main(String[] args) {
		
		SudokuClient sudokuClient = new SudokuClient();
		if (args.length == 0){
			System.out.println("No arguments given; Verifying default files\n\n");
			
			String sourceFile = "solutionFiles" + System.getProperty("file.separator") + "correct.txt";
			sudokuClient.verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "incorrect.txt";
			sudokuClient.verifyFile(sourceFile);
			
			sourceFile = "solutionFiles" + System.getProperty("file.separator") + "invalid_data.txt";
			sudokuClient.verifyFile(sourceFile);
		}
		else if (args.length >= 1){
			if (args[0].equals("-i")){
				System.out.println("\nGoing interactive!\nType 'quit' to exit the verifier.\n");
				try {
					sudokuClient.runInteractiveClient();
				} catch (IOException e) {
					System.out.println("There has been a problem reading/writing from/to the console. Exiting!");
				}
			}
			else if (args[0].equals("-h")){
				sudokuClient.printUsage();
			}
			else
				sudokuClient.verifyFile(args[0]);
		}	
	}

	/**
	 * <p>Verifies the file passed as a argument according to the <code>SudokuVerifier.java</code> class</p>
	 * @param sourceFile
	 */
	private void verifyFile(String sourceFile) {
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

	private void printUsage() {
		String usage = "\nThis jar file can be used in 3 modes: \n\n" +
			"- No arguments mode: It will verify the 3 files provided in the 'solutionFiles' folder.\n" +
			"  Usage: java -jar target/SudokuVerifier-0.1.jar \n\n" +
			"- file arguments mode: It will verify the 1 file provided in the command line.\n" +
			"  Usage: java -jar target/SudokuVerifier-0.1.jar solutionFiles/correct.txt\n\n" +
			"- Interactive mode: It will verify the files provided in the command line when started with argument '-i'.\n" +
			"  Usage: java -jar target/SudokuVerifier-0.1.jar -i\n\n" +
			"\nThe option -h prints this usage help";
		
		System.out.println(usage);
	}
	
	private void printHeader(String sourceFile) {
		System.out.println("Verifying solution for file: " + sourceFile);
		System.out.println("---------------------------------------------------------------------");
	}

	private void runInteractiveClient() throws IOException {
		String line;
		setupStreams();
		do {
			write("Please enter a file name (quit): ");
			line = in.readLine();
			writeln("");
			writeln("----------------");
			if (!line.equals(QUIT)){
				verifyFile(line);
				writeln("");
			} else {
				writeln("Thanks for using the Sudoku Verifier. BYEZ!!!");
			}
		} while (!line.equals(QUIT));
	}
	
	private void setupStreams(){
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));		
	}
	
	private void write(String line) throws IOException{
		out.write(line);
		out.flush();
	}
	
	private void writeln(String line) throws IOException{
		out.write(line);
		out.newLine();
		out.flush();
	}
}