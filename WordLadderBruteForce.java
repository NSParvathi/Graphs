package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Sravani
 * Problem Statemet :
 * 	Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
	Keep the following conditions in mind:

		A word can only consist of lowercase characters.
		Only one letter can be changed in each transformation.
		Each transformed word must exist in the wordList including the targetWord.
		startWord may or may not be part of the wordList
	
	Note:  If there’s no possible way to transform the sequence from startWord to targetWord return 0.
	
	
	Solution: 
	Queue: Define a queue data structure to store the BFS traversal.
	Hash set: Create a hash set to store the elements present in the word list to carry out the search and delete operations in O(1) time. 
	
	The steps to be followed to solve this problem:
	1. Firstly, we start by creating a queue data structure in order to store the word and the length of the sequence to reach that word 
	   as a pair. We store them in form of {word, steps}. 
	2. Then, we push the startWord into the queue with length as ‘1’.
	3. We now create a hash set wherein, we put all the elements in wordList to keep a check on if we’ve visited that word before or not.
	  In order to mark a word as visited here, we simply delete the word from the hash set. There is no point in visiting someone multiple
	  times during the algorithm. 
	4. pop the first element out of the queue and carry out the BFS traversal where, for each word popped out of the queue,
	   we try to replace every character with ‘a’ – ‘z’, and we get a transformed word. We check if the transformed word is present
	   in the set or not.
	   If the word is present, we push it in the queue and increase the count of the sequence by 1 and if not, we simply move on to 
	   replacing the original character with the next character.
	5. Also delete the word from the set if it matches with the transformed wor. 
	6. pop the next element out of the queue ds and if at any point in time, the transformed word becomes the same as the targetWord,
	   we return the count of the steps taken to reach that word. 
	7. If a transformation sequence is not possible, return 0.
		
	Time Complexity: O(N * M * 26)
	Where N = size of wordList Array and M = word length of words present in the wordList..
	Note that, hashing operations in an unordered set takes O(1) time, but if you want to use ordered set here, 
	then the time complexity would increase by a factor of log(N) as hashing operations in a set take O(log(N)) time.

	Space Complexity:  O( N ) 
	Where N = size of wordList Array.
	

 */
public class WordLadderBruteForce {

	static class Pair{
		String word;
		int steps;
		Pair(String word,int steps){
			this.word = word;
			this.steps = steps;
		}
	}
	public static int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        // Code here
		//Take a queue datastructure to maintain word and the no of steps while transforming the word
		Queue<Pair> q = new LinkedList<Pair>();
		//Take a HashSet to store the wordList and carry out search and delete operations.
		Set<String> set = new HashSet<>();
		//while transforming if you encounter a word present in wordList then delete that word from hashSet so that no furthur traversal
		//as this traversal take lower no of steps.
		
		for(int i=0;i<wordList.length;i++) {
			set.add(wordList[i]);
		}
		
		
		q.add(new Pair(startWord,1));
		while(!q.isEmpty()) {
			
			Pair p = q.poll();
			//Change each character in tha word from a to z and see new formed word exist in the wordList. 
			//If yes add that to queue with increased step count and delete it from the set
			String word = p.word;
			int steps = p.steps;
			set.remove(word);
			if(word.equals(targetWord))
				return steps;
			
			//Now,replace each character of word with character from 'a' to 'z' and check if new word exist.
			
			for(int i=0;i<word.length();i++) {
				for(char ch='a';ch<='z';ch++) {
					char[] replaceArray = word.toCharArray();
					replaceArray[i] = ch;
					String replacedWord = new String(replaceArray);
					if(set.contains(replacedWord)) {
						q.add(new Pair(replacedWord,steps+1));
						set.remove(replacedWord);
					}
				}
			}
		}
		return 0;
		
    }
	public static void main(String args[]) {
		String[] wordList = {"des","der","dfr","dgt","dfs"};
		String startWord = "der", targetWord= "dfs";
		System.out.println(wordLadderLength(startWord,targetWord,wordList));
	}
}
