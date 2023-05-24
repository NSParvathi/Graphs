package graphs;
/**
 * 	Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths.
 *  Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.

	In this problem statement, we need to keep the following conditions in mind:

		A word can only consist of lowercase characters.
		Only one letter can be changed in each transformation.
		Each transformed word must exist in the wordList including the targetWord.
		startWord may or may not be part of the wordList.
		Return an empty list if there is no such transformation sequence.

	Approach:

		The Solution is divided into 2 steps :

		Step 1: Finding the minimum number of steps to reach the endWord and storing the step number for every string in a data structure.
		 		So that we can backtrack at later stages. 

			1. First, insert the beginWord in a queue data structure and then start the BFS traversal.
			2. Now, we pop the first element out of the queue and carry out the BFS traversal where, for each word popped out of the queue,
			 we try to replace every character with ‘a’ – ‘z’, and we get a transformed word. We check if the transformed word is present 
			 in the wordList or not.
			3. If the word is present, we push it in the queueand add it to the map and increase the count by 1 in the map.
			4. If the word is not present, then move on.
			5. Delete the word from the wordList if it matches with the transformed word.
			6. We pop the next element out of the queue ds and if at any point in time, if you reach the target word we stop the BFS.

		Step 2: Backtrack in the map from end to beginning to get the answer sequences.

			1. We follow the DFS traversal here but in a reverse manner.
			2. Start from the targetWord in the sequence we replace the character by character from a-z in that word and check whether
			 the transformed word is present in the map and at the previous level of the targetWord or not.
			3. If it present, we push the word into the list and then continue a similar traversal until we reach the beginWord.
			4. Following this technique, we would get all the shortest possible sequences to reach from beginWord to targetWord but in reverse order. 
			So the moment we encounter the beginWord in the traversal, we reverse the current sequence, insert it into the answer list and
			 then continue DFS with the original list.
			

 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder2_Optimised {

	static class Pair{
		String word;
		int steps;
		Pair(String word,int steps){
			this.word = word;
			this.steps = steps;
		}
	}
	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
	
		//Find the no of steps to reach the endWrod
		
		Map<String,Integer> map = new HashMap<>();
		int noOfSteps = findSeqSteps(beginWord,endWord,wordList,map);
		
		List<List<String>> ans = new ArrayList<>();
		if(noOfSteps == 0)
			return ans;
		
		/*
		 * Set<String> wordSet = new HashSet<>();
		 * 
		 * for(int i=0;i<wordList.size();i++) { wordSet.add(wordList.get(i)); }
		 * wordSet.add(beginWord); System.out.println(wordSet);
		 */
		
		//Start DFS from endWord and find the ladder
		
		dfs_op(endWord,new ArrayList<>(),beginWord,ans,map);
		
		return ans;
	}
	
	public static int findSeqSteps(String beginWord,String endWord,List<String> wordList,Map<String,Integer> map) {
		//delcate Queue for traversing
		Queue<Pair> queue = new LinkedList<>();
		Set<String> wordSet = new HashSet<>();
		for(int i=0;i<wordList.size();i++) {
			wordSet.add(wordList.get(i));
		}
	
		//Declare map to track words at each level
		if(!wordSet.contains(endWord))
			return 0;
		queue.add(new Pair(beginWord,1));
		wordSet.remove(beginWord);
		map.put(beginWord, 1);
		
		while(!queue.isEmpty()) {
			
			Pair p = queue.poll();
			String word = p.word;
			int steps = p.steps;
			
			if( word.equals(endWord))
				return steps;
			
			for(int i=0;i<word.length();i++) {
				for(char ch = 'a';ch<='z';ch++) {
					char[] charArray = word.toCharArray();
					charArray[i] = ch;
					String newWord = new String(charArray);
					if(wordSet.contains(newWord)) {
						queue.add(new Pair(newWord,steps+1));
						map.put(newWord,steps+1);
						wordSet.remove(newWord);
					}
				}
			}
		}
		
		
		return 0;
	}
	
	public static void dfs(String word,String startWord,Map<String,Integer> map,Set<String> wordSet,List<String> list,List<List<String>> ans) {
		list.add(word);
		wordSet.remove(word);
		
		if(word.equals(startWord)) {
			List<String> ansList = new ArrayList<>(list);
			Collections.reverse(ansList);
			ans.add(ansList);
			
			return;
		}
	
		
		for(int i=0;i<word.length();i++) {
			for(char ch='a';ch<='z';ch++) {
				char[] charSeq = word.toCharArray();
				charSeq[i]=ch;
				String newWord = new String(charSeq);		
				//whenever a new word formed checking whether it exist in the set and then map and the 
				// level in the map should be smaller for he new word when compared to original word. If yes then calling dfs(newWord)
				if(wordSet.contains(newWord) && map.containsKey(newWord)) {					
					if(map.get(newWord)  < map.get(word)) {	
						
						dfs(newWord,startWord,map,wordSet,list,ans);
						list.remove(newWord);  
						wordSet.add(newWord);
					}
				}
			}
		}
		
	}
	
	/**
	 * Optimised dfs function
	 * Instead of using set and map and comparing step no in map,Here I am going to use only map and then compare the step no
	 * of new word is equal to step no of word-1.
	 * @param args
	 */
	public static void dfs_op(String word,List<String> list,String targetWord,List<List<String>> ans,Map<String,Integer> map) {
		list.add(word);

		if(word.equals(targetWord)) {
			List<String> ladder = new ArrayList(list);
			Collections.reverse(ladder);
			ans.add(ladder);
		}
		
		for(int i=0;i<word.length();i++) {
			for(char ch='a';ch<='z';ch++) {
				char[] charSeq = word.toCharArray();
				charSeq[i] = ch;
				String newWord = new String(charSeq);
				if(map.containsKey(newWord) && map.get(newWord) == map.get(word)-1) {
					dfs_op(newWord,list,targetWord,ans,map);
					list.remove(newWord);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		String startWord = "red", targetWord = "tax";
		
		List<String> wordList = new ArrayList();
		wordList.add("ted");
		wordList.add("tex");
		wordList.add("red");
		wordList.add("tax");
		wordList.add("tad");
		wordList.add("den");
		wordList.add("rex");
		wordList.add("pee");
		List<List<String>> ans = findLadders(startWord,targetWord,wordList);
		System.out.println(ans);
	}
}
