package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



/*
 * Problem Statement: 
 * Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid) consisting of '0's (Water) and '1's(Land).
 * Find the number of islands.

 * Note: An island is either surrounded by water or boundary of grid and is formed by connecting adjacent lands horizontally or vertically
 *  or diagonally i.e., in all 8 directions.
 *  
 *  Solution :
 *  
 *  We can follow either of the traversal techniques. I will be solving it using BFS traversal, but you can apply DFS traversal as well. 
 *  Steps are as follows:
 *  
 *  1. For BFS traversal to start and visit all the nodes connected to it, we need a start node and a visited array. 
 *     Create a corresponding visited array, which will be an NxM matrix. 
 * 	2. We will be looping through the NxM cell, and for every unvisited node in the visited matrix, we will call the BFS for it,
 *     so that the BFS now expands and marks all the cells attached to it as visited. 
 *  3. Increment the counter variable at the start of each BFS call for the unvisited node, which is the number of islands.
 *  4. BFS function call will run through all the unvisited neighbouring land cells in the eight directions. 
 *	5. At the start of the BFS, we mark the node as visited, and then we check for adjacent nodes. If they are unvisited and contain one
       means land, then we add them to the queue, mark them as visited, and loop until the queue is empty, meaning all the adjacent nodes whose value is 1 are visited.
 *
 * 	Time Complexity: O(N x M x log(N x M)) + O(NxMx8) ~ O(N x M), For the worst case, assuming all the pieces as land,
 *  the DFS function will be called for (N x M) nodes, and for every node, we are traversing for 8 neighbors, it will take O(N x M x 8) time.
 *  Set at max will store the complete grid, so it takes log(N x M) time.
 *
 *  Space Complexity ~ O(N x M), O(N x M) for the visited array and set takes up N x M locations at max. 
 */
public class NumberOfIslands {

	static class Pair{
		Integer row;
		Integer col;
		Pair(Integer row, Integer col){
			this.row = row;
			this.col = col;
		}
	}
	 public static int numIslands(char[][] grid) {
	        // Code here
		 	int n = grid.length;
		 	int m = grid[0].length;
		 	boolean[][] visited = new boolean[n][m];		 	
		 	int count =0;		 	
		 	for(int i=0;i<n;i++) {
		 		for(int j=0;j<m;j++) {
		 			if(visited[i][j] == false && grid[i][j]==1) {
		 				
		 				count++;
		 				bfsTraversal(grid,visited,i,j);
		 			}
		 		}
		 	}
		 return count;	
	    }
	 
	 public static void bfsTraversal(char[][] grid,boolean[][] visited,int row,int col) {
		 visited[row][col] = true;
		 Queue<Pair> q = new LinkedList<>();
		 q.add(new Pair(row,col));
		 int n = grid.length;
		 int m = grid[0].length;
		 
		 while(!q.isEmpty()) {
			 row = q.peek().row;
			 col = q.peek().col;
			 q.poll();
			
		 for(int deltaRow = -1;deltaRow<=1;deltaRow++) {
			 	
			 for(int deltaCol = -1; deltaCol<=1;deltaCol++) {
				int newRow = row+deltaRow;
				int newCol = col+deltaCol;
				if(newRow>=0 && newRow<n && newCol >=0 && newCol<m && visited[newRow][newCol]==false && grid[newRow][newCol]==1) {
					q.add(new Pair(newRow,newCol));
					visited[newRow][newCol] = true;
				}
			 }
		 }
		 
	 }
	 }
	 
	 public static void main(String args[]) {
		 char[][] grid = {{0,1},{1,0},{1,1},{1,0}};
		 System.out.println(numIslands(grid));
	 }
	 
}
