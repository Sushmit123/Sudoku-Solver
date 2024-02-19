package com.sudoko_solver.ty;
import java.util.Scanner;
public class Solution {
    public boolean isSafe(char[][] board, int row, int col, int number) {
        //column
          for(int i=0; i<board.length; i++) {
              if(board[i][col] == (char)(number+'0')) {
                  return false;
              }
          }
         
          //row
          for(int j=0; j<board.length; j++) {
              if(board[row][j] == (char)(number+'0')) {
                  return false;
              }
          }
         
          //grid
          int sr = 3 * (row/3);
          int sc = 3 * (col/3);
         
          for(int i=sr; i<sr+3; i++) {
              for(int j=sc; j<sc+3; j++) {
                  if(board[i][j] == (char)(number+'0')) {
                      return false;
                  }
              }
          }
         
          return true;
      

       }

       public boolean helper(char[][] board, int row, int col) {
        if(row == board.length) {
              return true;
          }
         
          int nrow = 0;
          int ncol = 0;
         
          if(col == board.length-1) {
              nrow = row + 1;
              ncol = 0;
          } else {
              nrow = row;
              ncol = col + 1;
          }
         
          if(board[row][col] != '.') {
              if(helper(board, nrow, ncol)) {
                  return true;
              }
          } else {
             
              //fill the place
              for(int i=1; i<=9; i++) {
                  if(isSafe(board, row, col, i)) {
                      board[row][col] = (char)(i+'0');
                      if(helper(board, nrow, ncol))
                          return true;
                      else
                           board[row][col] = '.';
                  }
              }
          }
                        
          return false;

       }

       public void solveSudoku(char[][] board) {
           helper(board, 0, 0);
       }

       public static void main(String[] args) {
           Solution solution = new Solution();
           char[][] sudokuBoard = new char[9][9];

           // Take user input for the initial Sudoku board
           Scanner scanner = new Scanner(System.in);
           System.out.println("Enter the initial Sudoku board (9 rows, each row separated by spaces):");

           for (int i = 0; i < 9; i++) {
               String rowInput = scanner.nextLine();
               String[] values = rowInput.split(" ");

               // Check if the input has exactly 9 values
               if (values.length != 9) {
                   System.out.println("Invalid input. Please provide 9 values for each row.");
                   return; // Exit the program
               }

               for (int j = 0; j < 9; j++) {
                   sudokuBoard[i][j] = values[j].charAt(0);
               }
           }

           // Close the scanner
           scanner.close();

           // Call the solveSudoku method to start solving with user input
           solution.solveSudoku(sudokuBoard);

           // Display the solved Sudoku board
           System.out.println("Solved Sudoku Board:");
           for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                   System.out.print(sudokuBoard[i][j] + " ");
               }
               System.out.println();
           }
       }
   }


