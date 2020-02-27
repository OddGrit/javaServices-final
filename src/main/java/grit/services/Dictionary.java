package grit.services;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.MessageSource;

public class Dictionary {
	private MessageSource messageSource;
	private TreeMap<String, String> wordList = new TreeMap<String, String>();
	
	public Dictionary(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String translate(ArrayList<String> list, MessageSource messageSource, Locale locale) {
		TreeMap<String, String> map = new TreeMap<>();
		for (String s : list) {
			s = s.replace(".", " ").toLowerCase();
			map.put(s, getTranslation(s, messageSource, locale));
		}
		return JSONify(map);
	}
	
	public String add(String us, String uk) {
		us = us.replace(".", " ");
		uk = uk.replace(".", " ");
		if (wordList.containsKey(us))
			return "False";
		
		wordList.put(us, uk);
		return "True";
	}
		
	private String getTranslation(String word, MessageSource messageSource, Locale locale) {
		String trans;
		trans = messageSource.getMessage(word + ".word", null, "", locale);//.replace(".", " ");
		if (trans.equals("") && wordList.containsKey(word)) {
			trans = wordList.get(word);
		} 
		
		System.out.println(word + " " + trans);
		return trans;
	}
	
	private String JSONify(TreeMap<String, String> values) {
		String JSON = "{ ";
		for (Map.Entry<String, String> e : values.entrySet()) {
			JSON += "\"" + e.getKey() + "\": \"" + e.getValue() + "\",";
		}
		JSON += " }";
		return JSON;
	}
}
