package com.bookStore.entity;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;


@Entity
@Table(name="MyBooks")
public class MyBookList {

	//Book book = new Book();
	//int aaa = book.getAmount();	
	
	@Id
	private int id;
	private String author;
	private String name;
	private String genre;
	private String price;
	private int amount;
	private String link;
	private byte[] image;
	private String description;
	private String comment;
	private String commentName;
	private String commentDate;
	private String dateStartReading;
	private String dateStopReading;
	private Boolean favourites = false;
	private String status;

	public MyBookList(int id, String author, String name, String genre, int amount, String price, String link, byte[] image, String description, String comment, String commentName, String commentDate, String dateStartReading, String dateStopReading, Boolean favourites, String status) {
		super();
		this.id = id;
		this.author = author;
		this.name = name;
		this.genre = genre;
		this.price = price;		
		this.amount = amount;
		this.link = link;	
		this.image = image;
		this.description = description;
		this.comment = comment;
		this.commentName = commentName;
		this.commentDate = commentDate;
		this.dateStartReading = dateStartReading;
		this.dateStopReading = dateStopReading;
		this.favourites = favourites;
		this.status = status;
	}
	public MyBookList() {
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getAmount() {
		amount = 1;
		//++amount;
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getDateStartReading() {
		return dateStartReading;
	}
	public void setDateStartReading(String dateStartReading) {
		this.dateStartReading = dateStartReading;
	}


	public String getDateStopReading() {
		return dateStopReading;
	}
	public void setDateStopReading(String dateStopReading) {
		this.dateStopReading = dateStopReading;
	}

	public Boolean getFavourites() {
		return favourites;
	}
	public void setFavourites(Boolean favourites) {
		this.favourites = favourites;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}