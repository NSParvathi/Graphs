package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sravani 
 * 		   Problem Statement: Given an undirected graph, return a vector
 *         of all nodes by traversing the graph using depth-first search (DFS).
 * 
 *         Solution :
 * 
 *         DFS is a traversal technique which involves the idea of recursion and
 *         backtracking. DFS goes in-depth, i.e., traverses all nodes by going
 *         ahead, and when there are no further nodes to traverse in the current
 *         path, then it backtracks on the same path and traverses other
 *         unvisited nodes.
 *
 *         1. In DFS, we start with a node ‘v’, mark it as visited and store it
 *         in the solution vector. It is unexplored as its adjacent nodes are
 *         not visited. 2. We run through all the adjacent nodes, and call the
 *         recursive dfs function to explore the node ‘v’ which has not been
 *         visited previously. This leads to the exploration of another node ‘u’
 *         which is its adjacent node and is not visited. 3. The adjacency list
 *         stores the list of neighbours for any node. Pick the neighbour list
 *         of node ‘v’ and run a for loop on the list of neighbours (say nodes
 *         ‘u’ and ‘w’ are in the list). We go in-depth with each node. When
 *         node ‘u’ is explored completely then it backtracks and explores node
 *         ‘w’. 4. This traversal terminates when all the nodes are completely
 *         explored.
 *         
 *         Time Complexity: For an undirected graph, O(N) + O(2E), For a directed graph, O(N) + O(E)
 *         Space Complexity: O(3N) ~ O(N), Space for dfs stack space, visited array and an adjacency list.
 */
public class DFS {

	public static List<Integer> dfsTraversal(int V, List<List<Integer>> adj) {
		List<Integer> ans = new ArrayList<>();
		boolean[] vis = new boolean[V];
		dfs(adj, ans, vis, 0);
		return ans;
	}

	public static void dfs(List<List<Integer>> adj, List<Integer> ans, boolean[] vis, int Node) {
		vis[Node] = true;
		ans.add(Node);
		for (Integer it : adj.get(Node)) {
			if (vis[it] == false)
				dfs(adj, ans, vis, it);
		}
	}

	public static void main(String args[]) {

		int V = 5;
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}

		adj.get(0).add(2);
		adj.get(2).add(0);
		adj.get(0).add(1);
		adj.get(1).add(0);
		adj.get(0).add(3);
		adj.get(3).add(0);
		adj.get(2).add(4);
		adj.get(4).add(2);
		
		List<Integer> ans = dfsTraversal(V,adj);
		System.out.println(ans);
		

	}
}
