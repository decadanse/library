package jmp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends LibraryModel {

    private String name;
    private String middleName;
    private String surname;
    private List<Book> bookList;

    public Student() {
    }

    public Student(String name, String middleName, String surname) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;   
        this.bookList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String toStringForSearch() {
        return "" + id + " " + name + " " + surname + " " + middleName;
    }

    @Override
    public String toStringForLog() {
        return surname + " " + name + " " + middleName;
    }
}
