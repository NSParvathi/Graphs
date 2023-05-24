package graphs;
/**
 * Problem Statement : 
 * Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.

	In this problem statement, we need to keep the following conditions in mind:

		A word can only consist of lowercase characters.
		Only one letter can be changed in each transformation.
		Each transformed word must exist in the wordList including the targetWord.
		startWord may or may not be part of the wordList.
		Return an empty list if there is no such transformation sequence.

	Solution: 
	Approach:
	This problem uses the BFS traversal technique for finding out all the shortest possible transformation sequences by exploring all possible
	ways in which we can reach the targetWord.
 
	Queue: Define a queue data structure to store the level-wise formed sequences. The queue will be storing a List of strings, 
	which will be representing the path till now. The last word in the list will be the last converted word. 
	
	Hash set: Create a hash set to store the elements present in the word list to carry out the search and delete operations in O(1) time. 
	
	List: Define a List ‘visitedWords’ to store the words which are currently being used for transformation on a particular level
	and a List of Lists ‘ans’ for storing all the shortest sequences of transformation.

	Below are the steps to be followed to solve the problem:
		1. We start by creating a hash set to store all the elements present in the wordList which would make the search and delete operations
		   faster for us to implement.
		2. Next, we create a Queue data structure for storing the successive sequences path in the form of a List which on transformation 
		   would lead us to the target word.
		3. Now, we add the startWord to the queue as a List and also push it into the visitedWord list to denote that this word is currently
		   being used for transformation in this particular level.
		4. Pop the first element out of the queue and carry out the BFS traversal, where for each word that popped out from the back of 
		   the list present at the top of the queue, we check for all of its characters by replacing them with ‘a’ – ‘z’ if they are present
		   in the wordList or not. In case a word is present in the wordList, we simply first push it onto the usedOnLevel vector and do not
		   delete it from the wordList immediately.
		5. Now, push that word into the list containing the previous sequence and add it to the queue. So we will get a new path, 
		   but we need to explore other paths as well, so pop the word out of the list to explore other paths.
		6. After completion of traversal on a particular level, we can now delete all the words that were currently being used on that level
		  from the wordList which are present in visitedWords list  which ensures that these words won’t be used again in the future, 
		  as using them in the later stages will mean that it won’t be the shortest path anymore.
		7. If at any point in time we find out that the last word in the sequence present at the top of the queue is equal to the target word,
		   we simply push the sequence into the resultant list.
		8. In case, there is no transformation sequence possible, we return an empty 2D vector.
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class wordLadder2 {

	public static ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {

		Queue<ArrayList<String>> queue = new LinkedList<>();

		// Add the words to the Set
		Set<String> wordSet = new HashSet<>();
		for (int i = 0; i < wordList.length; i++) {
			wordSet.add(wordList[i]);
		}
		ArrayList<String> list = new ArrayList<>();
		list.add(startWord);
		queue.add(list);
		wordSet.remove(startWord);
		ArrayList<ArrayList<String>> answer = new ArrayList<>();
	
		while (!queue.isEmpty()) {
			
			ArrayList<String> visitedWords = new ArrayList<>();
		
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				list = queue.poll();
				
				String word = list.get(list.size() - 1);
				
				if(word.equals(targetWord)) {
					answer.add(list);
					
					continue;
				}

				// convert each character in the string and see if the newly formed strng
				// available in the set
				
				for (int j = 0; j < word.length(); j++) {
					for (char ch = 'a'; ch <= 'z'; ch++) {
						char[] letters = word.toCharArray();
						letters[j] = ch;
						String newWord = new String(letters);
						if (wordSet.contains(newWord)) {
							ArrayList<String> newList = new ArrayList<>(list);
						
							newList.add(newWord);
						
							visitedWords.add(newWord);
							queue.add(newList);
						}
					}
				}
			}
			for (String visited : visitedWords) {
				wordSet.remove(visited);
			}
			
			

		}

		return answer;
	}
	
	public static void main(String args[]) {
		String startWord = "der", targetWord = "dfs";
		String[] wordList = {"des","der","dfr","dgt","dfs"};
		ArrayList<ArrayList<String>> ans = findSequences(startWord,targetWord,wordList);
		System.out.println(ans);
	}
}
