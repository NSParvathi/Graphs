package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Sravani
 * 
 * Problem Statement: You are given an Undirected Graph having unit  weight, Find the shortest path from src to all the vertex and if it
 * is unreachable to reach any vertex, then return -1 for that vertex.
 * 
 * Following are the stpes to calculate shortest path in an undirected graph
  	1. We convert the graph into an adjacency list which displays the adjacent nodes for each index represented by a node.
	2. Create a dist array of size N initialized with a very large number which can never be the answer to indicate that initially, 
	all the nodes are untraversed.
    3. Perform the standard BFS traversal. 
	4. In every iteration, pick up the front() node, and and then traverse for its adjacent nodes. 
	 For every adjacent node,first check that is not the parent of the cuurent node and  we will relax the distance to the adjacent node 
	 if (dist[p.node] + 1 < dist[adjNode]).Here dist[p.node] means the distance taken to reach the current node, and ‘1’ is the edge weight between the node and the adjNode.
	 We will relax the edges if this distance is shorter than the previously taken distance.
	 Every time a distance is updated for the adjacent node, we push that adjNode and the cuurent node which is the parent into Queue.
	   
	One improvement here is that there is no need to maintain parent node information in the queue because whenever we try to traverse
	from the curret node to the adjacent nodes If it is a parent node, then the new distance will always be greater than the existing 
	distance, so it won't proceed further in that path.  
 * 
 */
public class ShortestPathUndirectedGraph {

	static class Pair {
		int node;
		int parent;

		Pair(int node, int parent) {
			this.node = node;
			this.parent = parent;
		}
	}

	public static int[] shortestPath(int[][] edges, int n, int m, int src) {
		// Code here

		// construct adjacnecy list from the given matrix
		// give n nodes and m edges.
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int start = edges[i][0];
			int end = edges[i][1];
			adj.get(start).add(end);
			adj.get(end).add(start);
		}

		int[] distance = new int[n];
		Arrays.fill(distance, (int) 1e9);
		// Now start from src and travel to the adjacent nodes and update distance array
		// with the shortest path from the src
		// Here i am going to use BFS so using Queue data structure
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(src, -1));
		distance[src] = 0;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int adjNode : adj.get(p.node)) {
				if (adjNode != p.parent ) {
					
					if (distance[p.node] + 1 < distance[adjNode]) {
						distance[adjNode] = distance[p.node] + 1;
						q.add(new Pair(adjNode, p.node));
						//Here i am adding adjcent node to the queue only if the current path distance is smaller than already exisiting 
						//distance previously calculated.Otherwise previous  path is the shortest and no need to traverse in that path
						//again.
					}
				}

			}

		}
		
		for(int i=0;i<n;i++) {
			if(distance[i] == 1e9) {
				distance[i]=-1;
			}
		}
		return distance;

	}
	
	public static void main(String args[]) {
		int n = 9, m= 10;
		int[][] edges= {{0,1},{0,3},{3,4},{4 ,5}
				,{5, 6},{1,2},{2,6},{6,7},{7,8},{6,8}}; 
		int src=0;
		int[] dis = shortestPath(edges,n,m,src);
		System.out.println(Arrays.toString(dis));
		
	}

}
