# Welcome to My Journal Repository!

Thank you for visiting the repository of my project, which I started relatively recently.

I am excited to introduce the first part of my project - My Book Journal - a website designed for logging and rating the books I've read.

Please note that the project is currently in progress. I apologize in advance for any imperfections you may encounter, particularly in terms of code organization and excessive comments. I am committed to making daily efforts to enhance the project and strive for continuous improvement.

Future plans for the project include the addition of a personalized notepad, activity tracker, calendar, and other ideas that come to mind. Your feedback and contributions are highly appreciated as they will play a significant role in shaping the project's growth.

Thank you for your interest and support!

## 1. Book Journal

The inspiration for creating the My Book Journal project stemmed from my personal need for an application that allows me to evaluate and record my thoughts about the books I've read. I wanted a centralized platform where I could keep track of my reading experiences and thoughts.

This project also provided an opportunity for me to learn new technologies. For this purpose, I employed **Java Spring Boot** and integrated it with a **MySQL** database. I drew inspiration from a [tutorial](https://www.youtube.com/watch?v=xnur2ujza6k&t=866s&ab_channel=ImaginaryEngineering) on creating a bookstore, and based on that I developed my own Book Journal.

### Key Features:
This project offers a range of functionalities. Here is the sequence in which activities are conducted:

1. **Welcome Page (welcome.html)**: This page introduces users to the project's updates (new features) and encourages them to log in. The login buttons are prominently placed.

2. **Login Page (login.html)**: After entering a valid email and password (authenticated against the database), users can log into their Book Journal.

3. **Registration Page (register.html)**: Users without an account can register here. All registration fields must be completed; otherwise, new user registration will not be possible.

4. **Home Page (home.html)**: After successfully logging in user goes to the home page that features a menu with buttons that prompt users to engage with their Book Journal. It also showcases three book categories in a carousel: recently added to the database, favorites, and recently read.

   - **Book List** Button: Redirects to **available_books.html** page, where all added books are displayed in a table. Clicking on a specific book's name leads to detailed information.
  
     - **Book description** page: Provides comprehensive details for each book, including name, author, rating, status, book description, and cover image.
  
     - **Editing and reviewing** on the book description page: Books' details can be edited using the "Edit" button. Users can add a book review by clicking the "R" icon and mark it as a favorite by clicking the heart icon. Reviews can be edited or deleted. The review date is automatically added (sourced from the computer's date).
  
   - **Favorite** Button: Redirects to **my_books.html** page, listing favorite books.
  
   - **Have Read** Button: Redirects to **read_books.html** page, displaying read books.
  
   - **Register New** Button: Redirects to **bookRegister.html** page, allowing users to add new books to the database. All required fields must be completed to save a new book.
  
   - **My Profile** Button: Redirects to **my_profile.html** page, where user information is presented.
  
     - **Profile Information**: User's name, username, the count of added, favorited, and read books, a brief user entry, and the last 3 posted books.
  
     - **Editing Profile**: By clicking "Edit profile," users can edit personal information, including name, username, email, password, and profile image. There's password verification for security.
  
   - **Log Out** Button: Logs the user out and redirects them to **welcome.html** page.



### Video

[<img src="https://i.ytimg.com/vi/ooWxca4_tt4/maxresdefault.jpg" width="100%">](https://www.youtube.com/embed/ooWxca4_tt4)
