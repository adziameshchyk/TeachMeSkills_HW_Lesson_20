package core.services.crud;

import core.models.City;
import core.models.Student;
import core.services.MySQLConnectorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUDService {

    public static final String INSERT_STUDENT = "INSERT INTO students (first_name, last_name, city_id) VALUES (?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM students";
    public static final String SELECT_BY_ID = "SELECT * FROM students WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM students WHERE id = ?";

    public Student getStudentById(int id) {
        Student student = null;

        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int cityId = resultSet.getInt("city_id");

                student = new Student(studentId, firstName, lastName, cityId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int cityId = resultSet.getInt("city_id");

                students.add(new Student(id, firstName, lastName, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(Student student) {
        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(int id) {
        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
