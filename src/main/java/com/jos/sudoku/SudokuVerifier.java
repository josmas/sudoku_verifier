package com.jos.sudoku;

public class SudokuVerifier {

	/**
	 * <p>Verifies that a particular entry (row, column, or square) of the Sudoku is correct</p>
	 * @param row  the Sudoku chain to verify
	 * @return A boolean indicating if the chain is correct or not (a <code>null</code> input returns <code>false</code>)
	 */
	public boolean isCorrect(String chain) {

		if (isEmptyChain(chain) || !isValidChain(chain))
			return false;
		
		boolean isCorrect = true;
		
		//Search for repetition in the chain
		for (int i = 0; i < chain.length(); i++) {
			int counterMatches = countMatches(chain, chain.charAt(i)+"");
			if (counterMatches > 1){
				isCorrect = false;
				break;
			}
		}		
		return isCorrect;
	}
	
	/**
	 * <p>Validity through RegExp: all numbers from 1 to 9 appear in the chain, just once</p>
	 * @param row  the Sudoku chain to verify
	 * @return A boolean indicating if the chain is valid
	 */
	private boolean isValidChain(String chain) {
		return chain.matches("[1-9]{9}");
	}
	
	/**
	 * <p>Counts the number of times a sub-chain appears in chain.</p>
	 * 
	 * @param chain  the String to check, may be null
	 * @param subChain  the substring to count, may be null
	 * @return the number of occurrences, 0 if <code>null</code> is passed in.
	*/
	private int countMatches(String chain, String subChain) { //TODO change names
		if (isEmptyChain(chain) || isEmptyChain(subChain)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = chain.indexOf(subChain, idx)) != -1) {
			count++;
			idx += subChain.length();
		}
		return count;
	}

	/**
	 * <p>Verifies that a string is not <code>null</code> or empty returning <code>false</code> otherwise.</p> 
	 * 
	 * @param chain  the string to be verified
	 * @return true or false depending on input
	 */
	private boolean isEmptyChain(String chain) {
		return chain == null || chain.length() == 0;
	}

}
