package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * There are a total of n tasks you have to pick, labeled from 0 to n-1. Some tasks may have prerequisites tasks, for example to pick task 0
 *  you have to first finish tasks 1, which is expressed as a pair: [0, 1]
	Given the total number of n tasks and a list of prerequisite pairs of size m. Find a ordering of tasks you should pick to finish all
	tasks.
	Note: There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all tasks, return an
	empty array. Returning any correct order will give the output as 1, whereas any invalid order will give the output 
	"No Ordering Possible".
 */
public class CourseSchedule {

	 static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) 
	    {
	        // add your code here
		 //Constructing adjacency list from the give pairs "prerequisites"
		 // If there is a pair [1,3] means 3 should complete before 1 that means edge sholud be from 3->1. So. adj.get(3).add(1)
		 List<List<Integer>> adj = new ArrayList<>();
		 	 for(int i=0;i<n;i++) {
		 		 adj.add(new ArrayList<>());
		 	 }
		 	 /*
		 	  *  prerequisites.get(0).add(0);
        		prerequisites.get(0).add(1)
        		Input format is like baove. Here get(0) represent first pair and add(0) and add(1) represent pair[0,1]
        		Pair [0,1] means 1 should complete first before 0 ->0
		 	  */
		 	 for(int i=0;i<m;i++) {
		 		 //i means ith pair and get(1) second element in the pair that should be completed first and get(0) first element
		 		 adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
		 	 }
		 	 
		 	 //calculate indegree of all the nodes
		 	 int[] indegree = new int[n];
		 	 for(int i=0;i<n;i++) {
		 		 for(int it:adj.get(i)) {
		 			 indegree[it]++;
		 		 }
		 	 }
		 	 //Take a data structure queue to prefrom topo sort BFS and ans array to store ordered nodes
		 	 Queue<Integer> q = new LinkedList<>();
		 	 int[] topo  = new int[n];
		 	 
		 	 for(int i=0;i<n;i++) {
		 		 if(indegree[i] == 0) {
		 			 q.add(i);
		 		 }
		 	 }
		 	 int i =0;
		 	 while(!q.isEmpty()) {
		 		 int node = q.poll();
		 		 topo[i++] = node;
		 		 for(int it:adj.get(node)) {
		 			 indegree[it]--;
		 			 if(indegree[it] == 0)
		 				 q.add(it);
		 		 }
		 	 }
		 	 if(topo.length == n)
		 		 return topo;
		 	 return new int[] {};
	    }
	 
	 public static void main(String args[]) {
		 int N = 4;
	        int M = 3;
	        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
	        for (int i = 0; i < N; i++) {
	            prerequisites.add(i, new ArrayList<>());
	        }


	        prerequisites.get(0).add(0);
	        prerequisites.get(0).add(1);

	        prerequisites.get(1).add(1);
	        prerequisites.get(1).add(2);

	        prerequisites.get(2).add(2);
	        prerequisites.get(2).add(3);
	        int ans[] = findOrder(N,M,prerequisites);
	        System.out.println(Arrays.toString(ans));
	 }
}
