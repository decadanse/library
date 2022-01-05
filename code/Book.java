package jmp;


public class Book extends LibraryModel {

    private String title;
    private String author;
    private String genre;
    private String yearOfPublication;
    private boolean available;
    private int studentId;

    public Book() {
    }

    public Book(String title, String author, String genre, String yearOfPublication) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
        this.available = true;
    }
      
//    public static List<Book> getListOfBooks() {
//        return listOfBooks;
//    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
  ///  
    public void rented() {
        this.available = true;
    }

    public void returned() {
        this.available = false;
    }
///
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String toStringForSearch() {
        return "" + id + " " + title + " " + author + " " + genre + " " + yearOfPublication;
    }

    @Override
    public String toStringForLog() {
        return title + " " + author + " " + genre + " " + yearOfPublication;
    }
}
