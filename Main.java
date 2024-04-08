
import java.sql.*;
import java.util.Scanner;
import DAOs.MySqlDao;
import DTOs.Countries;

/**
 * Main author: Daniel Ferrer
 * Other contributors: Marketa Bila
 */
public class Main {
    //initializing MySqlDao object
    private MySqlDao countryDao;

    //main method, creates instance of the main class and calls start method
    public static void main(String[] args) throws SQLException{
        Main app = new Main();
        app.start();
    }

    //start method sets up the database connection
    //establishes a connection to the MySWL database using JDBC (Java Database Connectivity)
    public void start() throws SQLException {
        String url = "jdbc:mysql://localhost/";
        String dbName = "DTOs.Countries";
        String userName = "root";
        String password = "";
        boolean db = true;

        try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
            System.out.println("\nConnected to the database.");

            // Statements allow us to issue SQL queries to the database
            Statement statement = conn.createStatement();
        }
            //scanner for user input
            Scanner scanner = new Scanner(System.in);
            String choice;

            //set up a menu for user interaction
            while (db == true) {
                System.out.println("\nMenu:");
                System.out.println("1. Feature 1");
                System.out.println("2. Feature 2");
                System.out.println("3. Delete a country by ID");
                System.out.println("4. Feature 4");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                //reads user input
                int userChoice = scanner.nextInt();

                //performs actions based on the user's choice
                //it will call a method to retrieve information based on user's choice
                if (userChoice == 1) {
                    countryDao.getAllCountries();
                }
                else if (userChoice == 2) {
                    System.out.print("Enter the ID of the country to get: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    countryDao.getCountryById(id);
                }
                else if (userChoice == 3) {
                    System.out.print("Enter the ID of the country to delete: ");
                    int ID = scanner.nextInt();
                    scanner.nextLine();
                    countryDao.deleteCountryById(ID);
                }
                else if (userChoice == 4) {
                    System.out.print("Enter the ID of the country to add: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    System.out.print("Enter the name of the country to add: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter the capital of the country to add: ");
                    String capital = scanner.nextLine();

                    System.out.print("Enter the population of the country to add: ");
                    int population = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the religion of the country to add: ");
                    String religion = scanner.nextLine();

                    System.out.print("Enter the area of the country to add: ");
                    double area = scanner.nextDouble();

                    Countries newCountry = new Countries(id, name, capital, population, religion, area);
                    countryDao.insertCountry(newCountry);
                }
                else if (userChoice == 5) {
                    System.out.println("Exiting the program.");
                    break;  //exits the loop and ends the program
                }
                else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        }
    }