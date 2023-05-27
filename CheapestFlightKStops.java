package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem Statemenet:
 * 
 * There are n cities and m edges connected by some number of flights. You are given an array of flights where
 * flights[i] = [ fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost price.
 * You have also given three integers src, dst, and k, and return the cheapest price from src to dst with at most k stops. 
 * If there is no such route, return -1.
 * 
 * Solution:
 * 
 * Below are the steps to be followed:
 * 1. creat an adjacency list, a queue that stores the distance-node and stops pairs in the form {stops,{node,dist}} and
 * 	  a cost array with each node initialized with a very large number and the source node marked as ‘0’.
   2. We push the source cell to the queue along with its cost which is also 0 and the stops are marked as ‘0’ initially 
   	  because we’ve just started.
   3. Pop the element at the front of the queue and look out for its adjacent nodes. 
   4. If the current cost value of a node is better than the previous cost indicated by the cost array and the number of stops
      until now is less than K, we update the cost in the array and push it to the queue. Also, increase the stop count by 1.
   5. We repeat the above two steps until the queue becomes empty. We do not stop the algorithm from just reaching the
      destination node as it may give incorrect results.
   6. Return the calculated cost after we reach the required number of stops.
      If the queue becomes empty and we don’t encounter the destination node, return ‘-1’ indicating there’s no path from
      source to destination.
 */
public class CheapestFlightKStops {

	static class Tuple{
		int stops;
		int node;
		int cost;
		Tuple(int stops,int node,int cost){
			this.stops = stops;
			this.node= node;
			this.cost = cost;
		}
	}
	static class Pair{
	    int node;
	    int cost;
	    public Pair(int first,int second){
	        this.node = first;
	        this.cost = second;
	    }
	}
	public static int cheapestFlight(int n,int flights[][],int src,int dst,int k) {
        // Code here
		//Intial configuration
		//cost array and queue which containd Tuple(Steps,node,distance)
		// General queue is enough not priority queue as we go steps wise always 
		
		int[] cost = new int[n];
		Arrays.fill(cost,(int)1e9);
		
		Queue<Tuple> q = new LinkedList<>();
		
		//Create adjacency List from the given 2D array
		
		List<List<Pair>> adj = new ArrayList<>();
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i=0;i<flights.length;i++) {
			adj.get(flights[i][0]).add(new Pair(flights[i][1],flights[i][2]));
		}
		
		cost[src] =0;
		q.add(new Tuple(0,src,0));
		
		while(!q.isEmpty()) {
			Tuple t = q.poll();
			if(t.stops==k+1  && cost[dst] != 1e9)
				return cost[dst];
			for(Pair p:adj.get(t.node)) {
				if(t.cost+p.cost < cost[p.node] ) {
					cost[p.node] = t.cost+p.cost;
					q.add(new Tuple(t.stops+1,p.node,t.cost+p.cost));
				}
			}
		}
		return -1;
		
    }
	public static void main(String args[]) {
	  int n = 4, src = 0, dst = 3, K = 1;
      int[][] flights={{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
      
      int cost = cheapestFlight(4,flights,src,dst,K);
      System.out.println(cost);
	}
}
