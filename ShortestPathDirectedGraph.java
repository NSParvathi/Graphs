package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sravani
 *
 *	Problem Statement : 
 *	Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length M, where there is a
 * 	directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i, 0<=i

	Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that 
	vertex.
	
 *	Solution:
 *	We will calculate the shortest path in a directed acyclic graph by using topological sort.
 * Topological sort can be implemented in two ways- BFS and DFS. Here, we will be implementing using the DFS technique. 
	
 *The shortest path in a directed acyclic graph can be calculated by the following steps:

		1. Perform topological sort on the graph using BFS/DFS and store it in a stack.  
		2. Now, iterate on the topo sort. For the source node, we will assign dist[src] = 0. 
		3. For every node that comes out of the stack which contains our topo sort, we can traverse for all its adjacent nodes,
		    and relax them. 
		4. In order to relax them, we simply do a simple comparison of dist[node] + wt and dist[adjNode]. 
		   Here dist[node] means the distance taken to reach the current node, and it is the edge weight between the node and the adjNode. 
			If (dist[node] + wt < dist[adjNode]), then we will go ahead and update the distance of the dist[adjNode] to the new found 
			better path. 
		5. Once all the nodes have been iterated, the dist[] array will store the shortest paths and we can then return it.
 */
public class ShortestPathDirectedGraph {

	static class Pair{
		int adjNode;
		int wt;
		Pair(int node,int wt){
			this.adjNode = node;
			this.wt =wt;
		}
	}
	public static int[] shortestPath(int N,int M, int[][] edges) {
		//Code here
		
		//First construct adjacency List from the given matrix. 
		//Adjacency list contains Pair<Integer,Integer> which represents pair of end node and edge weight
		
		List<List<Pair>> adj = new ArrayList<>();
		for(int i=0;i<N;i++) {
			adj.add(new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
		}
		
		//Now implement topsort to get ordering of vertices. I am using DFS traversal
		Deque<Integer> stack = new LinkedList<Integer>();
		
		boolean[] vis = new boolean[N];
		for(int i=0;i<N;i++) {
			if(vis[i]== false) {
				dfs_topo(i,vis,adj,stack);
			}
		}
		
		//Now we have node in stack in topological sorted order
		//Take distance array and get the nodes one by one from stack and find the distance from the source to that node
		int[] distance = new int[N];
		Arrays.fill(distance,(int)1e9);
		distance[0] = 0;
		while(!stack.isEmpty()) {
			int node = stack.pop();
			
			for(Pair it:adj.get(node)) {
				if(distance[node]+it.wt < distance[it.adjNode]) {
					distance[it.adjNode]= distance[node]+it.wt;
				}
			}
		}
		for(int i=0;i<N;i++) {
			if(distance[i]== 1e9) {
				distance[i]=-1;
			}
		}
		return distance;
	}
	
	public static void dfs_topo(int node,boolean[] vis,List<List<Pair>> adj,Deque<Integer> stack) {
		vis[node] = true;
		for(Pair P:adj.get(node)) {
			if(vis[P.adjNode]==false) {
				dfs_topo(P.adjNode,vis,adj,stack);
			}
		}
		stack.push(node);
	}
	
	public static void main(String args[]) {
		
		int n = 6, m= 7;
		int[][] edge= {{0,1,2},{0,4,1},{4,5,4}
				,{4,2,2},{1,2,3},{2,3,6},{5,3,1}};
		int[] distance = shortestPath(n,m,edge);
		System.out.println(Arrays.toString(distance));
	}
}
