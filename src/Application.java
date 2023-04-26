import java.sql.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import javax.sql.PooledConnection;
public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        private static final String DRIVER="com.mysql.cj.jdbc.Driver";
        private static final String URL="jdbc:mysql://localhost:3306";
        private static final String USER_NAME="root";
        private static final String PASSWORD="12345";
        private static final String INSERT_SQL =    "INSERT INTO Employee.EmployeeDemographics " +
                                                    "(EmployeeID, FirstName, LastName, Age, Gender)" +
                                                    " VALUES (2004, 'Mary', 'Kom', '35', 'Female')";
        private static final String UPDATE_SQL =    "UPDATE Employee.EmployeeDemographics " +
                                                    "SET Age = 31 " +
                                                    "WHERE EmployeeID = 2001";
        private static PooledConnection pooledConnection = null;
        private static Connection proxyCon1 = null;
        private static Connection proxyCon2 = null;

        public static void main(String[] args) throws ClassNotFoundException {

            Class.forName(DRIVER);

            // Connection - Pooling
            MysqlConnectionPoolDataSource mysqlConnectionPoolDataSource = new MysqlConnectionPoolDataSource();
            mysqlConnectionPoolDataSource.setURL(URL);
            mysqlConnectionPoolDataSource.setUser(USER_NAME);
            mysqlConnectionPoolDataSource.setPassword(PASSWORD);

            try{
                PooledConnection pooledConnection = mysqlConnectionPoolDataSource.getPooledConnection();
                System.out.println("PooledConnection is created");

                // Creating connection - proxyCon1
                proxyCon1 = pooledConnection.getConnection();
                Statement statement = proxyCon1.createStatement();
                statement.execute(INSERT_SQL);
                statement.close();

                // Creating connection - proxyCon2
                proxyCon2 = pooledConnection.getConnection();
                Statement statement1 = proxyCon2.createStatement();
                statement1.execute(UPDATE_SQL);
                statement1.close();

            } catch (SQLException ex){
                throw new RuntimeException(ex);
            } finally {
                try{
                    if(proxyCon1 != null)   proxyCon1.close();
                    if(proxyCon2 != null)   proxyCon1.close();
                    if(pooledConnection != null) pooledConnection.close();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
    }
}