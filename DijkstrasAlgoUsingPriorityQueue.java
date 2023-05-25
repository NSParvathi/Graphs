package graphs;
/**
 * Problem Statement:
 * Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists
 * containing two integers where the first integer of each list j denotes there is an edge between i and j, second integers
 * corresponds to the weight of that edge. You are given the source vertex S and You have to Find the shortest distance of all
 * the vertex from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and the Source vertex S.
 * 
 * Solution:
 * I am using Priority Queue in this approach for finding the shortest distances from the source node to every other node 
 * through Dijkstra’s Algorithm.
 * 
 * Below are the steps to be followed
 * 
 * 1. I am using a min-heap and a distance array of size V initialized with infinity and initialize the distance of source
 *  node as 0.
 * 2. We push the source node to the queue along with its distance which is also 0.
 * 3. For every node at the top of the queue, we pop the element out and look out for its adjacent nodes.
 *    If the current distance is better than the previous distance indicated by the distance array, we update the distance 
 *    and push it into the queue.
 * 4. A node with a lower distance would be at the top of the priority queue as opposed to a node with a higher distance
 *    because we are using a min-heap. 
 * 5. The Pair class, which indicates pairs of distance and node, should implement a comparable interface as it is required
 *     to define the order of the pairs for the Priority Queue.
 *     
 *     Time Complexity: O( E log(V) ), Where E = Number of edges and V = Number of Nodes.

	   Space Complexity: O( |E| + |V| ), Where E = Number of edges and V = Number of Nodes.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstrasAlgoUsingPriorityQueue {
	static class Pair implements Comparable{
		Integer distance;
		Integer node;
		Pair(int distance,int node){
			this.distance = distance;
			this.node = node;
		}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Pair p = (Pair) o;
			return this.distance.compareTo(p.distance);
		}
	}
	static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
		
		//Declare Priority Queue for BFS traversal
		Queue<Pair> pq = new PriorityQueue<>();
		int[] distance = new int[V];
		Arrays.fill(distance,(int)1e9);
		pq.add(new Pair(0,S));
		distance[S] =0;
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int node = p.node;
			int dis = p.distance;
			ArrayList<ArrayList<Integer>> adjNodes = adj.get(node);
			for(ArrayList<Integer> nodes:adjNodes) {
				int adjNode = nodes.get(0);
				int disAdjNode = nodes.get(1);
				if(dis+disAdjNode < distance[adjNode]) {
					distance[adjNode] = dis+disAdjNode;
					pq.add(new Pair(dis+disAdjNode,adjNode));
				}
			}
		}
		for(int i=0;i<distance.length;i++) {
			if(distance[i]== 1e9) {
				distance[i] =-1;
			}
		}
		
		return distance;
    }
	
public static void main(String args[]) {
		
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
		int V = 3, E = 3;
		for(int i=0;i<V;i++){
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(new ArrayList());
		adj.get(0).add(new ArrayList<>());
		adj.get(0).get(0).add(1);
		adj.get(0).get(0).add(1);
		
		adj.get(0).get(1).add(2);
		adj.get(0).get(1).add(6);
		
		adj.get(1).add(new ArrayList());
		adj.get(1).add(new ArrayList<>());
		adj.get(1).get(0).add(2);
		adj.get(1).get(0).add(3);
		
		adj.get(1).get(1).add(0);
		adj.get(1).get(1).add(1);
		
		adj.get(2).add(new ArrayList());
		adj.get(2).add(new ArrayList<>());
		adj.get(2).get(0).add(1);
		adj.get(2).get(0).add(3);
		
		adj.get(2).get(1).add(0);
		adj.get(2).get(1).add(6);
		
		//{{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
		System.out.println(Arrays.toString(dijkstra(V,adj,2)));
	}
}
