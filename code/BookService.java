package jmp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import javax.swing.JOptionPane;



public class BookService {
    
    private ArrayList<Book> catalog;
    /**
     * Creates the BookService with an empty catalog
    */
    //constructor
    public BookService() {
        catalog = new ArrayList<>( );
        //loadbooks();
    }
    
    public List<Book> getListOfBooks() {
        return catalog;
    }
    
    private TextField titleField;
    private TextField authorField;
    private TextField genreField;
    private TextField yearOfPublicationFiels;

    
    public void addBookToCatalog(Book book) {
        catalog.add(book); 
        System.out.println("Book : [" + book.toStringForLog() + "] is added.");
    }

    public void deleteBook(Book book) {
        catalog.remove(book);
        System.out.println("Book : [" + book.toStringForLog() + "] is deleted.");
    }    

    public String giveBook(Book book, Student student) { 
        int id = book.getId();
        int stud_id = student.getId();
        List<Book> newList = student.getBookList();
        String answer;
        
        if (book.isAvailable()){
            book.setAvailable(false);
            book.setStudentId(stud_id);
            newList.add(book);
            student.setBookList(newList);
            answer = "Book : [" + book.toStringForLog() + "] is given to " + student.toStringForLog();
            System.out.println(answer);
        }else{
            answer = "Book : [" + book.toStringForLog() + "] is not available.";
            System.out.println(answer);
        }
        return answer;
    }
    
    //give book to the reading hall
    public String giveBook(Book book) { 
        int id = book.getId();
        String answer;
        if (book.isAvailable()){
            book.setAvailable(false);
            book.setStudentId(1); // 1 is reading hall id
            answer = "Book : [" + book.toStringForLog() + "] can be found in reading room.";
            System.out.println(answer);
        }else{
            answer = "Book : [" + book.toStringForLog() + "] is not available.";
            System.out.println(answer);
        }
        return answer;
    }
    
    //take book back to the library (from reading hall) 
    public String takeBook(Book book) {
        int id = book.getId();
        String answer;
        boolean hallHasIt = false;
        
        if (book.getStudentId() == 1){ //id = 1 need to be defined as reading hall id
            hallHasIt = true;
        }
        
        if (!book.isAvailable() && hallHasIt){
            book.setAvailable(true);
            answer = "Book : [" + book.toStringForLog() + "] is taken from the reading hall.";
            //this part: also can take book back from not only reading hall, but from the student also
            //solution: add check for book.getStudentId() 
            //solved
            System.out.println(answer);
        }else{
            answer = "Book : [" + book.toStringForLog() + "] is already in the library.";
            System.out.println(answer);
        }
        return answer;
    }
    //take book back to the library (from student)    
    public String takeBook(Book book, Student student) { 
        int id = book.getId();   
        int stud_id = student.getId();
        List<Book> newList = student.getBookList();
        String answer;
        boolean heHasIt = false;
        
        if (book.getStudentId() == stud_id){
            heHasIt = true;
        }
       
        if (!book.isAvailable() && heHasIt){
            book.setAvailable(true);
            book.setStudentId(stud_id);
            newList.remove(book);
            student.setBookList(newList);
            answer = "Book : [" + book.toStringForLog() + "] is taken from " + student.toStringForLog();
            System.out.println(answer);
        }else{
            answer = "Book : [" + book.toStringForLog() + "] is already in the library.";
            System.out.println(answer);
        }
        return answer;
    }
    
    public List<Book> getAllBooks() {
        System.out.println("All Books");
        List<Book> list = new ArrayList<>( );
        for (Book book : catalog) {
            // Get each item (in this case print each item)
            list.add(book);
            System.out.println(book.getId());
        }        
        return list;//null;
    }

    public List<Book> getAvailableBooks() {
        System.out.println("Available Books");
        List<Book> list = new ArrayList<>( );
        for (Book book : catalog) {
            if(book.isAvailable()){
                list.add(book);
                System.out.println(book.getId());
            }
        }
        return list;//null;
    }

    public List<Book> getNotAvailableBooks() {
        System.out.println("Not Available Books");
        List<Book> list = new ArrayList<>( );
        for (Book book : catalog) {
            if(!book.isAvailable()){
                list.add(book);
                System.out.println(book.getId());
            }
        }
        return list;//null;
    }

    public List<Book> getBookListByStudent(Student student) {
        System.out.println("List of books has been received from a student : " + student.toStringForLog() + ".");
        return student.getBookList();
    }

    public Book getBookById(int id) {
        Book book = new Book();
        book = catalog.get(id);
        System.out.println("Book : [" + book.toStringForLog() + "] has been gotten by id : " + id + ".");
        return book;   
    }

}
