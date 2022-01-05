package jmp;

public class StudentValidator {

    public boolean checkAllTextField(Student student) {
        return !(student.getName().isEmpty()
                || student.getSurname().isEmpty()
                || student.getMiddleName().isEmpty());
    }
}
