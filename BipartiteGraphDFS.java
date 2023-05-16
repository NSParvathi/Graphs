package graphs;

import java.util.ArrayList;
import java.util.Arrays;

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
	I will be solving this using DFS traversal
	
	The Steps are as follows:
	
	1. For DFS traversal, we need a start node and a visited array but in this case, instead of a visited array, we will take a colour array
	   where all the nodes are initialised to -1 indicating they are not coloured yet.
	2. In the DFS function call, make sure to pass the value of the assigned colour, and assign the same in the colour array. 
	   We will try to colour with 0 and 1. We will start with the colour 0 . 
	3. In DFS traversal, we travel in-depth to all its uncoloured neighbours using the adjacency list. For every uncoloured node,
	   initialise it with the opposite colour to that of the current node.
	4. If at any moment, we get an adjacent node from the adjacency list which is already coloured and has the same colour as the current
	 	node, we can say it is not possible to colour it, hence it cannot be bipartite. Thereby return a false indicating the given graph 
	 	is not bipartite; otherwise, keep on returning true.

 */
public class BipartiteGraphDFS {

	public static boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        int[] colors = new int[V];
        Arrays.fill(colors, -1);
       
        
        for(int i=0;i<V;i++) {
        	if(colors[i] == -1) {
        	
        		if(dfs(i,adj,0,colors) == false)
        			return false;;
        	}
        }
		
		return true;
    }
	
	public static boolean dfs(int Node,ArrayList<ArrayList<Integer>> adj,int color,int[] colors) {
		colors[Node] = color;
		
		for(Integer it:adj.get(Node)) {
			if(colors[it] == -1) {
				if(dfs(it,adj,1-color,colors)==false)
					return false;
			}
			else
				if(colors[it] ==color)
					return false;
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
        
        System.out.println(isBipartite(9,adj));
	}
}
