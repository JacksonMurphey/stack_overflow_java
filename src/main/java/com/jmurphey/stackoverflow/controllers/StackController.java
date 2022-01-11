package com.jmurphey.stackoverflow.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmurphey.stackoverflow.models.Answer;
import com.jmurphey.stackoverflow.models.Question;
import com.jmurphey.stackoverflow.services.StackService;

@Controller
@RequestMapping("/questions")
public class StackController {

	
	@Autowired
	StackService service;
	
	
	
// --- Get Mapping --- 
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("questions", service.findQuestions());
		return "index.jsp";	
	}
	
	@GetMapping("/new")
	public String newQ(@ModelAttribute("question")Question question) {
		return "new.jsp";
	}
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id")Long id, @ModelAttribute("answer")Answer answer, Model model) {
		
		
		model.addAttribute("question", service.getOneQuestion(id));
		return "show.jsp";
	}
	
	
	
// --- Post Mapping ---
	
	@PostMapping("/create")
	public String createQuestion(@Valid @ModelAttribute("question")Question question, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			service.createQuestion(question);
			return "redirect:/questions";
		}
	}
	
	
	@PostMapping("/submitanswer")
	public String addAnswer(@Valid @ModelAttribute("answer") Answer answer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("question", service.getOneQuestion(answer.getQuestion().getId()));
			return "show.jsp";
		} else {
			
			this.service.createAnswer(answer);
			
			return "redirect:/questions/" + answer.getQuestion().getId();
		}
	}
	
}
