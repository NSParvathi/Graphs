package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Sravani
 * Problem Statement : 
 * 
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image. *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, 
 * "flood fill" the image. *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color 
 * as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. 
 * Replace the color of all of the aforementioned pixels with the newColor.
 * 
 * Solution : 
 * 1. Take a variable named old_color to store the old color 
 * 2. start from the starting pixel and assign old_color = starting pixel color
 * 3. Take a Queue for BFS traversal of adjacency pixels.
 * 4. Start from starting pixel and add to queue. While queue is not empty pop the pixel from queue and traverse to all 4 adjacency pixels.
 * 	  If those are of same color of old color add then to queue and then change the color.
 * 5. continue until you find the adjacency pixels with old color.
 */
public class FloodFillAlgo {
	static class Pair{
		int row;
		int col;
		Pair(int row,int col){
			this.row = row;
			this.col = col;
		}
	}
	/**
	 *  I am using BFS Traversal to find adjacency nodes
	 * @param image  - represent image pixels
	 * @param sr  - source row number
	 * @param sc  - source column number
	 * @param newColor - new color to be updated
	 * @return
	 */
	public static int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        // Code here 
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(sr,sc));
		int oldColor = image[sr][sc];
		
		int[] rows = {-1,0,+1,0};
		int[] cols = {0,-1,0,+1};
		int n = image.length;
		int m = image[0].length;
		int[][] ans = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				ans[i][j]= image[i][j];
			}
		}
		ans[sr][sc] = newColor;
		while(!q.isEmpty()) {
			
			int row = q.peek().row;
			int col = q.peek().col;
			q.poll();
			// now find adjacency pixels
			for(int i=0;i<4;i++) {
				int newRow = row+rows[i];
				int newCol = col+cols[i];
				if(newRow>=0 && newRow<n && newCol>=0 && newCol<m && image[newRow][newCol] == oldColor && 
						ans[newRow][newCol] != newColor)
				{
					q.add(new Pair(newRow,newCol));
					ans[newRow][newCol] = newColor;
				}
			}
			
		}
		return ans;
    }
	
	/**
	 * using DFS Traversal
	 * @param image
	 * @param sr
	 * @param sc
	 * @param newColor
	 * @return
	 */
	public static int[][] floodFill_dfs(int[][] image, int sr, int sc, int newColor){
		int rows[] = {-1,0,+1,0};
		int cols[] = {0,-1,0,+1};
	
		int n = image.length;
		int m = image[0].length;
		int[][] ans = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				ans[i][j]= image[i][j];
			}
		}
		floodFill(image,ans,sr,sc,newColor,image[sr][sc],rows,cols,n,m);
		return ans;
	}
	
	public static void floodFill(int[][] image,int[][] ans,int row,int col,int newColor,int oldColor,int[] rows,int[] cols,int n,int m) {
		ans[row][col] = newColor;
		for(int i=0;i<4;i++) {
			int newRow = row+rows[i];
			int newCol = col+cols[i];
			if(newRow>=0 && newRow<n && newCol>=0 && newCol <m && image[newRow][newCol] == oldColor && ans[newRow][newCol] != newColor) {
				floodFill(image,ans,newRow,newCol,newColor,oldColor,rows,cols,n,m);
			}
		}
		
		
			
	}
	
	public static void main(String args[]) {
		int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1, sc = 1, newColor = 2;
		image = floodFill(image,sr,sc,newColor);
		for(int i=0;i<image.length;i++) {
			for(int j=0;j<image[i].length;j++) {
				System.out.print(image[i][j]+" ");
			}
			System.out.println();
		}
		
	}
		
}
