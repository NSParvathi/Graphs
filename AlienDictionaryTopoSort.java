package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author Sravani
 * Problem Statment :  Given a sorted dictionary of an alien language having N words and k starting alphabets of a standard dictionary. 
 * Find the order of characters in the alien language.
 * Approach: 
	We will apply the BFS(Breadth First Search) traversal technique. 
	
   Below are the steps:
      1.  we need to create the adjacency list for the graph.For that follow below steps
			a. We have to run a loop from the starting index to the second last index because we will check the ith element and the (i+1)th
			 element. 
			b. Inside the loop, we will pick two words (the word at the current index(s1) and the word at the next index(s2)).
			 For comparing them, we will again run a loop up to the length of the smallest string.
			c. Inside that second loop, if at any index we find inequality (s1[i] != s2[i]), we will add them to the adjacency list
			 (s1[i] —> s2[i]) in terms of numbers(subtracting ‘a’ from them), and then we will immediately come out of the loop. 
 			d. Finally, we will get the adjacency list.
	  2. Once the graph is created, simply perform a topo sort. 
		a. we will calculate the indegree of each node and store it in the indegree array.  
        b. we will push the node(s) with indegree 0 into the queue.
		c. Then, we will pop a node from the queue including the node in our answer array, and for all its adjacent nodes, we will decrease
		   the indegree of that node by one. 
        d. We will repeat steps b and c until the queue is completely empty. Finally, completing the BFS we will get the linear ordering of
         the nodes in the answer array.
		For the final answer, we will iterate on the answer array and add each element in terms of character to the final string. 
		Then we will return the string as our final answer.
 * 
 */
public class AlienDictionaryTopoSort {
	
	/**
	 * 
	 * @param dict  set of String
	 * @param N  Number of Strings in dict
	 * @param K  Number of alphabets
	 * @return
	 */
	public static String findOrder(String [] dict, int N, int K)
    {
        // Write your code here
        
        //Constuct the directed the graph from the given input means construct the adjacency list
		//K no of alphabets so K nodes
		List<List<Integer>> adj = new ArrayList<>();
		for(int i=0; i<K; i++) {
			adj.add(new ArrayList<>());
		}
		
		//compare adjacent Strings and find the alphabets what makes one String appears before next one. and constuct edge between bot the 
		//letter. For each letter converting into numbers. For example a->0,b->1,c-2...
		
		for(int i=0;i<N-1;i++) {
			String s1 = dict[i];
			String s2 = dict[i+1];
			int minIndex = Math.min(s1.length(), s2.length());
			for(int j=0;j<minIndex;j++) {
				if(s1.charAt(j) !=s2.charAt(j)) {
					char first = s1.charAt(j);
					char second = s2.charAt(j);
					adj.get(first-'a').add(second-'a'); // characters are converted into number and then create the edge.
					break;
				}
			}
		}
		
		
		//Now implement topological sorthing techniqie and find the order of alphabets
		int[] topo = topoSort(K,adj);
		
		StringBuffer sb  = new StringBuffer();
		for(int it:topo) {
			sb.append((char)(it+'a'));
		}
		return sb.toString();
    }
	
	public static int[] topoSort(int V,List<List<Integer>> adj) {
		
		//Step 1- Calculate indegree of each node
		int[] indegree = new int[V];
		for(int i=0;i<V;i++) {
			for(int it:adj.get(i)) {
				indegree[it]++;
			}
		}
		
		//Step 2 - find the nodes with indegree zero and add them to Queue
		Queue<Integer> q = new LinkedList<>();
		for(int i=0;i<V;i++) {
			if(indegree[i] == 0){
				q.add(i);
			}
		}
		int[] topoSort = new int[V];
		int i=0;
		//Step 3- until queue is not empty get the nodes and add them to topo sort list and reduce the adjacent nodes indegree.
		//If any node indegree became 0 then add that node to the queue.
		while(!q.isEmpty()) {
			int node = q.poll();
			topoSort[i++] = node;
			for(int it:adj.get(node)) {
				indegree[it]--;
				if(indegree[it] == 0)
					q.add(it);
			}
			
		}
		return topoSort;
	}
	
	public static void main(String args[]) {
		 int N = 5, K = 4;
	        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
	        String st = findOrder(dict,N,K);
	        System.out.println(st);
	}
	
}
