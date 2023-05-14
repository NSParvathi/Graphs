package graphs;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *  Detect Cycle in an Undirected Graph (using DFS)
 *	Problem Statement: Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not. 
 *  
 *  Intuition: 
 *  The cycle in a graph starts from a node and ends at the same node. DFS is a traversal technique that involves the idea of recursion and backtracking. 
 *  DFS goes in-depth, i.e., traverses all nodes by going ahead, and when there are no further nodes to traverse in the current path, then it backtracks on the same path
 *  and traverses other unvisited nodes. The intuition is that we start from a source and go in-depth, and reach any node that has been previously visited in the past; 
 *  it means there’s a cycle.
 *  
 *  Approach :
 *  1. Maintain visited array, call the DFS traversal for every unvisited node.
 *  2. While traversing graph using DFS if you encounter a node which has been already visited and its not the parent of the current node means that cycle has formed. So, return
 *  the recursive call.  
 *  3. if the node is unvisited mark it as visited and continue further.
 *  
 *  Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. 
 *  In the case of connected components of a graph, it will take another O(N) time.
 *
 *	Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.
 *  
 */
public class CycleDetectionDFS {

	public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
		boolean[] visited = new boolean[V];
		Arrays.fill(visited, false);
		int[] parent = new int[V];
		Arrays.fill(parent, -1);
		for(int i=0;i<V;i++) {
			if(visited[i] == false) {
				
				if(dfsTraversal(i,adj,visited,parent))
					return true;;
			}
		}
		return false;
		
	}
	
	public static boolean dfsTraversal(int s,ArrayList<ArrayList<Integer>> adj,boolean[] visited,int[] parent) {
		
		visited[s] = true;
		
		for(int it:adj.get(s)) {
			if(visited[it] == false) {
				parent[it] = s;
				if(dfsTraversal(it,adj,visited,parent))
					return true;;
			}else {
				if(it!= parent[s])
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		 ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	        for (int i = 0; i < 4; i++) {
	            adj.add(new ArrayList < > ());
	        }
	        adj.get(0).add(1);
	        adj.get(1).add(0);
	        adj.get(1).add(2);
	        adj.get(2).add(1);
	        
	        boolean ans = isCycle(4,adj);
	        System.out.println("is Cycle : "+ans);
	}
}
