package db;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Book;
import model.Info;
import model.Student;
import utils.ui.UiConstants;

import java.net.URL;
import org.apache.log4j.Logger;

import service.ConnectionService;
import utils.properties.PropertiesHolder;
import utils.converter.ResultSetConverter;
import utils.error.SqlError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InfoDAO {
    
    private final static Logger logger = Logger.getLogger(InfoDAO.class);    
    
    public static List<Info> getInfo() {
        List<Info> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_INFO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getInfo(resultSet));
            }
            logger.info("List of infos has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean save(Info info) {
        boolean save = false;
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_INFO)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, info.getInfo());
            preparedStatement.execute();
            connection.commit();
            save = true;
            logger.info("Info : [" + info.toStringForLog() + "] is saved.");
        } catch (SQLException e) {
            SqlError.catchSqlException(e);
//            if (e.getErrorCode() == DatabaseError.UNIQUE_ID.getErrorCode()) {
//                ViewUtil.showError(UiConstants.Dialogs.PASSPORT_DATA_ERROR);
//            } else e.printStackTrace();
        }
        return save;
    }
    
    public Info getLastAddedInfo() {
    	Info info = new Info();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_INFO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            info = ResultSetConverter.getInfo(resultSet);
            logger.info("Last added info : [" + info.toStringForLog() + "] has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

	public List<Info> getAllInfo() {
        List<Info> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_INFO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getInfo(resultSet));
            }
            logger.info("List of info has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
