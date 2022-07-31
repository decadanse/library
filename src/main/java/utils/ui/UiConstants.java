package utils.ui;

public interface UiConstants {

    interface Tittle {
        String START_TITTLE = "RESOURCES";
        String STUDENT_CHOOSE_TITTLE = "CHOOSE PROJECT";
        String STUDENT_INFO_TITTLE = "PROJECT INFO";
        String STUDENT_ADD_NEW_TITTLE = "ADD NEW PROJECT";
        String BOOK_EDIT_TITTLE = "EDIT RESOURCE";
        String BOOK_ADD_NEW_TITTLE = "ADD NEW RESOURCE";
        String ERROR = "ERROR";
        String CONFIRMATION = "CONFIRMATION";
        String INFO = "INFO";
    }

    interface Dialogs {
        String DATA_ERROR = "DatabaseError ERROR! CHECK YOUR DATABASE";
        String STUDENT_BOOK_ERROR = "PROJECT ERROR! CHECK YOUR PROJECT AND PROJECT RESOURCES";
        String CONFIRMATION_QUESTION = "Are you sure?";

    }

    interface Path {
        String INFO_PATH = "fxml/info/tabs/info_view.fxml";
        String ICON = "png/icon.jpeg";
        String START_PATH = "fxml/start_view.fxml";
        String STUDENT_CHOOSE_PATH = "fxml/student/student_choose_view.fxml";
        String STUDENT_INFO_PATH = "fxml/student/student_info_view.fxml";
        String STUDENT_ADD_NEW_PATH = "fxml/student/student_add_new_view.fxml";
        String BOOK_EDIT_PATH = "fxml/book/book_edit_view.fxml";
        String BOOK_ADD_NEW_PATH = "fxml/book/book_add_new_view.fxml";
    }
}
