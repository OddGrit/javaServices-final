package grit.services;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.MessageSource;

public class Dictionary {
	private DictionaryData data;
	
	public Dictionary(MessageSource messageSource) {
		data = new DictionaryData();
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
		return data.addWord(us.replace(".", " "), uk.replace(".", " "));
	}
	
	public String remove(String word) {
		return JSONify(word, data.deleteWord(word.toLowerCase().replace(".", " ")));
	}
		
	private String getTranslation(String word, MessageSource messageSource, Locale locale) {
		String trans;
		trans = messageSource.getMessage(word + ".word", null, "", locale);
		if (trans.equals("") && data.contains(word)) {
			trans = data.getTranslation(word);
		} 
		
		System.out.println(word + " " + trans);
		return trans;
	}
	
	private String JSONify(String name, String value) {
		TreeMap<String, String> map = new TreeMap<>();
		map.put(name, value);
		return JSONify(map);
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
