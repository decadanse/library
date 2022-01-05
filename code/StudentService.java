package jmp;


import java.util.ArrayList;


import java.util.List;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentService {
    
    private ArrayList<Student> allStudents;
    
    public StudentService() {
        allStudents = new ArrayList<>( );
    }
       
    private TextField nameField;
    private TextField middleNameField;
    private TextField surnameField;
    
    //private Student student = new Student();

    public void addStudent(Student student) { 
        allStudents.add(student);
        System.out.println("Student : [" + student.toStringForLog() + "] is added.");
    }

    public void deleteStudent(Student student) {
        allStudents.remove(student);
        System.out.println("Student : [" + student.toStringForLog() + "] is deleted.");
    }

    public Student getStudentById(int id) {
        Student student = new Student();
        student = allStudents.get(id);
        System.out.println("Student : [" + student.toStringForLog() + "] has been gotten by id : " + id + ".");
        return student; 
    }

    public List<Student> getAllStudents() {
        System.out.println("All Students");
        for (int i = 0; i < allStudents.size(); i++) {
            // Get each item (in this case print each item)
            System.out.println(allStudents.get(i));
        }
        return null;
    }
}
