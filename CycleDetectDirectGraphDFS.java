package graphs;
/*
 *Problem Statement:
 * 	Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.
 *
 * Intution:
 * 
 *   In case of undirected graph we said a cycle has foremd in the graph if we visit the node twice using visited array.
 *   But in directed graph it is entirerly different. Even node visited two times it doesn't mean that cycle has formed because
 *   we can reach the same node from different path.
 *   
 *   In a Directed Cyclic Graph, during traversal, if we end up at a node, which we have visited previously in the path, that means
 *   we came around a circle and ended up at this node, which determines that it has a cycle. Previously, we have learned a similar 
 *   technique to detect cycles in an Undirected Graph (using DFS). In that method, the algorithm returns true, if it finds an adjacent node
 *   that is previously visited and not a parent of the current node. But the same algorithm will not work in this case.
 *   
 *   While using DFS, the algorithm is designed with two visited arrays(vis and pathVis) in such a way that after making a DFS call
 *   the node is marked in both arrays, and while backtracking the node is unmarked from the path-visited (pathVis) array. 
 *   And when we find a node marked in both arrays, we conclude that there exists a cycle. A point to note here is that we involve 
 *   backtracking in the logic of DFS while finding the cycle, which is tough to replicate if we are trying to solve it iteratively. 
 *   Using BFS we cannot detect cycle easily as it is recursive approach.
 *   
 *   Approach:
 *   
 *  1. We will traverse the graph component-wise using the DFS technique. 
 *  2. Make sure to carry two visited arrays in the DFS call. One is a visited array(vis) and the other is a path-visited(pathVis) array.
 *     The visited array keeps a track of visited nodes, and the path-visited keeps a track of visited nodes in the current traversal only. 
 *  3. While making a DFS call, at first we will mark the node as visited in both the arrays and then will traverse through its adjacent nodes. 
 *     Now, there may be either of the three cases:
 * 	   Case 1: If the adjacent node is not visited, we will make a new DFS call recursively with that particular node.
 * 	   Case 2: If the adjacent node is visited and also on the same path(i.e marked visited in the pathVis array), we will return true,
 *             because it means it has a cycle, thereby the pathVis was true. Returning true will mean the end of the function call, as once we have got a cycle, 
 *             there is no need to check for further adjacent nodes. 
 * 	   Case 3: If the adjacent node is visited but not on the same path(i.e not marked in the pathVis array), we will continue to the next adjacent node, 
 *             as it would have been marked as visited in some other path, and not on the current one.
 *  4. Finally, if there are no further nodes to visit, we will unmark the current node in the pathVis array and just return false. 
 *  Then we will backtrack to the previous node with the returned value. The point to remember is, while we enter we mark both the pathVis and vis as true, 
 *  but at the end of traversal to all adjacent nodes, we just make sure we unmark the pathVis and still keep the vis marked as true,
 *  as it will avoid future extra traversal calls. 
 */
import java.util.ArrayList;
import java.util.Arrays;

public class CycleDetectDirectGraphDFS {

	public static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
		int[] vis = new int[V];
		Arrays.fill(vis, 0); // if vis[i] == 0 means not visisted and if vis[i] == means visited
		
		int[] pathVis = new int[V];
		Arrays.fill(pathVis,0);
		
		for(int i=0;i<V;i++) {
			if(vis[i] == 0) {
				if(dfs(i,adj,vis,pathVis)== true)
					return true;;
			}
		}
 		
		return false;
    }
	
	public static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj,int[] vis, int[] pathVis) {
		
		vis[node] = 1;
		pathVis[node] = 1;
		
		for(Integer it:adj.get(node)) {
			if(vis[it] ==0 ) {
				if(dfs(it,adj,vis,pathVis))
					return true;
			}else {
				if(pathVis[it] == 1) {
					System.out.println(node);
					return true;
				}
			}	
		}
		pathVis[node] =0;
		return false;
	}
	
	public static void main(String args[]) {
		int V = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
       adj.get(6).add(5);
       adj.get(5).add(3);
       adj.get(3).add(1);
       adj.get(1).add(2);
       adj.get(2).add(4);
       adj.get(4).add(0);
       
        System.out.println(isCyclic(V,adj));
	}
}
