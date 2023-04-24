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

        // 3. Create a statement
        Statement statement = connection.createStatement();

        // 4. Execute the query and fetch the result set
        ResultSet resultSet = statement.executeQuery("select * from employeesalary");

        // 5. Traverse through the resultset
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "
                    + resultSet.getString(2)+" "
                    + resultSet.getInt(3));
        }
        // 6. Close the connection
        connection.close();
    }
}