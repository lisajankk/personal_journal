package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.MyBookListService;
import com.bookStore.service.BookService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBookListService service;
	@Autowired
	private BookService bservice;
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList( @PathVariable("id") int id) {
		Book b = bservice.getBookById(id); 
		b.setFavourites(false);
		bservice.save(b);

		service.deleteById(id);
		return "redirect:/my_books";
	}

	@RequestMapping("/deleteMyList_bDescription/{id}")
	public String deleteMyList_bDescription( @PathVariable("id") int id) {
		Book b = bservice.getBookById(id); 
		b.setFavourites(false);
		bservice.save(b);

		service.deleteById(id);
		return "redirect:/available_books";
	}

	
	@RequestMapping("/deleteFromFavourites_bDescription/{link}/{id}")
	public String DeleteFromFavourites_bDescription( @PathVariable("id") int id) {
		Book b = bservice.getBookById(id); 
		b.setFavourites(false);
		bservice.save(b);

		service.deleteById(id);
		return "redirect:/bookDescription/{link}/{id}";
	}
}
