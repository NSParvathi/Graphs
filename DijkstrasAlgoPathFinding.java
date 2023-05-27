package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Sravani
 *
 *         Problem Statement : You are given a weighted undirected graph having n+1 vertices numbered from 0 to n and m edges describing there are
 *         edges between a to b with some weight, find the shortest path between the vertex 1 and the vertex n, and if the path does not exist then
 *         return a list consisting of only -1.
 *         
          Solution: 
         
		  	1. Define a distance array to store the shortest distances for each node, a priority queue for storing the distance-node pairs,
		  	 and a parent array to the parent node from where present node came while traversing.
			2. Set all the parent nodes to themselves to indicate that the traversal has not yet been started.
			3. For every node at the top of the queue, we pop the element out and look out for its adjacent nodes.
			4. If the current reachable distance is better than the previous distance (dist + adjWt < distance[adjNode]), indicated by the distance
			 array, we update the distance and push it into the queue and also update the parent array to the node from where current node came.
			5. A node with a lower distance would be at the top of the priority queue as opposed to a node with a higher distance.
			6. By following above steps repeatedly until our queue becomes empty, we would get the minimum distance from the source node to all
			 other nodes and also our parent array would be updated according to the shortest path.
			7. Run a loop starting from the destination node storing the node’s parent and then moving to the parent again (backtrack) till the
			 parent[node] becomes equal to the node itself.
			8. Now reverse the list in which the path is being stored as the path is in reverse order. Finally, we return the ‘path’ list.
 */
public class DijkstrasAlgoPathFinding {
	// This Pair represent the end node and edge weight
	static class Pair {
		int node;
		int wt;

		Pair(int node, int wt) {
			this.node = node;
			this.wt = wt;
		}
	}
	
	//This pair is used to store in the priority queue while traversing
	static class DisPair implements Comparable {
		Integer distance;
		int node;

		DisPair(int distance, int node) {
			this.distance = distance;
			this.node = node;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			DisPair p = (DisPair) o;
			return this.distance.compareTo(p.distance);
		}
	}

	/*
	 * To print shortest path ehilw calculating shortest distance using queue we
	 * have to store the parent of each node which results shortest distance. Once
	 * we reach the destination node go back to the source node by using parent
	 * array. If you are able to reach the destination then return shortest path
	 * else return List with -1 as a value.
	 */
	public static List<Integer> shortestPath(int n, int m, int edges[][]) {

		// Step1 : create adjacency list from the given matrix
		List<List<Pair>> adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}

		// m reperesents no of edges and for each edge corresponding to each row in the
		// matrix

		for (int i = 0; i < m; i++) {
			adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
		}

		// Now take priority queue to travese and distance array to get the shortest
		// distance and the parent array
		// to track from where it comes so that at last we can find the path.
		// We take priority queue because we need a path with shortest distance

		Queue<DisPair> pq = new PriorityQueue<>();
		int[] distance = new int[n + 1];
		Arrays.fill(distance, (int) 1e9);
		distance[1] = 1;
		int[] parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		pq.add(new DisPair(0, 1));
		// Now iterate through pq and fill the distance array and parent array

		while (!pq.isEmpty()) {
			DisPair p = pq.poll();
			int node = p.node;
			int dist = p.distance;

			for (Pair adjNodes : adj.get(node)) {

				int adjNode = adjNodes.node;
				int adjWt = adjNodes.wt;
				if (dist + adjWt < distance[adjNode]) {
					distance[adjNode] = dist + adjWt;
					parent[adjNode] = node;
					pq.add(new DisPair(dist + adjWt, adjNode));
				}
			}
		}

		// Now get the shrotest path from the parent array

		List<Integer> path = new ArrayList<>();

		// If distance to a node could not be found, return an array containing -1.
		if (distance[n] == 1e9) {
			path.add(-1);
			return path;
		}
		int node = n;
		while (node != parent[node]) {
			path.add(node);
			node = parent[node];

		}
		path.add(node);
		Collections.reverse(path);

		return path;
	}

	public static void main(String args[]) {
		int[][] edges = { { 1, 2, 2 }, { 2, 5, 5 }, { 2, 3, 4 }, { 1, 4, 1 }, { 4, 3, 3 }, { 3, 5, 1 } };
		List<Integer> path = shortestPath(5, 6, edges);
		System.out.println(path);

	}
}
