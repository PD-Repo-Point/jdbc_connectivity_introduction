import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*
         * SQL - Commands
         * DDL -> Create, Drop, Truncate, Alter, Rename
         * DML -> Insert, Update, Delete
         * DCL -> Grant, Revoke
         * TCL -> Commit, Rollback, Savepoint
         * --> ACID properties
         *
         * DQL -> Select
         * */

        /*                               ----> Compilation (Tokenization, Parsing-creates a parse tree, Optimization)
         *                                       |
         * Java Application  ----> DB ---        |
         *                                       |
         *                               <----- Execution
         * */

        // 1. Register and load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Build the connection with DriverManager.getConnection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employee",
                "root",
                "12345");

        // 3. a) Create a statement - STATEMENT
        Statement statement = connection.createStatement(); //-> receives object of Statement

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