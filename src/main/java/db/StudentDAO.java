package db;

import model.Student;
import org.apache.log4j.Logger;
import service.ConnectionService;
import utils.error.DatabaseError;
import utils.error.SqlError;
import utils.properties.PropertiesHolder;
import utils.converter.ResultSetConverter;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAO {
    private final static Logger logger = Logger.getLogger(StudentDAO.class);


    public boolean save(Student student) {
        boolean save = false;
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_STUDENT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getMiddleName());
            preparedStatement.setString(4, student.getPassportData());
            preparedStatement.setString(5, student.getRegistrationDate());
            preparedStatement.setString(6, student.getBlacklistDate());
            preparedStatement.execute();
            connection.commit();
            save = true;
            logger.info("Student : [" + student.toStringForLog() + "] is saved.");
        } catch (java.sql.SQLNonTransientConnectionException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            SqlError.catchSqlException(e);
//            if (e.getErrorCode() == DatabaseError.UNIQUE_ID.getErrorCode()) {
//                ViewUtil.showError(UiConstants.Dialogs.PASSPORT_DATA_ERROR);
//            } else e.printStackTrace();
        }
        return save;
    }

    public void delete(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_STUDENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("Student : [" + student.toStringForLog() + "] is deleted.");
        } catch (java.sql.SQLNonTransientConnectionException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_STUDENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getMiddleName());
            preparedStatement.setString(4, student.getPassportData());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("Student : [" + student.toStringForLog() + "] is update.");
        } catch (java.sql.SQLNonTransientConnectionException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Student getStudentById(int id) {
        Student student = new Student();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = ResultSetConverter.getStudent(resultSet);
            logger.info("Student : [" + student.toStringForLog() + "] has been gotten by id : " + id + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student getLastAddedStudent() {
        Student student = new Student();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_STUDENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = ResultSetConverter.getStudent(resultSet);
            logger.info("Last added student : [" + student.toStringForLog() + "] has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getListAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENTS)) {
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getStudent(resultSet));
            }
            logger.info("List of students has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> getStudentsBlacklist() {
        List<Student> list = new ArrayList<>();

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_STUDENTS)) {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getStudent(resultSet));
            }
            logger.info("Blacklist of students has been gotten.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addToBlackList(Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PropertiesHolder.getProperty("DATE_FORMAT"));
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.ADD_STUDENT_TO_BLACKLIST_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, dateFormat.format(new Date()));
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("Student : [" + student.toStringForLog() + "] was added to blacklist.");
        } catch (java.sql.SQLNonTransientConnectionException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromBlackList(Student student) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_STUDENT_FROM_BLACKLIST_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "");
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
            connection.commit();
            logger.info("Student : [" + student.toStringForLog() + "] was deleted from blacklist.");
        } catch (java.sql.SQLNonTransientConnectionException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
