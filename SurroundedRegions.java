package graphs;
/**
 * Problem Statement : Given a matrix mat of size N x M where every element is either ‘O’ or ‘X’. Replace all ‘O’ with ‘X’ that is surrounded by ‘X’. An ‘O’ (or a set of ‘O’) is 
 * considered to be surrounded by ‘X’ if there are ‘X’ at locations just below, just above just left, and just right of it.
 * 
 * Intuition:.
	The boundary elements in the matrix cannot be replaced with ‘X’ as they are not surrounded by ‘X’ from all 4 directions. This means if ‘O’ (or a set of ‘O’) is connected 
	to a boundary ‘O’ then it can’t be replaced with ‘X’. 

	The intuition is that we start from boundary elements having ‘O’ and go through its neighboring Os in 4 directions and mark them as visited to avoid replacing them with ‘X’. 
	
 *  Approach:
    We can follow either of the traversal techniques as long as we are starting with a boundary element and marking all those Os connected to it. We will be solving it 
    using DFS traversal, but you can apply BFS traversal as well. 

	DFS is a traversal technique that involves the idea of recursion.. DFS goes in-depth, i.e., traverses all nodes by going ahead, and when there are no further nodes to traverse in the current path, then it backtracks on the same path and traverses other unvisited nodes.

	The algorithm steps are as follows:

		1. Create a corresponding visited matrix and initialize it to 0.
		2. Start with boundary elements, once ‘O’ is found, call the DFS function for that element and mark it as visited. In order to traverse for boundary elements,
		   you can traverse through the first row, last row, first column, and last column. 
		3. DFS function call will run through all the unvisited neighboring ‘O’s in all 4 directions and mark them as visited so that they are not converted to ‘X’ in the future.
		   The DFS function will not be called for the already visited elements to save time, as they have already been traversed. 
		4. When all the boundaries are traversed and corresponding sets of ‘O’s are marked as visited, they cannot be replaced with ‘X’. 
		   All the other remaining unvisited ‘O’s are replaced with ‘X’. This can be done in the same input matrix as the problem talks about replacing the values, otherwise 
		   tampering with data is not advised. 
 */
public class SurroundedRegions {

	static char[][] fill(int n, int m, char a[][])
    {
        boolean[][] vis = new boolean[n][m];
		//Traverse the given matrix and find all the boundary 0's
		// boundary rows are 0 and n-1, boundary columns are 0 and m-1
		
		// traversing first row and last row
		for(int j=0;j<m;j++) {
			
			if(vis[0][j] == false && a[0][j]== 'O') {
				//call dfs traversal to find 'O's adjacent to top boundary O
				dfs(n,m,a,vis,0,j);
			}
			
			if(vis[n-1][j] == false && a[n-1][j] == 'O') {
				//call dfs traversal to find 'O's adjacent to bottom boundary O
				dfs(n,m,a,vis,n-1,j);
			}
		}
		
		//traversing first column and last column
		for(int i =0;i<n;i++) {
			if(vis[i][0] == false && a[i][0] == 'O') {
				//call dfs traversal to find 'O's adjacent to left boundary O
				dfs(n,m,a,vis,i,0);
			}
			
			if(vis[i][m-1] == false && a[i][m-1] == 'O') {
				//call dfs traversal to find 'O's adjacent to right boundary O
				dfs(n,m,a,vis,i,m-1);
			}
		}
		
		//Now traverse the given matrix and visited array and change the O's to X's in those cells that are not marked in the visited array.
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(vis[i][j] == false && a[i][j]=='O') {
					a[i][j] = 'X';
				}
			}
		}
		return a;
    }
	
	static void dfs(int n,int m,char a[][],boolean[][] vis,int row,int col) {
	
		//These below arrays are used to find cells in 4 directtions
		int[] rows = {-1,0,+1,0};
		int[] cols = {0,-1,0,+1};
		vis[row][col] = true;
		for(int i=0;i<4;i++) {
			int newRow= row+rows[i];
			int newCol = col+cols[i];
			
			if(newRow>=0 && newRow<n && newCol >=0 && newCol<m && vis[newRow][newCol] == false && a[newRow][newCol] == 'O') {
				vis[newRow][newCol] = true;
				dfs(n,m,a,vis,newRow,newCol);
			}
		}
		
	}
	
	public static void main(String args[]) {
		
		//Test cases
		char mat[][] = {
		        {'X', 'X', 'X', 'X','X'}, 
		        {'O', 'X', 'X', 'X','O'}, 
		        {'O', 'X', 'X', 'O','X'}, 
		        {'X', 'X', 'X', 'O','O'}};
		
		char[][] ans = fill(4,5,mat);
		 for(int i = 0;i < 4;i++) {
	            for(int j = 0;j < 5;j++) {
	                System.out.print(ans[i][j] + " ");
	            }
	            System.out.println();
	        }
	}
}
