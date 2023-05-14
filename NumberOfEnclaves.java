package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: 
 * You are given an N x M binary matrix grid, where 0 represents a sea cell and 1 represents a land cell. 
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * Find the number of land cells in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 * 
 * Intuition: 
 * The land cells present in the boundary cannot be counted in the answer as we will walk off the boundary of the grid.
 * Also, land cells connected to the boundary land cell can never be the answer. 
 *
 * The intuition is that we need to figure out the boundary land cells, go through their connected land cells and mark them as visited.
 * The sum of all the remaining land cells will be the answer.
 * 
 * Approach:
 * 
 * We can use either BFS or DFS traversal to traverse from boundary land cells to adjacent land cells which are attached to boundary land
 * cell.
 * Here I am using BFS traversal. BFS traversal requires queue data structure which stores Pair of row number and column number.
 * 
 * Below are the steps to be followed:
 * 1. Take a visited array and queue data structure.
 * 2. Traverse a given matrix and find the boundary 1's and mark them visited and store them in queue.
 * 3. For each element of queue apply BFS traversal and find the adjacent land cell and mark them visited as well.
 * 4. Repeat above steps until all the land cells which are connected to boundary are marked as visited.
 * 5. Now traverse the visited array,given array and count the no of land cells which are not visited and return that count which 
 * 	  represents the no of enclaves.   
 */
public class NumberOfEnclaves {

	static class Pair{
		int row;
		int col;
		Pair(int row,int col){
			this.row = row;
			this.col = col;
		}
	}
	static int numberOfEnclaves(int[][] grid) {

        Queue<Pair> q = new LinkedList<>();
        
		//find the boundary 1's and add them to queue
		int n = grid.length;
		int m= grid[0].length;
		boolean[][] vis = new boolean[n][m];
		//Finding boundary land cells in the first row and last row
		for(int j =0;j<m;j++) {
			if(vis[0][j] == false && grid[0][j] == 1) {
				vis[0][j] = true;
			 q.add(new Pair(0,j));
			}
			
			if(vis[n-1][j] == false && grid[n-1][j] == 1) {
				vis[n-1][j] = true;
				q.add(new Pair(n-1,j));
			}
		}
		
		//find boundary land cells in left and right columns
		for(int i=0;i<n;i++) {
			if(vis[i][0] == false && grid[i][0] == 1){
				vis[i][0] = true;
				q.add(new Pair(i,0));
			}
			if(vis[i][m-1] == false && grid[i][m-1] == 1) {
				vis[i][m-1] = true;
				q.add(new Pair(i,m-1));
			} 
			
		}
		
		int[] rows = {-1,0,+1,0};
		int[] cols = {0,-1,0,+1};
		//now do BFS traversal for the queue cells and find the land cell which can reach to boundary
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i=0;i<4;i++) {
				int newRow = p.row+rows[i];
				int newCol = p.col+cols[i];
				if(newRow>=0 && newRow<n && newCol>=0 && newCol<m && vis[newRow][newCol] == false && grid[newRow][newCol] == 1) {
					vis[newRow][newCol] = true;
					q.add(new Pair(newRow,newCol));
				}
			}
		}
		
		//Now traverse the visited array and given grid and count the land cells which are not visited.
		
		int count =0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++) {
				if(vis[i][j] == false && grid[i][j] == 1)
					count++;
			}
		
		return count;
    }
	
	public static void main(String args[]) {
		int grid[][] = {{0, 0, 0, 0},
	            {1, 0, 1, 0},
	            {0, 1, 1, 0},
	            {0, 0, 0, 0}};
		System.out.println(numberOfEnclaves(grid));
	}
}
