package controllers.info;

import controllers.BaseTableController;
import controllers.observers.info.InfoObservable;
import controllers.observers.info.InfoObserver;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.Info;
import service.info.InfoService;
import validators.InfoValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController extends BaseTableController<Info> implements Initializable, InfoObserver {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfId, tfInfo;
    
    @FXML
    private TableView<Info> tvInfo;
    @FXML
    private TableColumn<Info, Integer> tcId;
    @FXML
    private TableColumn<Info, String> tcInfo;
    @FXML
    private AnchorPane apInfo;

    private InfoValidator infoValidator = new InfoValidator();
    private InfoService infoService = new InfoService();
    private Info info;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InfoObservable.registerInfoObserver(this);
        initTableData();
        initListeners();
    }
    
    private void initListeners() {
        btnOk.setOnAction(event -> {
            Info info = new Info(tfInfo.getText());
            if (infoValidator.checkAllTextField(info)) {
                if (infoService.saveInfo(info)) {
                	InfoObservable.onInfoAdded(infoService.getLastAddedInfo());
//                    getStage().close();
                }
            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }
    public void initInfo(Info info) {
        this.info = info;
        tfInfo.setText(info.getInfo());
    }
    private Stage getStage() {
        return ((Stage) apInfo.getScene().getWindow());
    }  
    
	@Override
	protected TableView<Info> getTableView() {
		return tvInfo;
	}

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(infoService.getAllInfo());
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        tvInfo.setItems(observableList);
        tvInfo.setVisible(true);
    }


	@Override
	protected TextField getTextFieldSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInfoAdded(Info info) {
		// TODO Auto-generated method stub
		
	}
}
