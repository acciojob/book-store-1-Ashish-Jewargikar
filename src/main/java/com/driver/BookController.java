package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

   
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
    	book.setId(id++);
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

      
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
    	for(Book book:bookList) {
    		if(book.getId()==Integer.parseInt(id)) {
    			return new ResponseEntity<>(book,HttpStatus.OK);
    		}
    	}
    	return null;
    }
   
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
    	return new ResponseEntity<>(bookList,HttpStatus.OK);
    }
    
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getAuthor().equals(author)) {
                authorBooks.add(b);
            }
        }
       
        return new ResponseEntity<>(authorBooks,HttpStatus.OK);
    }
      
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre){
    	List<Book> bookByGenre = new ArrayList<>();    
         for(Book b : bookList) {
        	 if(b.getGenre().equals(genre)) {
        		 bookByGenre.add(b);
        	 }
         }
         return new ResponseEntity<>(bookByGenre,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete-book-by-id/{id}")
    	public ResponseEntity<String> deleteBookById(@PathVariable String id){
    		for(Book b:bookList) {
    			if(b.getId()==Integer.parseInt(id)) {
    				bookList.remove(b);
    				return new ResponseEntity<>("Book with id" +id+"deleted",HttpStatus.OK);
    			}
    		}
    		return new ResponseEntity<>("Book with id "+id+"Not found",HttpStatus.NOT_FOUND);
    	}
    
    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String>deleteAllBooks(){
    	bookList.clear();
    	return new ResponseEntity<>("All Books Deleted",HttpStatus.OK);
    }
    }

	// post request /create-book
	// pass book as request body

	// get request /get-book-by-id/{id}
	// pass id as path variable
	// getBookById()
	
	// delete request /delete-book-by-id/{id}
	// pass id as path variable
	// deleteBookById()
	
	// get request /get-all-books
	// getAllBooks()

    // delete request /delete-all-books
    // deleteAllBooks()

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()

