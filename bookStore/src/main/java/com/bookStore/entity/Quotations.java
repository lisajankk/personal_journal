package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.*;

@Entity
//@Table(name="quotations")

public class Quotations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//@Column(nullable = false, length = 20)
	private String author;

	//@Column(nullable = false, length = 20) // unique = true, 
	private String quote;

	//@Column(nullable = false, length = 45) //unique = true, 
	private int bookId;

	public Quotations(int id, String author, String quote, int bookId) {
		super();
		this.id = id;
		this.author = author;
		this.quote = quote;	
		this.bookId = bookId;
	}

	public Quotations() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}
