package core.services.crud;

import core.models.City;
import core.models.Student;
import core.services.MySQLConnectorService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityCRUDService {

    public static final String SELECT_BY_CITY_NAME = "SELECT id FROM cities WHERE name = ?";
    public static final String SELECT_BY_ID = "SELECT name FROM cities WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM cities";
    public static final String INSERT_CITY = "INSERT INTO cities (name) VALUES (?)";
    public static final String DELETE_BY_ID = "DELETE FROM cities WHERE id = ?";

    

    public City getCityById(int id) {
        City city = null;

        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cityName = resultSet.getString("name");
                city = new City(cityName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    public int getCityId(String name) {
        int cityId = -1;

        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_CITY_NAME)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cityId = resultSet.getInt("id");
                return cityId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addCity(name);
        return getCityId(name);
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cityName = resultSet.getString("name");
                cities.add(new City(cityName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public void addCity(String name) {
        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCity(int id) {
        try (Connection connection = MySQLConnectorService.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
