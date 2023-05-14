package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: Given an undirected graph with V vertices and E edges, check whether it contains 
 * any cycle or not.
 *
 * Intuition :  
 * BFS traversal means we traverse level-wise for each node over all the directions.
 * That means for every node we traverse, we take a different path. So from any node, traverse through different paths.and if you reach the node that has already been visited, 
 * then a cycle has formed.
 *  
 * Approach: Below are the steps to be followed.
 * 1. Start from the first node and start BFS traversal.
 * 2. Maintain the visited node list.
 * 3. If you reach a node that has already been visited, that node should not be the parent node (just visited). Then A cycle has formed, in which we reached the same node from
 *    different paths, or else there is no cycle.
 * 4. Take a queue data structure and visited array. Push the pair that contains the node and its parent to the queue, and mark the node as visited.
 * 5. Pop the node from the queue and visit the neighbours except the parent. Continue this process until all the nodes are done, or if you encounter a cycle. 
 * 6. A graph may contain different components, meaning not all nodes are connected. So for each node, check whether it has been visited or not. If not, call traverse (node).
 *  
 *  Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.

Space Complexity: O(N) + O(N) ~ O(N), Space for queue data structure and visited array.
 */
public class CycleDetectionBFS {

	static class Pair{
		int node;
		int parent;
		Pair(Integer node, Integer parent){
			this.node = node;
			this.parent = parent;
			}
	}
	public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
		boolean[] visited = new boolean[V];
		Arrays.fill(visited,false);
		
		for(int i=0;i<V;i++) {
			if(visited[i] == false) {				
				if(traverse(adj,visited,i))
					return true;
			}
		}
			return false;
    }
	
	public static boolean traverse( ArrayList<ArrayList<Integer>> adj,boolean[] visited,Integer i) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i,-1));
		visited[i] = true;
		
		while(!q.isEmpty()) {
			 int node = q.peek().node;
			 int parent = q.peek().parent;
			q.remove();
			for(Integer it:adj.get(node)) {
				if(visited[it]==false) {
					q.add(new Pair(it,node));
					visited[it]=true;
					}
				else			
					if(it!=parent)
						return true;
				
			}
		}
		return false;
		
	}
	
	public static void main(String args[]) {
		 ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	        for (int i = 0; i < 4; i++) {
	            adj.add(new ArrayList < > ());
	        }
	        adj.get(0).add(1);
	        adj.get(1).add(0);
	        adj.get(1).add(2);
	        adj.get(2).add(1);
	        adj.get(2).add(0);
	        adj.get(0).add(2);
	        
	        boolean ans = isCycle(4,adj);
	        System.out.println("is Cycle : "+ans);
	}
}
