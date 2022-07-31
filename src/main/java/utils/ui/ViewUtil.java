package utils.ui;

import controllers.BaseTableController;
import controllers.book.BookEditController;
import controllers.info.InfoController;
import controllers.student.StudentChooseController;
import db.InfoDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.Info;

import java.io.IOException;
import java.util.Optional;

public class ViewUtil {

    public static FXMLLoader showView(String viewPath, String viewTitle, boolean resizable) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(ViewUtil.class.getClassLoader().getResource(viewPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.getIcons().add(new Image(UiConstants.Path.ICON));
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(resizable);
        stage.show();
        return loader;
    }

    public static void showStudentChoose(Book book) {
        FXMLLoader loader = showView
                (UiConstants.Path.STUDENT_CHOOSE_PATH, UiConstants.Tittle.STUDENT_CHOOSE_TITTLE, false);
        StudentChooseController studentChooseController = loader.getController();
        studentChooseController.setBook(book);
    }

    public static void showBookEditView(BaseTableController<Book> baseTableController) {
        FXMLLoader loader = showView
                (UiConstants.Path.BOOK_EDIT_PATH, UiConstants.Tittle.BOOK_EDIT_TITTLE, false);
        BookEditController bookEditController = loader.getController();
        bookEditController.initBookInfo(baseTableController.getSelectionItem());
    }

    public static void showError(String dialog) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(UiConstants.Tittle.ERROR);
        alert.setContentText(dialog);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(UiConstants.Tittle.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(UiConstants.Dialogs.CONFIRMATION_QUESTION);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }


    public static void showImg() {//Info info) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = showView
                (UiConstants.Path.INFO_PATH, UiConstants.Tittle.INFO, false);
//        FXMLLoader loader = showView(UiConstants.Path.INFO_PATH, UiConstants.Tittle.INFO, true);
        InfoController infoController = loader.getController();
        infoController.initTableData();//initInfo(info);        
    }
//    public static FXMLLoader showImg() {
//        Stage stage = new Stage();
//        Parent root = null;
//        FXMLLoader loader = new FXMLLoader(ViewUtil.class.getClassLoader().getResource(UiConstants.Path.INFO_PATH));
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        stage.getIcons().add(new Image(UiConstants.Path.ICON));
//        stage.setTitle(UiConstants.Tittle.INFO);
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setResizable(true);
//        stage.show();
//        return loader;
//    }

}
