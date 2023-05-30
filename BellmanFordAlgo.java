package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Distance from the Source (Bellman-Ford Algorithm)
 * 
 * Problem Statement:
 * Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S.
   Note: If the Graph contains a negative cycle then return an array consisting of only -1.

    Solution:
    
    Below are the steps to be followed
        1. Create a distance array and intilize the source node to 0 and remaining all to max value which is 1e8
	2. Then we will run a loop for V-1 times.
	3. Inside that loop, relax the every given edge.
	4. For example, one of the given edge information is like (u, v, wt), 
	   where u = starting node of the edge, v = ending node, and wt = edge weight.
	   For all edges like this we will be checking if node u is reachable and if the distance to reach v through u is less than the 
	   distance to v found until now(i.e. dist[u] and dist[u]+ wt < dist[v]).
	5. Repeat the 4th step for N-1 times, we will apply the same step one more time to check if the negative cycle exists. 
	   If we found further relaxation is possible, we will conclude the graph has a negative cycle and from this step,
	   we will return a distance array of -1(i.e. minimization of distances is not possible).
        6. Otherwise, we will return the distance array which contains all the minimized distances.
 */
public class BellmanFordAlgo {

	class Pair{
		int node;
		int wt;
		Pair(int node,int wt){
			this.node = node;
			this.wt = wt;
			
		}
	}
	static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
		
		//Create an adjacency list from the given 2D matrix
		
		List<List<Pair>> adj = new ArrayList<>();
			
		int noOfEdges = edges.size();
		int[] distance = new int[V];
		Arrays.fill(distance,(int)(1e8));
		distance[S] = 0;
		
		//Start relaxing n-1 time edge wise not node wise
		for(int i=0;i<V-1;i++) {
			
			for(int j=0; j <noOfEdges;j++) {
				int u= edges.get(j).get(0);
				int v = edges.get(j).get(1);
				int wt = edges.get(j).get(2);
				
				if(distance[u] != 1e8 && distance[u]+wt < distance[v]) {
					distance[v] = distance[u]+wt;
				}
			}
		}
		
		//One more relaxing to find negative cycle
	
			for(int j=0; j <noOfEdges;j++) {
				int u= edges.get(j).get(0);
				int v = edges.get(j).get(1);
				int wt = edges.get(j).get(2);
				
				if(distance[u] != 1e8 && distance[u]+wt < distance[v]) {
					int[] temp = new int[1];
					temp[0] = -1;
					return temp;
				}
			}
		
		
		
		return distance;
        
    }
	
	public static void main(String args[]) {
		  int V = 6;
	        int S = 0;
	        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
	            {
	                add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
	                add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
	                add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
	                add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
	                add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
	                add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
	                add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
	            }
	        };
	        int[] dist =bellman_ford(V, edges, S);
	        System.out.println(Arrays.toString(dist));
	}
}
