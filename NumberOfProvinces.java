package graphs;
/*
 * Problem Statement:Given an undirected graph with V vertices. We say two vertices u and v belong to a single province if there is a path 
 * from u to v or v to u. Your task is to find the number of provinces.
 * 
 * Solution: Below are the steps to be followed
 *	1. We need a visited array initialized to false, representing the nodes that are not visited. 
 *  2. Run the for loop looping from 0 to N, and call the BFS for the first unvisited node. 
 *  2. BFS function call will make sure that it starts the BFS call from that unvisited node, and visits all the nodes that are in that province,
 *     and at the same time, it will also mark them as visited. 
 *  3. Since the nodes traveled in a traversal will be marked as visited, they will no further be called for any further BFS traversal. 
 *  4. Keep repeating these steps, for every node that you find unvisited, and visit the entire province. 
 *  5. Add a counter variable to count the number of times the BFS function is called, as in this way we can count the total number of 
 *     starting nodes, which will give us the number of provinces.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfProvinces {

	
public static void traverse(int V, List<List<Integer>> adj,boolean[] vis,Integer n){
		Queue<Integer> q = new LinkedList<>();
		
		q.add(n); //first node or starting node of the graph
		vis[n] = true;
		while(!q.isEmpty()) {
			Integer node = q.poll();
			
			for(Integer it:adj.get(node)) {
				if(vis[it] == false) {
					vis[it] = true;
					q.add(it);
				}
			}
			
		}		
	}

	public static int numOfProv(int V,List<List<Integer>> adj) {
			int count =0;
			boolean vis[] = new boolean[V];
			for(int i=0;i<V;i++) {
				if(vis[i]== false) {
					count++;
					traverse(V,adj,vis,i);
				}
			}
			return count;
	}
	
	public static void main(String arg[]) {
		List<List<Integer>> adj = new ArrayList<>();
		
		adj.add(new ArrayList<Integer>());
        adj.get(0).add(0, 1);
        adj.get(0).add(1, 0);
        adj.get(0).add(2, 1);
        adj.add(new ArrayList<Integer>());
        adj.get(1).add(0, 0);
        adj.get(1).add(1, 1);
        adj.get(1).add(2, 0);
        adj.add(new ArrayList<Integer>());
        adj.get(2).add(0, 1);
        adj.get(2).add(1, 0);
        adj.get(2).add(2, 1);
        System.out.println(numOfProv(3,adj));
	}
}
