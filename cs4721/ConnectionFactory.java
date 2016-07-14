package cs4721;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author justin
 */
public class ConnectionFactory {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RECFLIX?useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "root";
    private static ConnectionFactory instance = new ConnectionFactory();
    
    private ConnectionFactory(){
        try{
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private Connection createConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    
    public static Connection getConnection(){
        return instance.createConnection();
    }
}
