package graphs;
/*
 * City With the Smallest Number of Neighbors at a Threshold Distance
 * 
 * Problem Statement:
 * 
 *  There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi,weighti]  represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distance Threshold. You need to find out a city with the smallest number of cities that are reachable through some path and whose distance is at most Threshold Distance, If there are multiple such cities, our answer will be the city with the greatest number.
 *  
 *  Solution
 *  1. Iterate over all the edges, and set the value of dist[fromi][toi] and dist[toi][fromi] to weighti as the edges are bidirectional.
 *  
 *	2. After setting the adjacency matrix accordingly, we will run a loop from 0 to V-1(V = no. of vertices).
 *  3. In the kth iteration, this loop will help us to check the path via node k for every possible pair of nodes.
 *  4. Inside the loop, there will be two nested loops for generating every possible pair of nodes. Among these two loops, 
 *  5. Inside these nested loops, we will apply the following formula to calculate the shortest distance between the pair of nodes:
 *     dist[i][j] =min(dist[i][j], dist[i ][k]+dist[k][j]), where i = source node, j = destination node, and k = the node via 
 *     which we are reaching from i to j.
 *	6. The distance matrix will store all the shortest paths for each node. For example, dist[i][j] will store the shortest path from
 *	   node i to node j.
 *	7. After that, we will count the nodes(cnt) with a distance lesser or equal to distanceThreshold and check if it is lesser than
 *	 the current value of minReachable.
 *	8. If it is lesser, we will update minReachable with the count of nodes and node with the value of the current city.
 *	   Finally, we will return node as our answer.
 */
public class CityWithSmallestDist {
	static int findCity(int n, int m, int[][] edges, int distanceThreshold) {
		// code here

		// get the matrix which represent the shortest distance between two nodes.
		// Give edges matrix represnts m edges between n nodes
		// Now construct adjacency matrix by using given matrix. For that crate a nxn
		// matrix.

		int[][] dist = new int[n][n];
		// Traverse the each edge and desgin the adjacency matrix

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					dist[i][j] = 0;
				else
					dist[i][j] = (int) 1e9;
			}
		}
		for (int i = 0; i < m; i++) {
			dist[edges[i][0]][edges[i][1]] = edges[i][2];
			dist[edges[i][1]][edges[i][0]] = edges[i][2];

		}

		// Now, traverse via each node and get the required matrix

		for (int v = 0; v < n; v++) {

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][v] + dist[v][j]);
				}
		}
		
		//Now traverse through the adjacency matrix row wise and get the required node wich has minumun no of reachable nodes
		int node =-1;
		int minReachable = n+1;;
		for(int i=0;i<n;i++) {
			int count =0;
			for(int j=0;j<n;j++) {
				if(dist[i][j] <= distanceThreshold) {
					count++;
				}
			}
			if(count<=minReachable) {
				minReachable = count;
				node = i;
			}
		}
		return node;

	}
	
	public static void main(String args[]) {
		   int n = 4;
	        int m = 4;
	        int[][] edges =  {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
	        int distanceThreshold = 4;
	        
	        int node = findCity(n,m,edges,distanceThreshold);
	        System.out.println(node);
	        
	}
}
