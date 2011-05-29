Feature: Sudoku Verifier
  In order to make sure no one cheats on their sudoku solutions
  As a member of the Sudoku Inquisition (expected by nobody since 1970)
  I want to automate the checking of solutions


  Scenario: Checking a file with an incorrect solution
    Given the file correct_txt that contains an incorrect solution
    When I run the Sudoku verifier
    Then I should get the value "Correct"
    
  Scenario: Checking a file with an incorrect solution
    Given the file incorrect_txt that contains an incorrect solution
    When I run the Sudoku verifier
    Then I should get the value "Incorrect"