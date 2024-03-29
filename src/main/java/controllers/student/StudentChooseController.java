package controllers.student;

import controllers.BaseTableController;
import controllers.observers.book.BookObservable;
import controllers.observers.student.StudentObservable;
import controllers.observers.student.StudentObserver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentChooseController extends BaseTableController<Student> implements Initializable {
    @FXML
    private AnchorPane apStudentChose;
    @FXML
    private TableView<Student> tvChooseStudent;
    @FXML
    private TableColumn tcId, tcSurname, tcName, tcMiddleName, tcPassportData;
    @FXML
    private Button btnGiveBook, btnCancel;
    @FXML
    private TextField tfSearch;

    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    protected TableView<Student> getTableView() {
        return tvChooseStudent;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return tfSearch;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(studentService.getAllStudents());
        tcId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tcSurname.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tcMiddleName.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        tcPassportData.setCellValueFactory(new PropertyValueFactory<Student, String>("passportData"));
        tvChooseStudent.setItems(observableList);
        tvChooseStudent.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableData();
        initListeners();
    }


    private void initListeners() {
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search();
            }
        });
        btnGiveBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (getSelectionItem() != null) {
                    bookService.giveBook(book, getSelectionItem());
                    Book givenBook = bookService.getBookById(book.getId());
                    givenBook.setStudentId(getSelectionItem().getId());
                    BookObservable.onBookGiven(givenBook);
                    getStage().close();
                }
            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return ((Stage) apStudentChose.getScene().getWindow());
    }


}
