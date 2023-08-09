package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;
import com.bookStore.repository.BookRepository;

@Service
public class MyBookListService {
	
	@Autowired
	private MyBookRepository mybook;

	private BookRepository book;
	
	public void saveMyBooks(MyBookList book) {
		mybook.save(book);
	}

	public void save(Book b) {
		book.save(b);
	}
	
	public List<MyBookList> getAllMyBooks(){
		return mybook.findAll();
	}
	
	public void deleteById(int id) {
		mybook.deleteById(id);
	}
}
