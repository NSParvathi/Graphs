package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Sravani
 * Problem Statement: Given an undirected graph, return a list of all nodes by traversing the graph using breadth-first search (BFS).
 * 
 * Solution :
 * I am going to use Queue data structure and visited array for BFS traversal of given graph.
 * Below are the steps to be followed:
 * 
 * 1. Take the root of the graph and add it to the queue, at the same time mark visited[root.dat] to true.* 
 * 2. repeat a loop while queue is not empty, get the node from queue and get all the adjacent nodes of the node using adjacency list.
 * 3. check whether already those adjacency nodes are visited are not using visited array. If not add them to the Queue and mark them in visited array or 
 * 	  leave them.
 * 4. Repeat above two steps until all the nodes completed in the given graph.
 * 
 * Time Complexity : O(N)+O(2E)
 * Space Complexity :O(3N) ~ O(N), Space for queue data structure visited array and an adjacency list
 *
 */
public class BFS {

	/**
	 * Here I am assuming node numbering starts from 0.
	 * @param V  - no of nodes/vertices
	 * @param adj  - adjacency nodes list
	 * @return
	 */
	public static List<Integer> bfsGraph(int V, List<List<Integer>> adj){
		
		boolean visited[] = new boolean[V];
		List<Integer> bfs = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		
		q.add(0); //first node or starting node of the graph
		visited[0] = true;
		while(!q.isEmpty()) {
			Integer node = q.poll();
			bfs.add(node);
			for(Integer it:adj.get(node)) {
				if(visited[it] == false) {
					visited[it] = true;
					q.add(it);
				}
			}
			
		}
		
		return bfs;
	}
	
	public static void main(String args[]) {
		List<List<Integer>> adj= new ArrayList<>();
		int V = 5; //5 nodes means numbering from 0 to 4
		
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<>());
		}
		
		adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
        
        List<Integer> ans = bfsGraph(V,adj);
        System.out.println(ans);
	}
}
