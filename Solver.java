import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] answer = new int[][] {{5,3,4,6,7,8,9,1,2},
                                               {6,7,2,1,9,5,3,4,8},
                                               {1,9,8,3,4,2,5,6,7},
                                               {8,5,9,7,6,1,4,2,3},
                                               {4,2,6,8,5,3,7,9,1},
                                               {7,1,3,9,2,4,8,5,6},
                                               {9,6,1,5,3,7,2,8,4},
                                               {2,8,7,4,1,9,6,3,5},
                                               {3,4,5,2,8,6,1,8,9}};
    
        int[][] puzzle1 = new int[][] {{0,0,3,0,1,0,5,0,0},
                                       {8,0,0,3,9,5,0,0,1},
                                       {1,5,0,0,0,0,0,2,7},
                                       {0,8,0,0,7,0,0,5,0},
                                       {6,2,0,9,0,4,0,1,3},
                                       {0,9,0,0,2,0,0,7,0},
                                       {9,1,0,0,0,0,0,3,4},
                                       {2,0,0,7,4,8,0,0,9},
                                       {0,0,6,0,3,0,2,0,0}};
        ;
                                      
        System.out.println("Puzzle Before: ");
        System.out.println();
        for (int i = 0; i < puzzle1.length; i++) {
            for(int j = 0; j < puzzle1.length; j++){
              System.out.print(puzzle1[i][j] + " "); 
              if(j % 3 == 2) System.out.print("| ");
            }
            System.out.println();
            if(i % 3 == 2){
              System.out.println("----------------------");
            }
             
        }
        
        int[][] solution = puzzle1.clone();
        solve(solution);
        
        
        
        System.out.println("Puzzle After: ");
        System.out.println();
        for (int i = 0; i < puzzle1.length; i++) {
            for(int j = 0; j < puzzle1.length; j++){
              System.out.print(puzzle1[i][j] + " "); 
              if(j % 3 == 2) System.out.print("| ");
            }
            System.out.println();
            if(i % 3 == 2){
              System.out.println("----------------------");
            }
             
        }
        System.out.println("Puzzle Solved, here is the solution: ");
        System.out.println();
        for (int i = 0; i < puzzle1.length; i++) {
            for(int j = 0; j < puzzle1.length; j++){
              System.out.print(solution[i][j] + " "); 
              if(j % 3 == 2) System.out.print("| ");
            }
            System.out.println();
            if(i % 3 == 2){
              System.out.println("----------------------");
            }
             
        }
        
    }

    /**
     * Simple recursive, deterministic, depth first search, backtracking algorithm for Sudoku
     * Row major format puzzle as input
     *
     * @param puzzle Row major format puzzle as input
     * @return
     */
    public static boolean solve(int[][] puzzle) {
        int N = 3; // length ^ 0.25
        int SIZE = puzzle.length;
        boolean noEmptyCells = true;
        int myRow = 0, myCol = 0;
        for (int i = 0; i < SIZE; i++) {
          if(!noEmptyCells) break;
          for(int j = 0; j < SIZE; j++){
            if (puzzle[i][j] == 0) {
                myRow = i;
                myCol = j;
                noEmptyCells = false;
                break;
            }
          }
        }
        if (noEmptyCells) return true;

        for (int choice = 1; choice <= puzzle.length; choice++) {
            boolean isValid = true;
            int gridRow = myRow / N;
            int gridCol = myCol / N;
            // check grid for duplicates
            for (int row = N * gridRow; row < N * gridRow + N - 1; row++){
              for (int col = N * gridCol; col < N * gridCol + N - 1; col++){
                if (puzzle[row][col] == choice){
                    isValid = false;
                }
                  
              }
            }            
            // row & column
            for (int ji = 0; ji < SIZE; ji++){
              if (puzzle[myRow][ji] == choice || puzzle[ji][myCol] == choice){
                  isValid = false;
                  break; 
              }
            }

            if (isValid) {
              puzzle[myRow][myCol] = choice;
              boolean solved = solve(puzzle);
              
              if (solved) return true;
              else puzzle[myRow][myCol] = 0;
            }
        }
        return false;
    }
}
