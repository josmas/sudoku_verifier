package com.jos.sudoku.stepdefinitions;

import java.io.IOException;

import com.jos.sudoku.Sudoku;
import com.jos.sudoku.SudokuVerifier;
import com.jos.sudoku.data.SudokuDAO;
import com.jos.sudoku.data.SudokuFileReader;

import static org.junit.Assert.*;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

/**
 * <p>Steps defined to run the cucumber features found in the 'features' directory</p>
 * @author jos
 *
 */
public class SudokuVerifierSteps {
	
	boolean isCorrect = false;
	String sudokuAsText = "";
	SudokuDAO data = new SudokuFileReader();
	Sudoku sudoku;
	SudokuVerifier sv;
	
	@Given("^the file correct_txt that contains a correct solution$")
	public void theFileCorrectTxtThatContainsACorrectSolution() throws IOException {
		sudokuAsText = data.readInput("solutionFiles" + System.getProperty("file.separator") + "correct.txt");
		sudoku = new Sudoku(sudokuAsText);
		sv = new SudokuVerifier();
	}
	
	@When("^I run the Sudoku verifier$")
	public void iRunTheSudokuVerifier() {
		isCorrect = sv.verifySudoku(sudoku);
	}
	
	@Then("^I should get an affirmative value")
	public void iShouldGetAnAffirmativeValue() {
		assertTrue(isCorrect);
	}
	
	@Given("^the file incorrect_txt that contains an incorrect solution$")
	public void theFileIncorrectTxtThatContainsAnIncorrectSolution() throws IOException {
		sudokuAsText = data.readInput("solutionFiles" + System.getProperty("file.separator") + "incorrect.txt");

		sudoku = new Sudoku(sudokuAsText);
		sv = new SudokuVerifier();
	}
	
	@When("^I run the Sudoku verifier for incorrect$")
	public void iRunTheSudokuVerifierForIncorrect() {
		isCorrect = sv.verifySudoku(sudoku);
	}
	
	@Then("^I should get a negative value$")
	public void iShouldGetANegativeValue() {
		assertFalse(isCorrect);
	}


}
