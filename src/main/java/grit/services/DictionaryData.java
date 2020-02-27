package grit.services;

import java.util.TreeMap;

public class DictionaryData {
	private TreeMap<String, String> wordList = new TreeMap<String, String>();
	
	public DictionaryData() {
		
	}
	
	public String addWord(String us, String uk) {
		if (wordList.containsKey(us))
			return "False";
		
		wordList.put(us, uk);
		return "True";
	}
	
	public boolean contains(String word) {
		return wordList.containsKey(word);
	}
	
	public String getTranslation(String word) {
		if (!wordList.containsKey(word))
			return "";
		return wordList.get(word);
	}
	
	public String deleteWord(String word) {
		return wordList.remove(word);
	}
}
