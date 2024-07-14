package abhi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.*;

public class Libraryms {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// create a book instance to put values 
		Book obj = new Book();
		obj.setTitle("Chi Chi ");
		obj.setAuthor("OneeChan");
		obj.setAvailable(true);
		obj.setIsbn(5);
		// create a library object to access library functions 
		Library obj2 = new Library();
//		add book into library and database as well
		obj2.addBook(obj); // will be added to database automatically..make sure you have a database
		System.out.println("printing all the books..");
		List<Book> allBooks = obj2.getAllAvailableBooks();
		for(Book bk  : allBooks) { 
			System.out.println("title :"+bk.getTitle()+"| author :"+bk.getAuthor()+"| available :"+bk.isAvailable()+"| isbn :"+bk.getIsbn());
		}
		
		// save the book to a file
//		System.out.println("saving books to file");
//		obj2.saveBooksToFile("myBooks.txt");
//		DatabaseConn db = new DatabaseConn();
//		db.addBooksDB("Iron Man 3", "Robert Dowry Jr.", true, 1);
		
		//get book data from database
//		obj2.getBook("Iron Man 3");
	}
}


// class to create a book with following attributes
class Book  {
	private String title;
	private String author;
	private 	int isbn;
	private boolean isAvailable;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void changeAvailability(boolean available) {
        this.isAvailable = available;
    }
	
}

// class to store the objects of book class.
class Library {
	// should be able to add, remove and search for a book 
	public List<Book> books; // create list of books
	DatabaseConn db = null; // to store data into database
	
	public Library () {
		// initialize the members 
		books = new ArrayList<>();
		db = new DatabaseConn();
	}
	// add a book
	// making the methods synchronised so that only one thread can access the method at a time
	public synchronized  void  addBook(Book book) {
		books.add(book); // locally 
		String title = book.getTitle();
		String author = book.getAuthor();
		boolean avail = book.isAvailable();
		int isbn = book.getIsbn();
		db.addBooksDB(title, author, avail, isbn); //to add book to database..object is already inited
	}
	
	public synchronized void removeBook(Book book) {
		books.remove(book);
	}
	
	public synchronized void getBook(String title) throws SQLException {
		db.getBookDB(title); // get the data from database
	}
	
	
	public List<Book> getAllAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        synchronized (books) {
            for (Book book : books) {
                if (book.isAvailable()==true) {
                    availableBooks.add(book);
                }
            }
        }
        return availableBooks;
    }
	
	// something wrong with file system
	// save book to a file
	public void saveBooksToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            synchronized (books) {
                oos.writeObject(new ArrayList<>(books));
                System.out.println("Books saved to " + fileName + " successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }
	
	// Method to load books from a file using deserialization
    public void loadBooksFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            @SuppressWarnings("unchecked")
			List<Book> loadedBooks = (List<Book>) ois.readObject();
            synchronized (books) {
                books.clear();
                books.addAll(loadedBooks);
            }
            System.out.println("Books loaded !!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Could not load the books ! \n " + e.getMessage());
        }
   }

}

// class to create a mysql database instance to store books into database
class DatabaseConn {
	// to connect with the database
	private String url = "jdbc:mysql://localhost:3306/practice";
	private String user = "root";
	private String passwd = "2481";
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	public DatabaseConn () {
		// initialise database connection
		try {
			 con = DriverManager.getConnection(url, user, passwd);
			 st = con.createStatement();	
			 
			System.out.println("connected with database successfully !!");
		} catch (Exception e) {
			System.out.println("Cannot connect with the database !!"+e.getMessage());
		}
	}
	
	//add books from database 
	 public void addBooksDB(String title, String author, boolean isAvail, int isbn) {
	        String query = "INSERT INTO books (title, author, available, isbn) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement prep = con.prepareStatement(query)) {
	            prep.setString(1, title);
	            prep.setString(2, author);
	            prep.setBoolean(3, isAvail);
	            prep.setInt(4, isbn);
	            prep.executeUpdate();
	            System.out.println("Inserted successfully");
	        } catch (SQLException e) {
	            System.out.println("Error adding book to database: " + e.getMessage());
	        }
	    }
	 
	 // get book from database with its title
	 public void getBookDB(String title) throws SQLException {
	        String query = "select * from books where title= ?";
	        try(PreparedStatement prep = con.prepareStatement(query)) {
	        	prep.setString(1, title);
	        	ResultSet rs = prep.executeQuery();
	        	rs.next();
		        String auth = rs.getString("author");
		        String isavail = rs.getString("available");
		        String isbn = rs.getString("isbn");
		        System.out.println("Got the data from database ...");
		        System.out.println(" title-> " + title + " author-> " + auth + " available-> " + isavail + " isbn-> " + isbn);
	        }catch(SQLException e) { 
	        	System.out.println("Cannot run the query !!" + e.getMessage());
	        }
	        
	    }

}