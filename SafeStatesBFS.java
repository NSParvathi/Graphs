package graphs;
/**
 * Problem statement :
 * A directed graph of V vertices and E edges is given in the form of an adjacency list adj. Each node of the graph is labelled
 * with a distinct integer in the range 0 to V - 1.

   A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads
   to a terminal node.

   You have to return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 * 
 * Approach: 
	The node with outdegree 0 is considered to be a terminal node but the topological sort algorithm deals with the indegrees of the nodes.
	So, to use the topological sort algorithm, we will reverse every edge of the graph. Now, the nodes with indegree 0 become the terminal 
	nodes. After this step, we will just follow the topological sort algorithm as it is.
	
 * Below are the steps to be followed
	1. First, we will reverse all the edges of the graph so that we can apply the topological sort algorithm afterward.
	
    2. Then, we will calculate the indegree of each node and store it in the indegree array. We can iterate through the given adj list, 
       and simply for every node u->v, we can increase the indegree of v by 1 in the indegree array. 
	3. Then, we will push the node(s) with indegree 0 into the queue.
	4. Then, we will pop a node from the queue including the node in our safeNodes array, and for all its adjacent nodes, we will decrease
	   the indegree of that node by one. 
	5. After that, if for any node the indegree becomes 0, we will push that node again into the queue.
	6. We will repeat steps 3 and 4 until the queue is completely empty. Finally, completing the BFS we will get the linear ordering of the
	   nodes in the safeNodes array.
	   Finally, the safeNodes array will only consist of the nodes that are neither a part of any cycle nor connected to any cycle. 
	   Then we will sort the final safeNodes array as the question requires the answer in sorted order.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SafeStatesBFS {

	public static List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {

		// Reverse the edges of the graph. Generally terminal nodes doesn't have any out going edges, only incoming edges. 
		//But toposort works on indegree. For particular node if in degree is zero means we permorm that task first.
		//Like same way if you reverse the edges, the terminal nodes in degree becomes zero means its safe node. 
		//Reamining process is same as Topo sort once you reverse all the edges of the graph.
		
		//Step1 : Reverse all the edges of the graph
		
		List<List<Integer>> revAdj = new ArrayList<>();
		for(int i =0;i<V;i++) {
			revAdj.add(new ArrayList<>());
			
		}
		
		for(int i=0;i<V;i++) {
			for(int it:adj.get(i)) {
				revAdj.get(it).add(i);
			}
		}
		
		//Step 2 : Calculate the in degree of each node
		int[] inDegree = new int[V];
		for(int i=0;i<V;i++) {
			for(int it:revAdj.get(i)) {
				inDegree[it]++;
			}
		}
		
		//Step 3 : Find the node with in degree zero and add it to the Queue
		Queue<Integer> q = new LinkedList<>();
		for(int i=0;i<V;i++) {
			if(inDegree[i] == 0)
				q.add(i);
		}
		List<Integer> safeNodes = new ArrayList<>();
		
		//step 4: Untill queue is not empty get the nodes from queue, add it to safe nodes list and decrese the adjacent nodes
		// indegree by 1, while decreasing if any node indegree becomes zero add it to the queue.
		
		while(!q.isEmpty()) {
			int node = q.poll();
			safeNodes.add(node);
			for(int it:revAdj.get(node)) {
				inDegree[it]--;
				if(inDegree[it] == 0)
					q.add(it);
			}
		}
		
		//Now sort the list as mentioned in the question: the list should be in ascending order.
		Collections.sort(safeNodes);
		return safeNodes;
    }
	
	
}
