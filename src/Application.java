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


        // 3. c) Call the stored procedures - CALLABLE STATEMENT
        // I -- get_num_of_empl
        CallableStatement callableStatement = connection.prepareCall("{call get_num_of_empl(?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER); // OUT PARAMETER

        // II -- get_first_name
        CallableStatement callableStatement1 = connection.prepareCall("{call get_first_name(?,?)}");
        callableStatement1.setInt(1, 1001); // IN PARAMETER
        callableStatement1.registerOutParameter(2, Types.VARCHAR); // OUT PARAMETER


        // 4. Execute the query and fetch the result set
        callableStatement.execute();
        callableStatement1.execute();


        // 5. Traverse through the resultset
        System.out.println(callableStatement.getInt(1));
        System.out.println(callableStatement1.getString(2));

        callableStatement.close();
        callableStatement1.close();


        // 6. Close the connection
        connection.close();
    }
}