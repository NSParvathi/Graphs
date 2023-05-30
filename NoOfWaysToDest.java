package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Number of Ways to Arrive at Destination
 * 
 * Problem Statement: 
 * 
 * 	You are in a city that consists of n intersections numbered from 0 to n – 1 with bi-directional roads between some intersections. 
 *  The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between 
 *  any two intersections.

	You are given an integer n and a 2D integer array ‘roads’ where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi
	that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n – 1 in the shortest 
	amount of time.

	Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
	
	Solution:
	
	1. Creat an adjacency list, a priority queue that stores the dist-node pairs in the form {dist, node} and a dist array with each node 
	  initialized with a very large number. 
	2. We need one more array named ‘ways’ which is initialized to ‘0’ for every node when they’re unvisited (so the number of ways is 0).
	3. Push the start node to the queue along with its distance marked as ‘0’ and ways marked as ‘1’ initially because we’ve just started the algorithm.
	3. Pop the element from the front of the queue and look out for its adjacent nodes.
	4. If the current dist value for a number is better than the previous distance indicated by the distance array, we update the distance in the array and
	   push it to the queue. Now, here side by side we also keep the number of ways to the ‘node’ the same as before.
	5. If the current dist value is the same as the previously stored dist value at the same index, increment the number of ways by 1 at that index.
	6. We repeat the above steps until the queue becomes empty or till we reach the destination.
	7. Return the ways[n-1] modulo 10^9+7 when the queue becomes empty.
 */
public class NoOfWaysToDest {

	static class Pair{
		int wt;
		int node;
		Pair(int node,int wt){
			this.wt = wt;
			this.node = node;
		}
		@Override
		public
		String toString() {
			return node+" "+wt;
		}
	}
	 static int countPaths(int n, List<List<Integer>> roads) {
	        // Your code here
		 
		 	//create adjacency list from the given 2D array
		 	//Here raods.get(0) represent one edge from roads.get(0).get(0) to roads.get(0).get(1) with time roads.get(0).get(2).
		 	//Initial configuration - define Priority Queue,distance array and no of ways array
	
		 	List<List<Pair>> adj = new ArrayList<>();
		 	for(int i=0;i<n;i++) {
		 		adj.add(new ArrayList<>());
		 	}
		
		    int m = roads.size();
	        for (int i = 0; i < m; i++) {	        	
	            adj.get(roads.get(i).get(0)).add(new Pair(roads.get(i).get(1), roads.get(i).get(2)));
	            adj.get(roads.get(i).get(1)).add(new Pair(roads.get(i).get(0), roads.get(i).get(2)));
	        }        
		 
		 	Queue<Pair> pq = new PriorityQueue<>((p1,p2)-> p1.wt-p2.wt);
		 	int[] distance = new int[n];
		 	int[] ways = new int[n];
		 	
		 	Arrays.fill(distance,(int)1e9);
		 
		 	distance[0]=0;
		 	Arrays.fill(ways,0);
		 	pq.add(new Pair(0,0));
		 	ways[0] = 1;
		    int mod = (int)(1e9 + 7);
		 	
		 	while(!pq.isEmpty()) {
		 		Pair p = pq.poll();
		 		int node = p.node;
		 		int dist= p.wt;
		 		
		 		for(Pair pair: adj.get(node)) {
		 			int adjNode = pair.node;
		 			int wt = pair.wt;
		 			if(distance[node]+wt < distance[adjNode]) {
		 				distance[adjNode] = distance[node]+wt;
		 				ways[adjNode] = ways[node]+ways[adjNode];
		 				
		 				pq.add(new Pair(adjNode,distance[adjNode]));
		 			} else if(distance[node]+wt == distance[adjNode])
		 				ways[adjNode] = (ways[node]+ways[adjNode])%mod;
		 		}
		 	}
		 	
		 	return ways[n-1]%mod;
		 	
	    }
	 
	 public static void main(String args[]) {
		  int n = 7;
	        List < List < Integer >> roads = new ArrayList <> () {
	            {
	                add(new ArrayList<Integer>(Arrays.asList(0, 6, 7)));
	                add(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
	                add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
	                add(new ArrayList<Integer>(Arrays.asList(1, 3, 3)));
	                add(new ArrayList<Integer>(Arrays.asList(6, 3, 3)));
	                add(new ArrayList<Integer>(Arrays.asList(3, 5, 1)));
	                add(new ArrayList<Integer>(Arrays.asList(6, 5, 1)));
	                add(new ArrayList<Integer>(Arrays.asList(2, 5, 1)));
	                add(new ArrayList<Integer>(Arrays.asList(0, 4, 5)));
	                add(new ArrayList<Integer>(Arrays.asList(4, 6, 2)));

	            }
	        };
	        System.out.println(countPaths(n,roads));
	 }
	
}
