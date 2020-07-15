package com.hai.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hai.entity.Entry;
import com.hai.service.EntryService;

@Controller
public class EntryController {

	private Logger logger = LogManager.getLogger(EntryController.class);

	@Autowired
	private EntryService entryService;

	/**
	 * Display entries
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		logger.info(request.getRequestURL().toString());
		List<Entry> entries = entryService.getAllEntries();
		model.addAttribute("entries", entries);
		return "index";
	}

	/**
	 * Add entry
	 * 
	 * @param title
	 * @param text
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createEntry(HttpServletRequest request, @RequestParam("title") String title,
			@RequestParam("text") String text) {
		logger.info(request.getRequestURL().toString());
		entryService.addEntry(title, text);
		return "redirect:/";
	}

}
