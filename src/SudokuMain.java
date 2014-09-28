import java.util.Scanner;
import java.util.Stack;

public class SudokuMain {
	public static void main(String args[]) {
		SudokuGrid current_grid = new SudokuGrid();	
		Stack <SudokuGrid> grids = new Stack<SudokuGrid>();
		Scanner input = new Scanner(System.in);

		grids.push(current_grid);
		
		while (true) {
			printMenu();
			System.out.println("Option = ");
			switch (input.nextInt()) {
			case 0:
				System.out.println("EXITING Sudoku solver");
				System.exit(0);
				break;
			case 1:
				solve(grids);
				break;
			case 2:
				grids.peek().printGrid();
				break;
			}
		}
	}

	private static void solve(Stack<SudokuGrid> grids){
		SudokuGrid current_grid = grids.pop();
		//get empty cell
		for(int r=0;r<9;r++){
			for( int c=0; c<9; c++){
				if(current_grid.isEmptyCell(r, c)){
					// cell is empty
					grids.push(current_grid);// save grid on stack
					
					current_grid = new SudokuGrid(current_grid);
					for(int value=1;value<=9;value++)
					{	
						current_grid.fillcell(r, c, value);
						current_grid.printGrid();
						if(current_grid.isValidGrid(r,c,value)){
							grids.push(current_grid);
							solve(grids);
							
							// check if finished
							if(current_grid.isFilled()){
								System.out.println("FINISHED");
								current_grid.printGrid();
								System.exit(0);
							}
						}
						else
							System.out.println("invalid combo");
					} 
					// at this point we have tested all valid combos, and
					// found none, we need to restore sudoku grid to valid grid
					System.out.println("DEADEND, backtracking, popped grid follows");
					current_grid = grids.pop();
					current_grid.printGrid();
					return;
				}
			}
		}
	}

	public static void printMenu() {
		System.out.println("0 - Exit");
		System.out.println("1 - Solve Sudoku");
		System.out.println("2 - Print Sudoku Grid");
	}
}