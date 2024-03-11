package DAOs;

import DTOs.Countries;
import Exceptions.DaoExceptions;

import java.sql.*;
import java.util.ArrayList;

public class MySqlDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Countries";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws DaoExceptions {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DaoExceptions("Failed to establish a database connection", e);
        }
    }

    public void freeConnection(Connection connection) throws DaoExceptions {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoExceptions("Failed to free database connection", e);
        }
    }
    //feature 1
    //Main author: Daniel Ferrer
    public ArrayList<Countries> getAllCountries() throws SQLException {
        ArrayList<Countries> countries = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Countries")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Countries country = new Countries(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Capital"),
                            resultSet.getInt("Population"),
                            resultSet.getString("Religion"),
                            resultSet.getDouble("Area")
                    );
                    countries.add(country);
                }
            }
        }

        return countries;
    }

    //feature 2
    //Main author: Marketa Bila
    public Countries getCountryById(int id) throws SQLException {
        Countries country = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries WHERE id = ?")) {

            statement.setInt(1, id); // Set the value of the parameter in the prepared statement
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    country = new Countries(
                            resultSet.getInt("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Capital"),
                            resultSet.getInt("Population"),
                            resultSet.getString("Religion"),
                            resultSet.getDouble("Area ")
                    );
                }
            }
        }

        return country;
    }
    // Feature 3
// Main author: Daniel Ferrer
    public void deleteCountryById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM countries WHERE id = ?")) {

            statement.setInt(1, id); // Set the value of the parameter in the prepared statement

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No country found with the given ID, no rows affected.");
            } else {
                System.out.println("Country deleted successfully.");
            }
        }
    }

    //feature 4
    //Main author: Marketa Bila
    public void insertCountry(Countries country) throws DaoExceptions {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO countries (Name, Capital, Population, Religion, Area) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, country.getName());
            statement.setString(2, country.getCapital());
            statement.setInt(3, country.getPopulation());
            statement.setString(4, country.getReligion());
            statement.setDouble(5, country.getArea());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoExceptions("Failed to insert country, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    country.setId(generatedKeys.getInt(1));
                } else {
                    throw new DaoExceptions("Failed to insert country, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DaoExceptions("Failed to insert country", e);
        }
    }
}

