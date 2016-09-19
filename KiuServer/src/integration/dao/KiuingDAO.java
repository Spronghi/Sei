package integration.dao;

import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.*;
import model.Kiuing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingDAO implements DAO<Kiuing> {
    private static final String SELECT = "SELECT `id`, `post_kiuer_id` FROM kiuing";
    private static final String SELECT_FROM_ID = "SELECT `id`, `post_kiuer_id` FROM kiuing WHERE `id`=?";
    private static final String INSERT = "INSERT INTO kiuing (post_kiuer_id) VALUES ('?')";
    private static final String UPDATE = "UPDATE kiuing SET post_kiuer_id='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM kiuing WHERE id=?";
    private static final Logger logger = Logger.getLogger(KiuingDAO.class.getName());

    private KiuingOperationDAO kiuingOperationDAO;
    private OperationDAO operationDAO;
    private PostKiuerDAO postDAO;
    
    public KiuingDAO(){
        kiuingOperationDAO = new KiuingOperationDAO();
        operationDAO = new OperationDAO();
        postDAO = new PostKiuerDAO();
    }

    private Kiuing setKiuing(ResultSet rs) throws SQLException {
        Kiuing kiuing = new Kiuing();
        kiuing.setId(rs.getInt(1));
        kiuing.setPost(postDAO.get(rs.getInt(2)));

        return kiuing;
    }

    @Override
    public Kiuing get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setKiuing(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<Kiuing> getAll() {
        List<Kiuing> agencies = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                agencies.add(setKiuing(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return agencies;
    }

    @Override
    public void create(Kiuing kiuing) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, kiuing.getPost().getId());
        kiuing.setId(ConnectorFactory.getConnection().executeUpdate(query));

        for(Operation operation : operationDAO.getAll()){
            KiuingOperation kiuingOperation = new KiuingOperation(false, operation, kiuing.getId());
            kiuingOperationDAO.create(kiuingOperation);
        }
    }

    @Override
    public void update(Kiuing kiuing) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, kiuing.getPost().getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(Kiuing kiuing) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, kiuing.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }
}
