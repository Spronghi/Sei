package integration.dao;

import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.PostKiuer;
import model.RequestType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class RequestTypeDAO implements FilterDAO<RequestType> {
    private static final String SELECT = "SELECT `id`, `type` FROM request_type";
    private static final String SELECT_FROM_ID = "SELECT `id`, `type` FROM request_type WHERE `id`=?";
    private static final String INSERT = "INSERT INTO request_type (type) VALUES ('?')";
    private static final String UPDATE = "UPDATE request_type SET type='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM request_type WHERE id=?";
    private static final String SELECT_FROM_TYPE = "SELECT `id`, `type` FROM request_type WHERE `type`='?'";
    private static final Logger logger = Logger.getLogger(RequestTypeDAO.class.getName());

    public RequestTypeDAO(){}

    private RequestType setRequestType(ResultSet rs) throws SQLException {
        return new RequestType(rs.getInt(1),rs.getString(2));
    }

    @Override
    public RequestType get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setRequestType(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<RequestType> getAll() {
        List<RequestType> types = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                types.add(setRequestType(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return types;
    }

    @Override
    public void create(RequestType type) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, type.getType());
        type.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    @Override
    public void update(RequestType type) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, type.getType());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(RequestType type) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, type.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }
    @Override
    public List<RequestType> getAllBy(String flag, Object key){
        List<RequestType> postList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.TYPE)){
            query = SELECT_FROM_TYPE;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                postList.add(setRequestType(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return postList;
    }
}
