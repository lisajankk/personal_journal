package com.bookStore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.entity.User;
import com.bookStore.entity.Quotations;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import com.bookStore.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.io.IOUtils;

import java.util.*;
import java.util.stream.Collectors;
@SessionAttributes("user")

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;

	@Autowired
	private UserService UserService;
	
	private User user;

	@GetMapping("home") /*/{username} */
	public String home(Model model) {


		List<MyBookList>mblist=myBookService.getAllMyBooks();
		int countFavourites = mblist.size();

		List<Book>blist=service.getAllBook();				
		int countRegistered = blist.size();		

		List<Book> hvblist = blist.stream().filter(book -> "have read".equals(book.getStatus())).collect(Collectors.toList());
		int countHaveRead = hvblist.size();		


			if (!blist.isEmpty()) {
			int lastIndex1 = countRegistered - 1;
			Book lastBook1 = blist.get(lastIndex1);
			byte[] imageDataB1 = lastBook1.getImage();
			model.addAttribute("picb1",Base64.getEncoder().encodeToString(imageDataB1));
			model.addAttribute("lastBook1", lastBook1);
		
			int lastIndex2 = countRegistered - 2;
			Book lastBook2 = blist.get(lastIndex2);
			byte[] imageDataB2 = lastBook2.getImage();
			model.addAttribute("picb2",Base64.getEncoder().encodeToString(imageDataB2));
			model.addAttribute("lastBook2", lastBook2);

			int lastIndex3 = countRegistered - 3;
			Book lastBook3 = blist.get(lastIndex3);
			byte[] imageDataB3 = lastBook3.getImage();
			model.addAttribute("picb3",Base64.getEncoder().encodeToString(imageDataB3));
			model.addAttribute("lastBook3", lastBook3);
			} else {
				System.out.println("No books found.");
			}


			if (!mblist.isEmpty()) {
			int favouriteIndex1 = countFavourites - 1;
			Book favouriteBook1 = blist.get(favouriteIndex1);
			byte[] imageDataBF1 = favouriteBook1.getImage();
			model.addAttribute("fpicb1",Base64.getEncoder().encodeToString(imageDataBF1));
			model.addAttribute("favouriteBook1", favouriteBook1);
		
			int favouriteIndex2 = countFavourites - 2;
			Book favouriteBook2 = blist.get(favouriteIndex2);
			byte[] imageDataBF2 = favouriteBook2.getImage();
			model.addAttribute("fpicb2",Base64.getEncoder().encodeToString(imageDataBF2));
			model.addAttribute("favouriteBook2", favouriteBook2);

			int favouriteIndex3 = countFavourites - 3;
			Book favouriteBook3 = blist.get(favouriteIndex3);
			byte[] imageDataBF3 = favouriteBook3.getImage();
			model.addAttribute("fpicb3",Base64.getEncoder().encodeToString(imageDataBF3));
			model.addAttribute("favouriteBook3", favouriteBook3);

			} else {
				System.out.println("No books found.");
			}


			if (!hvblist.isEmpty()) {
			int readIndex1 = countHaveRead - 1;
			Book readBook1 = blist.get(readIndex1);
			byte[] imageDataBR1 = readBook1.getImage();
			model.addAttribute("rpicb1",Base64.getEncoder().encodeToString(imageDataBR1));
			model.addAttribute("readBook1", readBook1);
		
			int readIndex2 = countHaveRead - 2;
			Book readBook2 = blist.get(readIndex2);
			byte[] imageDataBR2 = readBook2.getImage();
			model.addAttribute("rpicb2",Base64.getEncoder().encodeToString(imageDataBR2));
			model.addAttribute("readBook2", readBook2);

			int readIndex3 = countHaveRead - 3;
			Book readBook3 = blist.get(readIndex3);
			byte[] imageDataBR3 = readBook3.getImage();
			model.addAttribute("rpicb3",Base64.getEncoder().encodeToString(imageDataBR3));
			model.addAttribute("readBook3", readBook3);

			} else {
				System.out.println("No books found.");
			}



		return "home";
	}

	@GetMapping("welcome")
	public String welcome(HttpSession session) {
		session.removeAttribute("user");
		return "home_without_login";
	}

	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}


	@GetMapping("/login")
	public String LogIn(HttpSession session) {
		 session.setAttribute("user", user);
		return "login";
	}


	@GetMapping("/register")
	public String Register() {
		return "register";
	}

	@GetMapping("/read_books")
	public String ReadBooks(Model model) {
		List<Book>blist=service.getAllBook();
		List<Book> haveReadBooks = blist.stream()
                                .filter(book -> "have read".equals(book.getStatus()))
                                .collect(Collectors.toList());

		model.addAttribute("book",haveReadBooks);
		return "read_books";
	}



	@GetMapping("my_profile")
	public String MyProfile(Model model) {
		model.addAttribute("user", user);
		List<MyBookList>mblist=myBookService.getAllMyBooks();
		List<Book>blist=service.getAllBook();
		int countFavourites = mblist.size();		
		int countRegistered = blist.size();
		model.addAttribute("countFavourites", countFavourites);
		model.addAttribute("countRegistered", countRegistered);

		long countHaveRead = blist.stream().filter(book -> "have read".equals(book.getStatus())).count();
		model.addAttribute("countHaveRead", countHaveRead);
		byte[] imageData = user.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));
		//Book book = service.getBookById(id);
		//model.addAttribute("book", book);
			if (!blist.isEmpty()) {
			int lastIndex1 = countRegistered - 1;
			Book lastBook1 = blist.get(lastIndex1);
			byte[] imageDataB1 = lastBook1.getImage();
			model.addAttribute("picb1",Base64.getEncoder().encodeToString(imageDataB1));
			model.addAttribute("lastBook1", lastBook1);
		
			int lastIndex2 = countRegistered - 2;
			Book lastBook2 = blist.get(lastIndex2);
			byte[] imageDataB2 = lastBook2.getImage();
			model.addAttribute("picb2",Base64.getEncoder().encodeToString(imageDataB2));
			model.addAttribute("lastBook2", lastBook2);

			int lastIndex3 = countRegistered - 3;
			Book lastBook3 = blist.get(lastIndex3);
			byte[] imageDataB3 = lastBook3.getImage();
			model.addAttribute("picb3",Base64.getEncoder().encodeToString(imageDataB3));
			model.addAttribute("lastBook3", lastBook3);

			} else {

				System.out.println("No books found.");
			}

		return "my_profile";
	}





	@GetMapping("edit_profile")
	public String EditProfile(Model model) {
		model.addAttribute("user", user);
		List<MyBookList>mblist=myBookService.getAllMyBooks();
		List<Book>blist=service.getAllBook();
		int countFavourites = mblist.size();		
		int countRegistered = blist.size();
		model.addAttribute("countFavourites", countFavourites);
		model.addAttribute("countRegistered", countRegistered);

		long countHaveRead = blist.stream().filter(book -> "have read".equals(book.getStatus())).count();
		model.addAttribute("countHaveRead", countHaveRead);
		byte[] imageData = user.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));
		return "edit_profile";
	}


	@PostMapping("/save_profile/{username}")
	public String saveProfile(Model model, 
							@RequestParam("name") String name, 
							@RequestParam("username") String username, 
							@RequestParam("email") String email, 
							@RequestParam(value = "repeat_new_password", required = false) String repeat_new_password, 
							@RequestParam(value = "new_password", required = false) String new_password, 
							@RequestParam(value = "old_password", required = false) String old_password,
							@RequestParam(value = "isPasswordFieldsVisible", required = false) Boolean isPasswordFieldsVisible,
							@RequestParam("booksAreMy") String booksAreMy, 
							@RequestParam("titleBooksAreMy") String titleBooksAreMy,  
							@RequestParam(value = "img", required = false) MultipartFile imageFile) 
	throws IOException {  
		if (!imageFile.isEmpty()) {
			try {
				byte[] imageBytes = imageFile.getBytes();
				user.setImage(imageBytes);
				UserService.save(user);
			} catch (IOException e) {}
		}
		user.setName(name);// Update the commentName field
		user.setUsername(username);
		user.setEmail(email);
		user.setBooksAreMy(booksAreMy);
		user.setTitleBooksAreMy(titleBooksAreMy);

		String editProfileMessage;
		
		if(!old_password.isEmpty()){
			System.out.println("Old password is not empty");
			System.out.println(old_password);
			System.out.println(user.getPassword());
			System.out.println(old_password.equals(user.getPassword()));
			byte[] imageData = user.getImage();
			model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));

			if(old_password.equals(user.getPassword())){
				System.out.println("Old password is correct");
				if(new_password.equals(repeat_new_password)){
					System.out.println("New password is correct to repeated new password");
					user.setPassword(repeat_new_password);
					UserService.save(user);         
					editProfileMessage = "Changes to the data were successful! Password was changed.";
					model.addAttribute("editProfileMessage", editProfileMessage);
					return "redirect:/my_profile";					
				} 
				else {
					System.out.println("New password is INcorrect to repeated new password");
					editProfileMessage = "The new password does not match the confirmed new password";
					model.addAttribute("editProfileMessage", editProfileMessage);
					return "edit_profile";
				}
			} 
			else {
				System.out.println("Old password is INcorrect");
				editProfileMessage = "The old password is wrong";
				model.addAttribute("editProfileMessage", editProfileMessage);
				return "edit_profile";
			}		
		}
		UserService.save(user);         
		editProfileMessage = "Changes to the data were successful!";

		model.addAttribute("editProfileMessage", editProfileMessage);
		return "redirect:/my_profile";
	}


	@PostMapping("/register_new_user")
	public String registerUser(@RequestParam("name") String name, 
							@RequestParam("username") String username, 
							@RequestParam("email") String email, 
							@RequestParam("password") String password,
							HttpSession session) 	
	throws IOException {  // Add HttpSession parameter
		
		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

 		ClassPathResource resource = new ClassPathResource("templates/images/human.png");
        byte[] imageBytes = IOUtils.toByteArray(resource.getInputStream());


		user.setImage(imageBytes);
		UserService.save(user);
		session.setAttribute("user", user);
		
		return "redirect:/login"; 
	}



	@PostMapping("/login_to_server")// DODAĆ DOPISEK ŻE POPRAWNIE SIĘ ZALOGOWAŁO LUB JEŚLI NIE, ZOSTAĆ NA STRONIE I DAĆ ZNAĆ ŻE LOGIN LUB HASŁO NIEPOPRAWNE
	public String LoginToServer(@RequestParam("loginName") String email, 
								@RequestParam("loginPassword") String password, 
								RedirectAttributes redirectAttributes) { 
		
		user = UserService.getUserByEmail(email);
		String username = user.getUsername();
		redirectAttributes.addAttribute("username", username);
		// Perform the validation
		if (user != null && user.getPassword().equals(password)) {
			// Successful login
			return "redirect:home"; ///{username}  REDIRECT NA STRONĘ LOGIN PO SUKCESYWNEJ REJESTRACJI 
		} else {
			// Invalid credentials, show error page or redirect to login page with error message
			return "redirect:/login?error=InvalidCredentials";
		}
	}



	@GetMapping("/myimage/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		byte[] imageData = b.getImage();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG); 
		return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
	}


	/*@RequestMapping("/mylink/{id}")
	public String getMyLink(@PathVariable("id") int id) {
		Book b=service.getBookById(id);

		String thisLink = "books/" + b.getLink() + ".html";
		return thisLink;
	}*/

	@RequestMapping("/bookDescription/{link}/{id}")
	public String bookDescription(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book", book);
		byte[] imageData = book.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));

		return "book_description";
	}


	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}


	@PostMapping("/save/{link}/{id}")
    public String editBook(@RequestParam("id") int bookId, 
							@RequestParam("author") String author, 
							@RequestParam("name") String name, 
							@RequestParam("genre") String genre, 
							@RequestParam("price") String price, 
							@RequestParam("link") String link, 
							@RequestParam("status") String status, 
							@RequestParam("description") String description,  
							@RequestParam(value = "img", required = false) MultipartFile imageFile) 
	throws IOException {
		Book b = service.getBookById(bookId);
		if (!imageFile.isEmpty()) {
			try {
				byte[] imageBytes = imageFile.getBytes();
				b.setImage(imageBytes);
				service.save(b);
			} catch (IOException e) {}
		}
		b.setAuthor(author); // Update the commentName field
		b.setName(name);
		b.setGenre(genre);
		b.setPrice(price);
		b.setLink(link);
		b.setDescription(description);
		b.setStatus(status);
        service.save(b);         
        return "redirect:/bookDescription/{link}/{id}";
    }


	@PostMapping("/save_new")
    public String addBook(@ModelAttribute Book b, 
							@RequestParam("author") String author, 
							@RequestParam("name") String name, 
							@RequestParam("genre") String genre, 
							@RequestParam("price") String price, 
							@RequestParam("link") String link, 
							@RequestParam("description") String description, 
							@RequestParam("img") MultipartFile imageFile) 
	throws IOException {
		if (!imageFile.isEmpty()) {
			try {
				byte[] imageBytes = imageFile.getBytes();
				b.setImage(imageBytes);
				service.save(b);
			} catch (IOException e) {}
		}
		b.setAuthor(author); // Update the commentName field
		b.setName(name);
		b.setGenre(genre);
		b.setPrice(price);
		b.setLink(link);
		b.setDescription(description);
        service.save(b);         
        return "redirect:/available_books";
    }



	@PostMapping("/save_comment/{link}/{id}")
	public String saveComment(@RequestParam("id") int bookId, 
								@RequestParam("commentName") String commentName, 
								@RequestParam("comment") String comment) {
		Book existingBook = service.getBookById(bookId); // Fetch the existing book by its primary key

		if (existingBook != null) {
			existingBook.setCommentName(commentName); // Update the commentName field			
			existingBook.setComment(comment);

			if(existingBook.getCommentDate() == null ){
				existingBook.setCommentDate(LocalDate.now().toString());
			}
			

			service.save(existingBook);
		}
		return "redirect:/bookDescription/{link}/{id}";
	}

	
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}


	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		b.setFavourites(true);
		MyBookList mb=new MyBookList(b.getId(),b.getAuthor(),b.getName(),b.getGenre(),b.getAmount(),b.getPrice(),b.getLink(),b.getImage(),b.getDescription(),b.getComment(), b.getCommentName(), b.getCommentDate(), b.getDateStartReading(), b.getDateStopReading(), b.getFavourites(), b.getStatus());
		myBookService.saveMyBooks(mb);
		return "redirect:/available_books";
	}

	@RequestMapping("/addToFavourites_bDescription/{link}/{id}")
	public String AddToFavourites_bDescription(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		b.setFavourites(true);
		MyBookList mb=new MyBookList(b.getId(),b.getAuthor(),b.getName(),b.getGenre(),b.getAmount(),b.getPrice(),b.getLink(),b.getImage(),b.getDescription(),b.getComment(), b.getCommentName(), b.getCommentDate(), b.getDateStartReading(), b.getDateStopReading(), b.getFavourites(), b.getStatus());
		myBookService.saveMyBooks(mb);
		return  "redirect:/bookDescription/{link}/{id}";
	}
	

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book", book);
		byte[] imageData = book.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));
		return "bookEdit";		
	}


	@RequestMapping("/addBookComment/{link}/{id}")
	public String addBookComment(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book", book);
		byte[] imageData = book.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));
		return "addBookComment";		
	}


	@RequestMapping("/addQuoteComment/{link}/{id}")
	public String addQuoteComment(@PathVariable("id") int id, Model model) {
		Book book = service.getBookById(id);
		model.addAttribute("book", book);
		byte[] imageData = book.getImage();
		model.addAttribute("pic",Base64.getEncoder().encodeToString(imageData));
		return "addQuoteComment";		
	}


	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}	


	@RequestMapping("/deleteComment/{link}/{id}")
	public String deleteComment(@PathVariable("id") int bookId) {
		Book existingBook = service.getBookById(bookId); // Fetch the existing book by its primary key

		if (existingBook != null) {
			existingBook.setCommentName(null); // Update the commentName field
			existingBook.setComment(null);
			existingBook.setCommentDate(null);

			service.save(existingBook);
		}
		return "redirect:/bookDescription/{link}/{id}";
	}


}
