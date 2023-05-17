package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * Problem Statement:
 * 
 * A directed graph of V vertices and E edges is given in the form of an adjacency list adj. Each node of the graph is labeled with a
 * distinct integer in the range 0 to V – 1. A node is a terminal node if there are no outgoing edges. A node is a safe node if
 * every possible path starting from that node leads to a terminal node. You have to return an array containing all the safe nodes of 
 * the graph. The answer should be sorted in ascending order.
 * 
 * Solution :
 * 
 * I am going to solve this problem using DFS traversal and same approach as detecting cycle in Directed graph using DFS traversal.
 * 
 * Below are the stepts to be followed:
 * 1. Take a visisted array. Here I am going to use single visited array to indicate visited or both visited and path visited.
 *    Suppose array is vis[] then vist[i] ==0 if node has not visited yet.
 *    	vis[i] == 1 , if the node is visted but not in this path
 *    	vis[i] == 2 , if the node is visited in the current path.
 * 2. Take an stack named safeNodes.
 * 3. Start from the first node traverse the adjacent nodes by using adjacent list and mark vis[node] = 2. If you reach the end of the path
 * 	while coming back mark vis[node] =1 to indicate its only visited not path visited and for a particulare node if all the paths completed 
 * and no where we came to node in the current path means that node is safe node and add it to the safe node list.
 * 4. Here the reason behind using the Stack is that we need nodes in sorted order. I am adding nodes to stach while backtracking. 
 * So, we need Data structure based on LIFO order. 
 * @author Sravani
 *
 */
public class SafeStates {
	
	
	static List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {

		int[] vis = new int[V];
		Arrays.fill(vis, 0);
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<V;i++) {
			if(vis[i] == 0) {
				dfs(i,adj,vis,list);
			}
		}
		
		 Collections.sort(list);
		
		return list;
    }
	
	public static boolean dfs(int node,List<List<Integer>> adj,int[] vis,List<Integer> list) {
		
		vis[node] = 2;
		for(Integer it:adj.get(node)) {
			if(vis[it] == 0) {
				if(dfs(it,adj,vis,list)==false)
					return false;;
			}else if(vis[it] == 2) {
				return false;
			}
		}
		vis[node] = 1;
		list.add(node);
		return true;
	}
	
	public static void main(String args[]) {
		int V = 12;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        adj.get(3).add(5);
        adj.get(4).add(6);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(8).add(1);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);
        adj.get(11).add(9);
        
        List<Integer> ans = eventualSafeNodes(V,adj);
        System.out.println(ans);
	}
}
