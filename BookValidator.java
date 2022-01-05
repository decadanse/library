package jmp;


public class BookValidator {

    public boolean checkAllTextField(Book book) {
        return !(book.getTitle().isEmpty()
                || book.getAuthor().isEmpty()
                || book.getYearOfPublication().isEmpty());
    }
}
