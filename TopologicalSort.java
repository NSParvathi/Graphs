package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any
 * Topological Sorting of that Graph.
 * 
 * Note: In topological sorting, node u will always appear before node v if
 * there is a directed edge from node u towards node v(u -> v).
 * 
 * We will be solving it using the DFS traversal technique. DFS goes in-depth,
 * i.e., traverses all nodes by going ahead, and when there are no further nodes
 * to traverse in the current path, then it backtracks on the same path and
 * traverses other unvisited nodes.
 * 
 * The algorithm steps are as follows:
 * 
 * 1. We must traverse all components of the graph. 
 * 2. Make sure to carry a visited array(all elements are initialized to 0) and
 * a stack data structure,where we are going to store the nodes after completing
 * the DFS call. In the DFS call, first, the current node is marked as visited.
 * Then DFS call is made for all its adjacent nodes. After visiting all its adjacent 
 * nodes, DFS will backtrack to the previous node and meanwhile, the current node 
 * is pushed into the stack. Finally, we will get the stack containing one of the 
 * topological sortings of the graph. 
 * 3. The reason behind using the Stack is that stack follows LIFO order. 
 * We are using DFS to traverse and get Topological sorting.
 * In DFS we go into the depth until no more nodes to travel and then traverse
 * back. While back tracting we are adding to the stack so that nodes which
 * needs to be completed first will be at the top of the stack and later
 * completion nodes will be at the bottom. If you pop the nodes from the Stack
 * we will get Topological sorting as Stack returns recently added nodes first.
 * 
 */
public class TopologicalSort {

	static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] vis = new boolean[V];
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < V; i++) {
			if (vis[i] == false) {
				dfs(i, adj, vis, stack);
			}
		}
		int[] ans = new int[V];
		int i=0;
		while(!stack.isEmpty()) {
			ans[i++] = stack.pop();
		}
		return ans;
		// add your code here

	}

	public static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, Deque<Integer> stack) {
		vis[node] = true;
		for (int it : adj.get(node)) {
			if (vis[it] == false) {
				dfs(it, adj, vis, stack);
			}
		}
		stack.push(node);
	}
	
	public static void main(String args[]) {
		int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);
        
        int[] ans = topoSort(V,adj);
        System.out.println(Arrays.toString(ans));
        

	}
}
