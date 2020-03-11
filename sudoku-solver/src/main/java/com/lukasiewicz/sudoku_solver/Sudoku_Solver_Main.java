package com.lukasiewicz.sudoku_solver;

import java.util.stream.IntStream;

public class Sudoku_Solver_Main {

	static int[][] sudoku = {
			  { 0, 8, 0, 6, 0, 0, 0, 0, 0 },
			  { 0, 0, 2, 0, 7, 0, 0, 3, 0 },
			  { 9, 7, 1, 0, 0, 2, 8, 0, 0 },
			  { 0, 0, 0, 7, 0, 9, 2, 8, 0 },
			  { 0, 0, 8, 0, 0, 0, 9, 0, 0 },
			  { 0, 9, 7, 5, 0, 6, 0, 0, 0 },
			  { 0, 0, 9, 1, 0, 0, 4, 2, 3 },
			  { 0, 1, 0, 0, 4, 0, 7, 0, 0 },
			  { 0, 0, 0, 0, 0, 5, 0, 1, 0 } 
			};
	
    static int SUDOKU_START_INDEX = 0;
	
    static int SUDOKU_SIZE = 9;
    
    static int SUBSECTION_SIZE = 3;
    
    static int NO_VALUE = 0;
    
    static int MIN_VALUE = 1;
	
    static int MAX_VALUE = 9;
    
    
    private static boolean solve(int[][] sudoku) {

		for (int row = SUDOKU_START_INDEX; row < SUDOKU_SIZE; row++) {
	        for (int column = SUDOKU_START_INDEX; column < SUDOKU_SIZE; column++) {
				if (sudoku[row][column] == NO_VALUE) {

					for (int k = MIN_VALUE ; k <= MAX_VALUE ; k++) {
	                    sudoku[row][column] = k;
	                    if (isValid(sudoku, row, column) && solve(sudoku)) {
	                        return true;
	                    }
	                    sudoku[row][column] = NO_VALUE;
	                }
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	private static boolean isValid(int[][] sudoku, int row, int column) {
	    return (rowConstraint(sudoku, row)
	      && columnConstraint(sudoku, column) 
	      && subsectionConstraint(sudoku, row, column));
	}
	
	private static boolean rowConstraint(int[][] board, int row) {
	    boolean[] constraint = new boolean[SUDOKU_SIZE];
	    return IntStream.range(SUDOKU_START_INDEX, SUDOKU_SIZE)
	      .allMatch(column -> checkConstraint(board, row, constraint, column));
	}
	
	private static boolean columnConstraint(int[][] board, int column) {
	    boolean[] constraint = new boolean[SUDOKU_SIZE];
	    return IntStream.range(SUDOKU_START_INDEX, SUDOKU_SIZE)
	      .allMatch(row -> checkConstraint(board, row, constraint, column));
	}
	
	private static boolean subsectionConstraint(int[][] board, int row, int column) {
	    boolean[] constraint = new boolean[SUDOKU_SIZE];
	    int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
	    int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;
	 
	    int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
	    int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;
	 
	    for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
	        for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
	            if (!checkConstraint(board, r, constraint, c)) return false;
	        }
	    }
	    return true;
	}
	
	static boolean checkConstraint(int[][] sudoku, int row, boolean[] constraint, int column) {
		
		if (sudoku[row][column] != NO_VALUE) {
			        if (!constraint[sudoku[row][column] - 1]) {
			            constraint[sudoku[row][column] - 1] = true;
			        } else {
			            return false;
			        }
		}
		return true;
	}
	
	private static void printSudoku() {
	    for (int row = SUDOKU_START_INDEX; row < SUDOKU_SIZE; row++) {
	        for (int column = SUDOKU_START_INDEX; column < SUDOKU_SIZE; column++) {
	            System.out.print(sudoku[row][column] + " ");
	        }
	        System.out.println();
	    }
	}

	public static void main(String[] args) {
		solve(sudoku);
		printSudoku();
	}
	
	
}
