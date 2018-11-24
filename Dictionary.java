/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:]5
- Comment: I made it so it outputs an ArrayList<String> and does not need any parameters.
- My method reads the words from the "words.txt" file. [Mark out of 5:]5
- Comment: It reads every single word from the words.txt file and stops reading once there are no words left (it is null).
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:]5
- Comment: It returns all of the contents from words.txt in an ArrayList.

2. readWordList
- I have the correct method definition [Mark out of 5:]5
- Comment: I made it so it outputs an ArrayList<String> and the parameter it needs is the wordList string.
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:]
- Comment: each word is separated by a comma which is used for the String ArrayList which is made and then returned.

3. isUniqueList
- I have the correct method definition [Mark out of 5:]5
- Comment:  I made it so it outputs a boolean and the parameter needed is the word list, an ArrayList<String>.
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:]5
- Comment: I use a nested for loops so that it checkes each word with every other word.
- Exits the loop when a non-unique word is found. [Mark out of 5:]5
- Comment: It exits the loop immediately if the boolean is false which means a word isn't unique.
- Returns true if all the words are unique and false otherwise. [Mark out of 5:]5
- Comment: It only returns true if all of the words are different from each other, it goes through all of them. Otherwise, it returns false.

4. isEnglishWord
- I have the correct method definition [Mark out of 5:]5
- Comment:  I made it so it outputs a boolean and the only parameter needed is the current word (which is a String).
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:]3
- Comment: I used the binarySearch method with the word and array so that if it returned something <0 it was not in the dictionary.
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:]2
- Comment: I did this using an if-else statement for if the value was bigger than or equal to 0

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:]5
- Comment: I made it so it outputs a boolean and the only parameter needed is the current word and the following word (both Strings).
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:]10
- Comment: I made a counter and, after looping through, if the counter ends up being exactly the amount of letters needed minus one then the it returns true.

6. isWordChain
- I have the correct method definition [Mark out of 5:]5
- Comment: I made it so it outputs a boolean and the parameter needed is the word list, an ArrayList<String>.
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:]10
- Comment: It calls all  three of  these functions and the boolean returned from this method affects the main which prints the appropriate message.

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10:]10
- Comment: It reads everything from words.txt by referring to the isWordChain method which refers to the readDictionary method which does this.
- Asks the user for input and calls isWordChain [Mark out of 5:]5
- Comment: It asks the user for an input which is then used (after changing it to an ArrayList) when I call isWordChain.

 Total Mark out of 100 (Add all the previous marks):100
*/
package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Dictionary {
	
	public static ArrayList<String> readDictionary() throws Exception {
		FileReader fileReader = new FileReader("words.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> dictionaryList =new ArrayList<String>();
		boolean endOfFileFound = false;
		while (!endOfFileFound)
		{
			String word = bufferedReader.readLine();
			if (word ==null)
			{
				endOfFileFound = true;
			}
			else 
			{
				dictionaryList.add(word);
			}
		}
		bufferedReader.close();
		fileReader.close();
		return dictionaryList;
	}
	
	public static ArrayList<String> readWordList(String inputWordList) {
		ArrayList<String> wordList = new ArrayList<>(Arrays.asList(inputWordList.split(",")));
		return wordList;
	}
	
	public static boolean isUniqueList(ArrayList<String> wordList) {
		for (int i = 0; i < wordList.size()-1; i++) {
	        for (int j = i+1; j < wordList.size(); j++) {
	             if (wordList.get(i)== wordList.get(j)) {
	                 return false;
	             }
	        }
	    }              
	    return true;          
	}
	
	public static boolean isEnglishWord(String word) throws Exception {
		boolean isEnglishWord;
		String[] array = readDictionary().toArray(new String[readDictionary().size()]);
		if (Arrays.binarySearch(array, word)>=0) {
			isEnglishWord = true;
		}
		else
		{
			isEnglishWord = false;
		}
		return isEnglishWord;
	}
	
	public static boolean isDifferentByOne(String word1, String word2) {
		boolean isDifferentByOne = false;
		if (word1.length() == word2.length())
		{
			int counter = 0;
			for (int index=0; index<word1.length(); index++)
			{
				if (word1.charAt(index) == word2.charAt(index))
				{
					counter++;
				}
			}
			if (counter == word1.length()-1)
			{
				isDifferentByOne = true;
			}
		}
		return isDifferentByOne;
	}
	
	public static boolean isWordChain(ArrayList<String> inputWordList) throws Exception {
		boolean isWordChain=true;
		int index=0;
		if (isUniqueList(inputWordList))
		{
			while (isWordChain && index <inputWordList.size() )
			{
				for (index=0; index < inputWordList.size(); index++)
				{
					if (isEnglishWord(inputWordList.get(index)) && (index==0 || isDifferentByOne(inputWordList.get(index),inputWordList.get(index-1))))
					{
						isWordChain = true;
					}
					else
					{
						isWordChain = false;
					}
				}
			}
		}
		return isWordChain;
	}

	public static void main(String[] args) throws Exception {
		boolean finished = false;
		while (!finished)
		{
			System.out.println("Please input a chain of words, separated by commas (or an empty list to quit):");
			Scanner input = new Scanner(System.in);
			String wordList;
			if (!(wordList = input.nextLine()).isEmpty())
			{
				ArrayList<String> wordChain = readWordList(wordList);
				if (isWordChain(wordChain))
				{
					System.out.println("It's a word chain!");
				}
				else
				{
					System.out.println("This is not a word chain");
				}
			}
			else
			{
				finished = true;
				System.out.println("The program has ended.");
			}
		}
	}

}
