package graphs;
/*
 * Problem Statement: Given an adjacency list of a graph adj of V no. of vertices having 0 based index. Check whether the graph is 
 * bipartite or not.

 * If we are able to colour a graph with two colours such that no adjacent nodes have the same colour, it is called a bipartite graph.
 * 
 * Solution: 
 * Intuition:.
	A bipartite graph is a graph which can be coloured using 2 colours such that no adjacent nodes have the same colour. 
	Any linear graph with no cycle is always a bipartite graph. With a cycle, any graph with an even cycle length can also be a bipartite
	graph. So, any graph with an odd cycle length can never be a bipartite graph.

	The intuition is the brute force of filling colours using any traversal technique, just make sure no two adjacent nodes have the same
	colour. If at any moment of traversal, we find the adjacent nodes to have the same colour, it means that there is an odd cycle, or it
	cannot be a bipartite graph. 
	
	Approach:
	I will be solving this using BFS traversal
	
	The Steps are as follows:
	
	1. For BFS traversal, we need a start node and a visited array but in this case, instead of a visited array, we will take a colour array
	   where all the nodes are initialised to -1 indicating they are not coloured yet.
	2. In the BFS function call, make sure to pass the value of the assigned colour, and assign the same in the colour array. 
	   We will try to colour with 0 and 1. We will start with the colour 0 . 
	3. In BFS traversal, we travel in leve wise of all its uncoloured neighbours using the adjacency list. For every uncoloured node,
	   initialise it with the opposite colour to that of the current node.
	4. If at any moment, we get an adjacent node from the adjacency list which is already coloured and has the same colour as the current
	 	node, we can say it is not possible to colour it, hence it cannot be bipartite. Thereby return a false indicating the given graph 
	 	is not bipartite; otherwise, keep on returning true.

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraphBFS {

	public static boolean checkBipartiteGraph(int V,ArrayList<ArrayList<Integer>> adj){
		
		int[] colors = new int[V];
		Arrays.fill(colors,-1);
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=0;i<V;i++) {
			if(colors[i]==-1) {
				q.add(i);
				colors[i] =0;
				if(bfs(colors,adj,q)==false)
					return false;
			}
		}
		return true;
		
	}
	
	public static boolean bfs(int[] colors,ArrayList<ArrayList<Integer>> adj,Queue<Integer> q) {
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int node = q.poll();
				
				int color = colors[node];
				
				
				int adjColor= 1-color;
				
			for(Integer it:adj.get(node)) {
				
				if(colors[it] == -1) {
					colors[it] = adjColor;
					
					q.add(it);
				}else {
				if(colors[it] != adjColor ) {
					
						return false;
					}
				}
			}
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 9; i++) {
            adj.add(new ArrayList < > ());
        }
       adj.get(1).add(2);
       adj.get(2).add(1);
       adj.get(2).add(3);
       adj.get(3).add(2);
       adj.get(2).add(4);
       adj.get(4).add(2);
       adj.get(3).add(5);
       adj.get(5).add(3);
       adj.get(4).add(6);
       adj.get(6).add(4);
       adj.get(5).add(7);
       adj.get(7).add(5);
       adj.get(6).add(7);
       adj.get(7).add(6);
       adj.get(7).add(8);
       adj.get(8).add(7);
       
       
       
        
        System.out.println(checkBipartiteGraph(9,adj));
	}
}
