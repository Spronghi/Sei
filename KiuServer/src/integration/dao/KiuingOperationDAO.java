package integration.dao;

import exception.FlagNotFoundException;
import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.Kiuing;
import model.KiuingOperation;
import model.PostHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingOperationDAO implements FilterDAO<KiuingOperation>{
    private static final String SELECT = "SELECT `id`, `operation_id`, `done`, `kiuing_id` FROM kiuing_operation";
    private static final String SELECT_FROM_ID = "SELECT `id`, `operation_id`, `done`, `kiuing_id` FROM kiuing_operation WHERE `id`=?";
    private static final String INSERT = "INSERT INTO kiuing_operation (operation_id, done, kiuing_id) VALUES (?,'?',?)";
    private static final String UPDATE = "UPDATE kiuing_operation SET operation_id=?, done=?, kiuing_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kiuing_operation WHERE id=?";
    private static final String SELECT_FROM_KIUING = "SELECT `id`, `operation_id`, `done`, `kiuing_id` FROM kiuing_operation WHERE `kiuing_id`=?";
    private static final Logger logger = Logger.getLogger(KiuingOperationDAO.class.getName());

    private OperationDAO operationDAO;

    public KiuingOperationDAO(){
        operationDAO = new OperationDAO();
    }

    private KiuingOperation setKiuingOperation(ResultSet rs) throws SQLException {
        KiuingOperation kiuingOperation = new KiuingOperation();
        kiuingOperation.setId(rs.getInt(1));
        kiuingOperation.setOperation(operationDAO.get(rs.getInt(2)));
        kiuingOperation.setDone(rs.getBoolean(3));
        kiuingOperation.setKiuing(rs.getInt(4));
        return kiuingOperation;
    }

    @Override
    public KiuingOperation get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setKiuingOperation(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<KiuingOperation> getAll() {
        List<KiuingOperation> agencies = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                agencies.add(setKiuingOperation(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return agencies;
    }

    @Override
    public void create(KiuingOperation kiuingOperation) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, kiuingOperation.getOperation().getId());
        query = Replacer.replaceFirst(query, kiuingOperation.isDone());
        query = Replacer.replaceFirst(query, kiuingOperation.getKiuing());
        kiuingOperation.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    @Override
    public void update(KiuingOperation kiuingOperation) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, kiuingOperation.getOperation().getOperation());
        query = Replacer.replaceFirst(query, kiuingOperation.isDone());
        query = Replacer.replaceFirst(query, kiuingOperation.getKiuing());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(KiuingOperation kiuingOperation) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, kiuingOperation.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }
    @Override
    public List<KiuingOperation> getAllBy(String flag, Object key){
        List<KiuingOperation> operationList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.KIUING)){
            query = SELECT_FROM_KIUING;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                operationList.add(setKiuingOperation(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return operationList;
    }
}
