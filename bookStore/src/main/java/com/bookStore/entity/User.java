package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.*;

@Entity
//@Table(name="user")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//@Column(nullable = false, length = 20)
	private String name;

	//@Column(nullable = false, length = 20) // unique = true, 
	private String username;

	//@Column(nullable = false, length = 45) //unique = true, 
	private String email;

	//@Column(nullable = false, length = 64)
	private String password;

	private byte[] image;

	private String booksAreMy;

	private String titleBooksAreMy;

	public User(int id, String name, String username, String email, String password, byte[] image, String booksAreMy, String titleBooksAreMy) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;		
		this.password = password;
		this.image = image;
		this.booksAreMy = booksAreMy;
		this.titleBooksAreMy = titleBooksAreMy;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getBooksAreMy() {
		return booksAreMy;
	}
	public void setBooksAreMy(String booksAreMy) {
		this.booksAreMy = booksAreMy;
	}
	public String getTitleBooksAreMy() {
		return titleBooksAreMy;
	}
	public void setTitleBooksAreMy(String titleBooksAreMy) {
		this.titleBooksAreMy = titleBooksAreMy;
	}
}
