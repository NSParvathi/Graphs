package graphs;
/**
 * Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where
 * adj[i] is a list of lists containing two integers where the first integer of each list j
 * denotes there is an edge between i and j, second integers corresponds to the weight of that 
 * edge. You are given the source vertex S and You have to Find the shortest distance of all the 
 * vertex from the source vertex S. You have to return a list of integers denoting the shortest 
 * distance between each node and Source vertex S.
 * 
 * Approach:

	We’ll be using Set in this approach for finding the shortest distances from the source node to every other node 
	through Dijkstra’s Algorithm.

	Below are the Steps to be followed:
	1. We would be using a set and a distance array of size V (where ‘V’ are the number of nodes in the graph) 
	   initialized with infinity and initialize the distance to source node as 0.
	2. We push the source node to the set along with its distance which is also 0.
	3. Now, we start erasing the elements from the set and look out for their adjacent nodes one by one.
	   If the current reachable distance is better than the previous distance indicated by the distance array,
	   we update the distance and insert it in the set.
	4. A node with a lower distance would be first erased from the set as opposed to a node with a higher distance.
	5. By following step 3, until our set becomes empty, we would get the minimum distance from the source node to all
	   other nodes. We can then return the distance array. 
	The only difference between using a Priority Queue and a Set is that in a set we can check if there exists a pair
	with the same node but a greater distance than the current inserted node as there will be no point in keeping that 
	node into the set if we come across a much better value than that. So, we simply delete the element with a greater
	distance value for the same node.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import graphs.DijkstrasAlgoUsingPriorityQueue.Pair;

/**
 *  
 */
public class DijkstrasAlgoUsingSet {
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
			if(this.distance == p.distance) {
				return this.node.compareTo(p.node);
			}
			return this.distance.compareTo(p.distance);
		}
	}
	 static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
	    {
		 	Set<Pair> set = new TreeSet<>();
		 	int[] distance = new int[V];
		 	
		 	Arrays.fill(distance,(int) 1e9);
		 	
		 	distance[S] = 0;
		 	set.add(new Pair(0,S));
		 	while(!set.isEmpty()) {
		 		Pair p = set.iterator().next();
		 		set.remove(p);
		 		ArrayList<ArrayList<Integer>> adjNode = adj.get(p.node);
		 		System.out.println("Node Returned : "+p.node);
		 		System.out.println(adjNode);
		 		for(ArrayList<Integer> edgeInfo : adjNode) {
		 			int dis = edgeInfo.get(1);
		 			int node = edgeInfo.get(0);
		 			if(dis+p.distance < distance[node]) {
		 				set.removeIf(p1-> p1.distance == distance[node] && p1.node == node);
		 				//Usng predicate rdeleting node with distance larger then the curr distace
		 				set.add(new Pair(dis+p.distance,node));
		 				distance[node] = dis+p.distance;
		 			}
		 		}
		 	}
		 	
		 	return distance;
	    }
	 
	 public static void main(String args[]) {
			
			ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
			int V = 4, E = 5;
			for(int i=0;i<V;i++){
				adj.add(new ArrayList<>());
			}
			
			adj.get(0).add(new ArrayList<>());
			adj.get(0).add(new ArrayList<>());
			adj.get(0).add(new ArrayList<>());
			adj.get(0).get(0).add(1);
			adj.get(0).get(0).add(9);
			
			adj.get(0).get(1).add(2);
			adj.get(0).get(1).add(1);
			
			adj.get(0).get(2).add(3);
			adj.get(0).get(2).add(1);
			
			adj.get(1).add(new ArrayList<>());
			adj.get(1).add(new ArrayList<>());
			
			adj.get(1).get(0).add(0);
			adj.get(1).get(0).add(9);
			adj.get(1).get(1).add(3);
			adj.get(1).get(1).add(3);
			
			adj.get(2).add(new ArrayList<>());
			adj.get(2).add(new ArrayList<>());
			
			adj.get(2).get(0).add(0);
			adj.get(2).get(0).add(1);
			adj.get(2).get(1).add(3);
			adj.get(2).get(1).add(2);
			
			adj.get(3).add(new ArrayList<>());
			adj.get(3).add(new ArrayList<>());
			adj.get(3).add(new ArrayList<>());
			
			adj.get(3).get(0).add(0);
			adj.get(3).get(0).add(1);
			adj.get(3).get(1).add(2);
			adj.get(3).get(1).add(2);
			adj.get(3).get(2).add(1);
			adj.get(3).get(2).add(3);
		
			
			
			
			
			
			//{{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
			System.out.println(Arrays.toString(dijkstra(V,adj,0)));
		}
}
