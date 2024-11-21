package com.example.wordStatDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@SpringBootApplication
@Controller
public class WordStatDemoApplication {
	@Autowired
	WordCheck wordCheck;

	public static void main(String[] args) {
		SpringApplication.run(WordStatDemoApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "wordStat";
	}

	@GetMapping("/wordStat")
	public String index1(){
		return "wordStat";
	}

	//@GetMapping("/getresult")
	@PostMapping("/getresult")
	public String getresult(String username, String word
			, Model model
	//							 , RedirectAttributes model
	){
		System.out.println("получено word = " + word);
		int x = wordCheck.saveWord(username, word);
		//String sx = String.valueOf(x);
		model.addAttribute("x", x);
		System.out.println("wordCheck = " + wordCheck.userWords);
		System.out.println("пользователи, которые вводили это слово = " + wordCheck.getUsersByWord(word));
		System.out.println("слова, которые вводил этот пользователь = " + wordCheck.getWordsByUser(username));
		return "wordStat";
		//return new RedirectView("/wordStat");
	}

	@GetMapping("/adminka")
	public String adminka( Model model){
		//model.addAttribute("s", wordCheck.userWords.toString());
		model.addAttribute("tab_lines", wordCheck.userWords);
		return "adminka";
	}

	@PostMapping("/save")
	public String save(Model model){
		model.addAttribute("tab_lines", wordCheck.userWords);
        try {
            wordCheck.save();
			model.addAttribute("message", "сохранено");
        } catch (IOException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "adminka";
	}

	@PostMapping("/load")
	public String load(Model model){

		try {
			wordCheck.load();
			model.addAttribute("message", "загружено");
		} catch (IOException e) {
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("tab_lines", wordCheck.userWords);
		return "adminka";
	}
}
