package graphs;
/*
 * Floyd Warshall Algorithm
 * 
 * Problem Statement:
 * 
 *  The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph.
 *  The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge from i to j. 
 *  If Matrix[i][j]=-1, it means there is no edge from i to j.
 */
public class FloydWarshallAlgo {

	public static void shortest_distance(int[][] matrix) {
		// Code here
		// We have to traverse the entire graph via each node and then once all nodes are
		// completed that will be the your required matrix
		// which represents the shortest distance between pair of nodes.
		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					matrix[i][j] = 0;
				else if (matrix[i][j] == -1)
					matrix[i][j] = (int) 1e9;
			}
		}
		for (int v = 0; v < n; v++) {

			// Via v node traverse all nodes
			for (int i = 0; i < n; i++) {

				for (int j = 0; j < n; j++) {
					/*
					 * if(i!=j && matrix[i][v] != 1e9 && matrix[v][j] != 1e9 &&
					 * matrix[i][v]+matrix[v][j]<matrix[i][j]) matrix[i][j] =
					 * matrix[i][v]+matrix[v][j];
					 */
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][v] + matrix[v][j]);
				}
			}

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1e9)
					matrix[i][j] = -1;
			}
		}

	}

	public static void main(String args[]) {
		int V = 4;
		int[][] matrix = new int[V][V];

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = -1;
			}
		}

		matrix[0][1] = 2;
		matrix[1][0] = 1;
		matrix[1][2] = 3;
		matrix[3][0] = 3;
		matrix[3][1] = 5;
		matrix[3][2] = 4;
		shortest_distance(matrix);
	}
}
