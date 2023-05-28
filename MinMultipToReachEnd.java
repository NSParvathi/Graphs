package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem Statement:
 * 
 * Given start, end, and an array arr of n numbers. At each step, the start is multiplied by any number in the array and 
 * then a mod operation with 100000 is done to get the new start.
 * Your task is to find the minimum steps in which the end can be achieved starting from the start.
 * If it is not possible to reach the end, then return -1.
 * 
 * Approach:
 * 
 * Below are the steps to be followed:
   1. Take a queue that stores the pair (steps,value) and insert the pair (0, start).
   2. Now pop the queue, get the value, and iterate through the given array by multiplying the value with the array 
      element and then mod by 100000.and insert it into the queue by incrementing the step count by 1. 
   3. At any point, if you encounter the end value, return the step number, as it would be the minimum number of steps
      required to reach the end.
   4. If the queue is empty and we don't encounter the end value at any point, return -1.
   5. Define a distance array that holds the number of steps required to reach that value. If the value marked
      as Infinity means you haven't reached it yet; insert into the queue to proceed further. If that array element is
      already marked, it indicates that it has already been achieved and that there is no need to work on it further.more
      as we are going step by step, and we can avoid repetition of the same number.
*/
public class MinMultipToReachEnd {

	static class Pair{
		int steps;
		int value;
		Pair(int steps,int value){
			this.steps = steps;
			this.value = value;
		}
	}
	
	
	static int minimumMultiplications(int[] arr, int start, int end) {

        
		//create Queue which stores Pair (step no , new value). No need of priority queue as we go step wise and distance
		//array to store no of steps required to reach that value.
		
		int[] distance = new int[100000];
		Arrays.fill(distance,(int)1e9);
		Queue<Pair> q = new LinkedList<>();
		distance[start] =0;
		q.add(new Pair(0,start));
		System.out.println(Arrays.toString(distance));
		while(!q.isEmpty()){
			
			Pair p = q.poll();
			int value = p.value;
			
			int steps = p.steps;
			if(value == end)
				return steps;
			for(int i=0;i<arr.length;i++) {
				int newValue = (value*arr[i])%100000;
				
				if(steps+1<distance[newValue]) {
					distance[newValue] = steps+1;
					q.add(new Pair(steps+1,newValue));
				}
			}
		}
		System.out.println(Arrays.toString(distance));
		
		return -1;
    }
	
	public static void main(String args[]) {
		int start=8, end=4288;
        int[] arr = {20,14,1,4,20};
        int ans = minimumMultiplications(arr,start,end);
        System.out.println(ans);
	}
}
