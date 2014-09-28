public class SudokuGrid {
	final static int num_rows = 9;
	final static int num_cols = 9;
	int grid[][] = new int[num_rows][num_cols];
	
	int valid_row;
	int valid_col;
	int valid_val;
	
	public SudokuGrid(){
//	1
//		1 5 7 6 4 3 9 8 2 
//		6 4 9 2 1 8 5 3 7 
//		8 3 2 9 5 7 1 4 6 
//		7 6 4 1 3 5 2 9 8 
//		2 1 5 8 6 9 3 7 4 
//		3 9 8 4 7 2 6 5 1 
//		9 8 3 7 2 1 4 6 5 
//		5 2 6 3 8 4 7 1 9 
//		4 7 1 5 9 6 8 2 3 

	}
	
	// makes a copy of a grid
	public SudokuGrid(SudokuGrid copy){
		for(int r=0;r<num_rows;r++){
			for(int c=0;c<num_cols;c++){
				grid[r][c]=copy.grid[r][c];
			}
		}
	}
	
	public boolean isValidGrid(int r, int c, int value){
		//check row
		for(int row=0;row<num_rows;row++){
			if(row==r)
				continue;
			if(grid[row][c] == value)
				return false;
		}
		
		//check column
		for(int col=0;col<num_cols;col++){
			if(col==c)
				continue;
			if(grid[r][col] == value)
				return false;
		}
		
		//check 3x3 matrix
		int base_row;
		int base_col;
		
		if(r<3)
			base_row = 0;
		else if (r<6 & r>=3)
			base_row = 3;
		else
			base_row = 6;
		
		if(c<3)
			base_col = 0;
		else if (c<6 & c>=3)
			base_col = 3;
		else
			base_col = 6;
		
		for(int row=0;row<3;row++){
			for(int col=0;col<3;col++){
				int offset_row = base_row + row;
				int offset_col = base_col + col;
				if(grid[offset_row][offset_col] == value){
					if(offset_row==r & offset_col==c)
						continue;
					return false;
				}
			}
		}
		System.out.println("ALL TRUE for "+r+","+c+" "+value);
		return true;
	}
	
	public void printGrid() {
		System.out.println();
		for (int i = 0; i <num_rows; i++) {
			for (int j = 0; j < num_cols; j++) {
				if(grid[i][j] == 0)
					System.out.printf("%c ", '.');
				else
					System.out.printf("%d ", grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public boolean isFilled(){
		for (int r=0;r<num_rows;r++){
			for (int c=0;c<num_cols;c++){
				if(isEmptyCell(r,c))
					return false;
			}
		}
		return true;
	}
	public void fillcell(int r,int c, int value) {
		grid[r][c] = value;
	}
	public boolean isEmptyCell(int r, int c) {
		if(grid[r][c] == 0)
			return true;
		else
			return false;
	}
}
