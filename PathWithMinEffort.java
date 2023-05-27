package graphs;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Problem Statement: 
 * 
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the
 * height of the cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) 
 * (i.e.,0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

	A route’s effort is the maximum absolute difference in heights between two consecutive cells of the route.
	
	Solution:
	
	Intuition:
		Find all the paths from source to destination. While traversing in different path store the maximum efforts in the efforts matrix and 
		for traversing use a priority queue based of effort so that always return less effort path first so that we can get less effor path overall.
		The main catch here is that while traversing we have to maintain max effort in each path and when we reach the destination we have to get 
		minimum of all the max efforts in all the routes. If you reach at one cell from different pahts we have to get the minimum beacuse we need 
		minimum effort among all the paths.
	
	Approach:
	1. Create a queue that stores the distance-node pairs in the form {dist,(row, col)} and a effort matrix with each cell initialized with a
	   very large number (1e9) and the source cell marked as ‘0’.
	2. We push the source cell to the queue along with its effort which is also 0.
	3. Pop the element at the front of the queue and look out for its adjacent nodes (left, right, bottom, and top cell).
	   Also, for each cell, check the cell if it lies within the limits of the matrix or not.
	4. If the current difference effort value of a cell from its parent effort is better and the the current max effort is smaller than already 
	   effort present in that cell then, we update the effort in the effort matrix and push it into the queue along with cell coordinates.
	5. We repeat the above three steps until the queue becomes empty or until we encounter the destination node.
	6. Return the calculated difference and stop the algorithm from reaching the destination node. If the queue becomes empty and we don’t encounter the destination node, return ‘0’ indicating there’s no path from source to destination.

		
 */
public class PathWithMinEffort {

	static class Tuple {
		int effort;
		int row;
		int col;
		Tuple(int effort, int row,int col){
			this.effort = effort;
			this.row = row;
			this.col = col;
		}
		
	}
	static int MinimumEffort(int heights[][]) {
		
		int n= heights.length;
		int m = heights[0].length;		
		
		int[][] effort = new int[n][m];
		
		for(int i=0;i<n;i++) {
			Arrays.fill(effort[i], (int)1e9);
		}
		effort[0][0] =0;
		
		//Create delta rows and delta cols arrays to traverse all directions of a cell
		int[] deltaRow = {0,-1,0,+1};
		int[] deltaCol = {-1,0,+1,0};
		
		
		Queue<Tuple> pq = new PriorityQueue<>((t1,t2)->t1.effort-t2.effort);
		pq.add(new Tuple(0,0,0));
		
		
		while(!pq.isEmpty()) {
			Tuple t = pq.poll();
			int diffEffort = t.effort;
			if(t.row == n-1 && t.col == m-1)
				return diffEffort;
			for(int i=0;i<4;i++) {
				int newRow = t.row + deltaRow[i];
				int newCol = t.col+deltaCol[i];
			
				if(newRow>=0 && newRow<n && newCol>=0 && newCol<m  ) {
					int neweffort = Math.max( Math.abs(heights[newRow][newCol] - heights[t.row][t.col]),diffEffort);
					if(neweffort < effort[newRow][newCol]) {
						effort[newRow][newCol]= neweffort;
						pq.add(new Tuple(neweffort,newRow,newCol));
					}
				}
			}
			
		}
		
		return 0;
	}

	 public static void main(String[] args) {
	       
	        int[][] heights={{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
	        System.out.println(MinimumEffort(heights));
	 }
	
	
}
