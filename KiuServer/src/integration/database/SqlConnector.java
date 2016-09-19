package integration.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

class SqlConnector implements Connector{
    private final static String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
    private final static String SQL_PATH = "jdbc:mysql://localhost:3306/kiu";
    private final static String USER_ID = "kiu_user";
    private final static String PASSWORD = "popo";
    private final static Logger logger;
    private Connection connection;
    private static SqlConnector instance;

    private SqlConnector() {
        initConnection();
    }
    static {
        logger = Logger.getLogger(Connector.class.getName());
        instance = new SqlConnector();
    }
    private void initConnection(){
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
        }
        try {
            connection = DriverManager.getConnection(SQL_PATH, USER_ID, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }
    public static SqlConnector getInstance(){
        return instance;
    }
    @Override
    public ResultSet executeQuery(String query){
        ResultSet resultSet = null;
        try {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.toString());
        }
        return resultSet;
    }
    @Override
    public int executeUpdate(String query){
        int id=0;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.toString());
        }
        return id;
    }
}