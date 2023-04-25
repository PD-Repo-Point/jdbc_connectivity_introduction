import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // 1. Register and load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");


        // 2. Build the connection with DriverManager.getConnection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employee",
                "root",
                "12345");


        // 3. b) Create a prepared statement - PREPARED STATEMENT
        String query = "insert into employeesalary value(?,?,?)";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);  //-> object of the statement & pass the query
        preparedStatement.setInt(1, 2004);
        preparedStatement.setString(2, "Developer");
        preparedStatement.setInt(3, 60000);


        // 4. Execute the query and fetch the number of rows affected
        int num = preparedStatement.executeUpdate();
        System.out.println("No. of rows affected " + num);


        // 4. Execute the query and fetch the result set
        ResultSet resultSet = statement.executeQuery("select * from employeesalary");

        
        // 5. Close the connection
        connection.close();
    }
}