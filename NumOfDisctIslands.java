package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Problem Statement: Given a boolean 2D matrix grid of size N x M. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically)
 *  forms an island. Two islands are considered to be same if and only if one island is equal to another (not rotated or reflected).
 *  
 *  Solution :
 *  Two islands are said to be distinct if they are entirely different in shapes. Tow identify 2 islands are distinc or not we have to store their shapes in the data structure.
 *  Depending on the shape of the island formed, we count the number of islands.
 *  
 *  How to store shapes? 
 *  We can store the shapes in a set data structure, then the set will return unique islands. We can store the coordinates in a vector or a list.
 *  But how to figure out if the coordinates stored in the set data structure are identical? We can call one of the starting points a base, and subtract the base coordinates 
 *  from the land’s coordinates (Cell Coordinates – Base coordinates).
 *  Identical islands restuls same coordinates in the list even at different places and of same shape, and when we store the list to set it allows only unique coordinate list.
 *  
 *  Approach:
	We can follow either of the traversal techniques. We will be solving it using DFS traversal, but you can apply BFS traversal as well. 

	DFS is a traversal technique that involves the idea of recursion and backtracking. DFS goes in-depth, i.e., traverses all nodes by going ahead, and when there are no further nodes
	to traverse in the current path, then it backtracks on the same path and traverses other unvisited nodes. 

 *	The algorithm steps are as follows:

	1. For DFS traversal to start and visit all the nodes connected to it, we need a start node and a visited array. Create a corresponding visited array, which will be an NxM matrix. 
	2. We will be looping through the NxM cell, and for every unvisited node in the visited matrix, we will call the DFS for it, so that the DFS now expands and marks all the cells
	   attached to it as visited. 
	3. DFS function calls will run through all the unvisited neighboring land cells in the 4 directions. 
	4. At the start of the DFS, we mark the nodes as visited and stored, and then we check for their adjacent nodes. If they are unvisited, we call DFS for them one by one. 
	5. During the DFS call, every time we visit a cell, we will store its resultant coordinates into a list, and when the overall DFS gets completed, we will store the list into a set DS.
	6. Keep repeating these steps for every unvisited cell and visit the entire island. 
	7. The set data structure contains the list of unique islands only, so return the length of the set. In this way, we will get the number of distinct islands.
 */
public class NumOfDisctIslands {
		
	/*
	 * static class Pair{ int row; int col; Pair(int row,int col){ this.row = row;
	 * this.col = col; } public String toString() { return
	 * Integer.toString(row)+","+Integer.toString(col); }
	 * 
	 * }
	 */
	 
	public static int distincIsland(int[][] grid) {
		
		// we need visisted array and list to store resultant coordinates and then set to store distinc islands
		
		int n = grid.length;
		int m = grid[0].length;
		boolean[][] vis = new boolean[n][m];
		
		Set<List<String>> set = new HashSet<>();
		
		distinct(grid,vis,set,n,m);
		return set.size();
		
	}
	
	public static Set<List<String>> distinct(int[][] grid,boolean[][] vis, Set<List<String>> set,int n,int m) {
		
		//traverse for each cell and strat dfs it the cell is not visisted and it is land
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(vis[i][j]==false &&  grid[i][j] == 1) {
					List<String> list = new ArrayList<>();
					vis[i][j] = true;
					dfs(i,j,grid,vis,list,n,m,i,j);
					System.out.println(list);
					//dfs traversal completed means one island traversal done so add the list of coordinates of that island to set
					set.add(list);
				}
			}
		}
		
		return set;
	}
	/*
	 * row,col - base coordinates which needs to be carry throught out the dfs traversal and 
	 * row1 and col1 are current cell coordinates
	 */
	public static void dfs(int row,int col,int[][] grid,boolean[][] vis,List<String> list,int n,int m,int row1,int col1) {
		list.add(toString(row1-row,col1-col));
		int[] rows = {-1,0,+1,0};
		int[] cols = {0,-1,0,+1};
		
		for(int i=0;i<4;i++) {
			int newRow = row1+rows[i];
			int newCol = col1+cols[i];
			if(newRow>=0 && newRow<n && newCol >=0 && newCol<m && vis[newRow][newCol] == false && grid[newRow][newCol]==1) {
				vis[newRow][newCol] = true;
				
				dfs(row,col,grid,vis,list,n,m,newRow,newCol);
			}
		} 
	}
	
	/* Instead of using Pair class to store coordinates i am converting coordinates into String and then adding to List
	 * The reason behind is that for the Set class comparision means for not allowing duplicates
	 */
	public static String toString(int row,int col) {
		return Integer.toString(row)+" "+Integer.toString(col);
	}
	
	public static void main(String args[]) {
		 int grid[][] = {
			        {1, 1, 0, 1, 1},
			        {1, 0, 0, 0, 0},
			        {0, 0, 0, 0, 1},
			        {1, 1, 0, 1, 1}};
		 
		 
		 int distinctIslands = distincIsland(grid);
		 System.out.println(distinctIslands);	 
 
	}
}
