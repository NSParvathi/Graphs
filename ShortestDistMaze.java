package graphs;
/**
 * Problem Statemen:
 * 
 * Given an n * m matrix grid where each element can either be 0 or 1. You need to find the shortest distance between a given source cell to a
 * destination cell. The path can only be created out of a cell if its value is 1. 

   If the path is not possible between the source cell and the destination cell, then return -1.
   
   Solution:
   
 	1. Create a queue that stores distance and cell in the form of (distance,row number, col number)  and a distance matrix with each cell 
	initialized to 1e9(very large number to indicate unvisisted). 
	2. We push the source cell to the queue along with its distance which is 0.
	3. Pop the element from the queue and look out for its adjacent nodes (left, right, bottom, and top cell).
	   Also, for each cell, check the validity of the cell if it lies within the limits of the matrix or not.
	4, If the current distance to a cell from the source is better than the previous distance indicated by the distance matrix, 
	we update the distance in distance matrix and push it into the queue along with cell coordinates.
	4. We repeat the above two steps until the queue becomes empty or until we encounter the destination node.
	5. Return the calculated distance and stop the algorithm from reaching the destination node. If the queue becomes empty and
	 we don’t encounter the destination node, return ‘-1’ indicating there’s no path from source to destination.
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistMaze {
	
	static class Tuple{
		int dist;
		int row;
		int col;
		Tuple(int dist,int row,int col){
			this.dist = dist;
			this.row = row;
			this.col = col;
		}
	}
	static int shortestPath(int[][] grid, int[] source, int[] destination) {

		//If given source and destination are equal
		 if(source[0] == destination[0] && 
		           source[1] == destination[1]) return 0; 

		//Create a grid to store shortest distance from the source
		int n = grid.length;
		int m = grid[0].length;
		int[][] distance = new int[n][m];
		for(int i=0;i<n;i++) {
			Arrays.fill(distance[i], (int)1e9);
		}
		
		//Create delta rows and delta cols arrays to traverse all directions of a cell
		int[] deltaRow = {0,-1,0,+1};
		int[] deltaCol = {-1,0,+1,0};
		
		Queue<Tuple> q = new LinkedList<>();
		distance[source[0]][source[1]] = 0;
		q.add(new Tuple(0,source[0],source[1]));
		while(!q.isEmpty()) {
		
			Tuple t = q.poll();
			int tDist = t.dist;
			int row = t.row;
			int col = t.col;
			/*if(row == destination[0] && col == destination[1])
				return t.dist;*/
			//Traverse in all 4 directions and update shortest distance
			for(int i=0;i<4;i++) {
				int newRow = row+deltaRow[i];
				int newCol = col+deltaCol[i];
				if(newRow >=0 && newRow<n && newCol>=0 && newCol<m && grid[newRow][newCol]==1 && tDist+1 < distance[newRow][newCol]) {
					distance[newRow][newCol] = tDist+1;
					if(newRow == destination[0] && newCol == destination[1])
						return distance[newRow][newCol];
					q.add(new Tuple(tDist+1,newRow,newCol));
				}
			}
			
			
		}
		
		return -1;
	}
	
	public static void main(String args[]) {
		 
        int[] source={0,1};
        int[] destination={2,2};
        
        int[][] grid={{1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 0, 1}};
        
        System.out.println(shortestPath(grid,source,destination));
	}
}
