package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Topological Sorting using BFS
 * Problem Statement :  Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.

   Note: In topological sorting, node u will always appear before node v if there is a directed edge from node u towards node v(u -> v)
 * 
 * Topological sorting only exists in Directed Acyclic Graph (DAG). If the nodes of a graph are connected through directed edges and the graph does not contain a cycle, it is called a directed acyclic graph(DAG). 
 * Indegree Array: Initially all elements are set to 0. Then, We will count the incoming edges for a node and store it in this array. For example, if indegree of node 3 is 2, indegree[3] = 2.

   Queue: As we will use BFS, a queue is required. Initially, the node with indegree 0 will be pushed into the queue.

   Answer array: Initially empty and is used to store the linear ordering.

   The algorithm steps are as follows:

    1. First, we will calculate the indegree of each node and store it in the indegree array. We can iterate through the given adj list, 
       and simply for every node u->v, we can increase the indegree of v by 1 in the indegree array. 
    2. Initially, there will be always at least a single node whose indegree is 0. So, we will push the node(s) with indegree 0 into the
       queue.
    3. Then, we will pop a node from the queue including the node in our answer array, and for all its adjacent nodes, we will decrease the
       indegree of that node by one. For example, if node u that has been popped out from the queue has an edge towards node v(u->v), we
       will decrease indegree[v] by 1.
    4. After that, if for any node the indegree becomes 0, we will push that node again into the queue.
    5. We will repeat steps 3 and 4 until the queue is completely empty. Finally, completing the BFS we will get the linear ordering of the
       nodes in the answer array.
 *
 */
public class KhansAlgo {

	static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
		int[] indegree = new int[V];
		//find the indegree of all the nodes
		for(int i=0;i<V;i++) {
			for(int it:adj.get(i)) {
				indegree[it]++;
			}
		}
		//Take a queue and find the nodes with the indegree 0 and add to the queue .
		// indegree 0 means there task going to complete first before remaining tasks.
		Queue<Integer> q = new LinkedList<>();
		for(int i=0;i<V;i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		int[] ans = new int[V];
		//until queue it empty take a node from the queue add it to the toposort list and 
		//reduce its adjancent nodes indegree by 1 means removing edge and while reducing indegree 
		// if any node indegree became zero then add that to the queue
		int i=0;
		while(!q.isEmpty()) {
			int node = q.poll();
			ans[i++] = node;
			for(int it:adj.get(node)) {
				indegree[it]--;
				if(indegree[it] == 0)
					q.add(it);
			}
		}
		
		return ans;
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
