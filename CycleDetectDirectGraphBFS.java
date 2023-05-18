package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Sravani
 * 
 * Problem Statement: Given a Directed Graph with V vertices and E edges, check whether it contains any cycle or not.
 * 
 * Intuition:

	Since we know topological sorting is only possible for directed acyclic graphs(DAGs) if we apply Kahn’s algorithm in a directed cyclic 
	graph(A directed graph that contains a cycle), it will fail to find the topological sorting(i.e. The final sorting will not contain all
	the nodes or vertices). 
	So, finally, we will check the sorting to see if it contains all V vertices or not. If the result does not include all V vertices, 
	we can conclude that there is a cycle. 

 *	Initial Configuration:

	Indegree Array: Initially all elements are set to 0. Then, We will count the incoming edges for a node and store it in this array. 
	Queue: As we will use BFS, a queue is required. Initially, the nodes with indegree 0 will be pushed into the queue.

	topo list(Optional): Initially empty and is used to store the linear ordering or you can use couter variable.whenever we get the elements
	from the queue increment the counter and finally compare counter with the no of nodes

	The algorithm steps are as follows:

		1. we will calculate the in-degree of each node and store it in the in-degree array. We can iterate through the given adj list, 
		and simply for every node u->v, we can increase the in-degree of v by 1 in the in-degree array. 
	    2. We will push the node(s) with in-degree 0 into the queue.
		3. Then, we will pop a node from the queue including the node in our answer array, and for all its adjacent nodes, 
		we will decrease the in-degree of that node by one. For example, 
		4. After that, if for any node the in-degree becomes 0, we will push that node again into the queue.
		5. We will repeat steps 3 and 4 until the queue is completely empty. Now, completing the BFS we will get the linear ordering of 
		the nodes in the topo list.
		6. Finally, we will check the length of the topo list. If it equals V(no. of nodes) then the algorithm will return false
		otherwise it will return true.
 *
 */
public class CycleDetectDirectGraphBFS {
	/*
	 * We detect cycle using Topological sort. Topoplogical sort works only with acyclic graph and find the topo sort array.
	 * If the topo sort array size is equal to no of nodes means topo sort worked fine that means given graph is acyclic graph
	 * If not give graph has cycle.
	 */
	public static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        
		// calculate indegree of each node and store it in a array
		int[] indegree = new int[V];
		for(int i =0;i<V;i++) {
			for(int it:adj.get(i)) {
				indegree[it]++;
			}
		}
		
		//find the nodes with indegree 0 and add them to  queue
		Queue<Integer> q = new LinkedList<>();
		for(int i=0;i<V;i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		List<Integer> topo = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int node = q.poll();
			topo.add(node);
			for(int it:adj.get(node)) {
				indegree[it]--;
				if(indegree[it] == 0)
					q.add(it);
			}
		}
		if(topo.size() == V)
			return false;
		else return true;
    }
	
	public static void main(String args[]) {
		int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(0);
        adj.get(3).add(5);
        adj.get(4).add(2);
        adj.get(5).add(3);
        
        System.out.println(isCyclic(V,adj));
	}
}
