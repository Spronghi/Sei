package integration.dao;

import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.Operation;
import model.Operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class OperationDAO implements FilterDAO<Operation> {
    private static final String SELECT = "SELECT `id`, `operation` FROM operation";
    private static final String SELECT_FROM_ID = "SELECT `id`, `operation` FROM operation WHERE `id`=?";
    private static final String INSERT = "INSERT INTO operation (operation) VALUES ('?')";
    private static final String UPDATE = "UPDATE operation SET operation='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM operation WHERE id=?";
    private static final String SELECT_FROM_OPERATION = "SELECT `id`, `operation` FROM operation WHERE `operation`='?'";
    private static final Logger logger = Logger.getLogger(OperationDAO.class.getName());

    public OperationDAO(){}

    private Operation setOperation(ResultSet rs) throws SQLException {
        return new Operation(rs.getInt(1),rs.getString(2));
    }

    @Override
    public Operation get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setOperation(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<Operation> getAll() {
        List<Operation> operations = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                operations.add(setOperation(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return operations;
    }

    @Override
    public void create(Operation operation) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, operation.getOperation());
        operation.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    @Override
    public void update(Operation operation) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, operation.getOperation());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(Operation operation) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, operation.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public List<Operation> getAllBy(String flag, Object key) {
        List<Operation> operationList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.OPERATION)){
            query = SELECT_FROM_OPERATION;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                operationList.add(setOperation(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return operationList;
    }
}
