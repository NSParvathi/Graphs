package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: Given a binary grid of N*M. Find the distance of the nearest 1 in the grid for each cell.
 *
 * The distance is calculated as |i1  – i2| + |j1 – j2|, where i1, j1 are the row number and column number of the current cell,
 * and i2, j2 are the row number and column number of the nearest cell having value 1.
 * 
 * 
 * Approach :  
 *  Instead checking for each cell follow below steps
 *  1. Take a queue data structure, a  visited matrix, and an answer matrix.
 *  2. Traverse the entire array and get the cells that contain 1 and store them in the queue, and store 0 in the corresponding cells of the answer matrix. 
 *  3. Take the counter variable and increment it when we add new adjacent cells to the queue. Replace the cell that contains 0 with the counter.
 *  4. Repeat the above steps until all the cells are marked as visited. 
 *  Time Complexity: O(NxM + NxMx4) ~ O(N x M)

	For the worst case, the BFS function will be called for (N x M) nodes, and for every node, we are traversing for 4 neighbors, so it will take O(N x M x 4) time.

	Space Complexity: O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
 */
public class DisOfNearCellHaving1 {
	
	static class Pair{
		int row;
		int col;
		Pair(int row,int col){
			this.row = row;
			this.col = col;
		}
	}
	 public int[][] nearest(int[][] grid)
	    {
	        // Code here
		 	int n = grid.length;  // no of rows
		 	int m = grid[0].length; // no of cols
		 	boolean[][] vis = new boolean[n][m];
		 	int[][] ans = new int[n][m];
		 	
		 	//Iterate over the grid array to find nearest 1
		 	Queue<Pair> q = new LinkedList<>();
		 	
		 	//Iterate over the array and get the cells which contains 1
		 	for(int i=0;i<n;i++) {
		 		for(int j=0;j<m;j++) {
		 			if(grid[i][j] == 1) {
		 				q.add(new Pair(i,j));
		 				vis[i][j] =true;
		 				ans[i][j] = 0;
		 			}
		 		}
		 	}
		 
		 	
		 	//Now BFS traversal until queue is empty
		 	return bfsTraversal(grid,vis,ans,q,n,m);
		 	
	    }
	 
	 public int[][] bfsTraversal(int[][] grid,boolean[][] vis,int[][] ans,Queue<Pair> q,int n,int m){
		 int count =0;
			int[] rows = {-1,0,+1,0};
		 	int[] cols = {0,-1,0,+1};
		 	while(!q.isEmpty()) {
		 		
		 		count++;
		 		int size = q.size();
		 		for(int i=0;i<size;i++) {
		 			Pair p = q.poll();
			 		int row = p.row;
			 		int col = p.col;
			 		//find adjacent cells
			 		for(int j=0;j<4;j++) {
			 			int newRow = row+rows[j];
			 			int newCol = col+cols[j];
			 			if(newRow>=0 && newRow<n && newCol>=0 && newCol<m && vis[newRow][newCol] == false && grid[newRow][newCol] == 0 ) {
			 				q.add(new Pair(newRow,newCol));
			 				vis[newRow][newCol] = true;
			 				ans[newRow][newCol] = count;
			 			}
			 		}
		 		}
		 	}
		 	return ans;
	 }
	 
	 public static void main(String args[]) {
		 int[][] grid =  {{0,1,1,0},{1,1,0,0},{0,0,1,1}};
		 int[][] ans =new DisOfNearCellHaving1().nearest(grid);
		 for(int i =0;i<ans.length;i++) {
			 System.out.println();
			 for(int j=0;j<ans[i].length;j++) {
				 System.out.print(ans[i][j]+" ");
			 }
		 }
	 }
	
}
