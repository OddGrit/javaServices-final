package grit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Configuration

@RestController
public class DictionaryController {
	@Autowired
	private MessageSource messageSource;
	
	private Dictionary dictionary;
	
	public DictionaryController() {
		dictionary = new Dictionary(messageSource);
	}
	 
	@RequestMapping(path = "/getWords", method = RequestMethod.GET)
	public String translate(@RequestParam ArrayList<String> words,
			 @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		System.out.println(words.toString());
		return dictionary.translate(words, messageSource, locale);
	}
	 
	@RequestMapping(path = "/{word}", method = RequestMethod.GET)
	public String translate(@PathVariable String word, 
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		ArrayList<String> list = new ArrayList<>();
		list.add(word);
		return dictionary.translate(list, messageSource, locale);
	}
	 
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String add(String us, String uk) {
		 return dictionary.add(us, uk);
	}
}
