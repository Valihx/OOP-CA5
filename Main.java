
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
        app.runMenu();
    }

    public void start() throws SQLException {
        String url = "jdbc:mysql://localhost/";
        String dbName = "DTOs.Countries";
        String userName = "root";
        String password = "";
        
        try( Connection conn = DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("\nConnected to the database.");

            // Statements allow us to issue SQL queries to the database
            Statement statement = conn.createStatement();
    }
        void start() {
        return;
        }
        }
        //main author: Daniel Ferrer
        public void runMenu() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            String choice;
    
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Feature 1");
                System.out.println("2. Feature 2");
                System.out.println("3. Delete a country by ID");
                System.out.println("4. Feature 4");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
    
                userChoice = scanner.nextLine();
    
                switch (userChoice) {
                    case "1":
                        countryDao.getAllCountries();
                        break;
                    case "2":
                        System.out.print("Enter the ID of the country to get: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        CountryDAO dao = new CountryDAO();
                        dao.getCountryById(id);
                        break;
                    case "3":
                        System.out.print("Enter the ID of the country to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        CountryDAO dao = new CountryDAO();
                        dao.deleteCountryById(id);
                        break;
                    case "4":
                    countrydao.insertCountry(); 
                        break;
                    case "5":
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } while (!choice.equals("5"));
        }
}
