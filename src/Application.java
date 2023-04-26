import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Register and load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Build the connection with DriverManager.getConnection
        Connection connection = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/employee",
                                "root",
                                "12345");

        // Create a statement
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);

        // Create SQL statement
        String SQL =    "INSERT INTO EmployeeDemographics " +
                        "(EmployeeID, FirstName, LastName, Age, Gender) " +
                        "VALUES(2003, 'Zarina', 'Ali', 30, 'Female')";

        // Add above SQL statement to the batch.
        statement.addBatch(SQL);

        // Create one more SQL statement
        SQL =   "INSERT INTO EmployeeDemographics " +
                "(EmployeeID, FirstName, LastName, Age, Gender) " +
                "VALUES(2004, 'Raja', 'Das', 32, 'Male')";
        // Add above SQL statement to the batch.
        statement.addBatch(SQL);

        // Create one more SQL statement
        SQL =   "UPDATE EmployeeDemographics " +
                "SET Age = 35 " +
                "WHERE EmployeeID = 1002";
        // Add above SQL statement to the batch.
        statement.addBatch(SQL);

        // Create an int[] to hold the returned values
        int[] count = statement.executeBatch();
        for( int c : count)
            System.out.println(c);

        // Explicitly commit statements to apply changes
        connection.commit();

        // Close the connection
        connection.close();
    }
}