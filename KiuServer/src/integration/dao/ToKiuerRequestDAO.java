package integration.dao;

import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.ToKiuerRequest;
import model.ToKiuerRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class ToKiuerRequestDAO {
    private static final String SELECT = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM kiuer_request";
    private static final String SELECT_FROM_ID = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM kiuer_request WHERE `id`=?";
    private static final String INSERT = "INSERT INTO kiuer_request (seen, kiuer_id, helper_id, post_kiuer_id, type_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE kiuer_request SET seen=?, kiuer_id=?, helper_id=?, post_kiuer_id=?, type_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kiuer_request WHERE id=?";
    private static final String SELECT_FROM_KIUER = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM kiuer_request WHERE `kiuer_id`=?";
    private static final Logger logger = Logger.getLogger(ToKiuerRequestDAO.class.getName());

    private KiuerDAO kiuerDAO;
    private HelperDAO helperDAO;
    private PostKiuerDAO postDAO;
    private RequestTypeDAO requestDAO;
    public ToKiuerRequestDAO(){
        postDAO = new PostKiuerDAO();
        helperDAO = new HelperDAO();
        kiuerDAO = new KiuerDAO();
        requestDAO = new RequestTypeDAO();
    }

    private ToKiuerRequest setToKiuerRequest(ResultSet rs) throws SQLException {
        ToKiuerRequest request = new ToKiuerRequest();
        request.setId(rs.getInt(1));
        request.setSeen(rs.getBoolean(2));
        request.setAddressee(kiuerDAO.get(rs.getInt(3)));
        request.setSender(helperDAO.get(rs.getInt(4)));
        request.setPost(postDAO.get(rs.getInt(5)));
        request.setType(requestDAO.get(rs.getInt(6)));
        return request;
    }

    public ToKiuerRequest get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setToKiuerRequest(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    public List<ToKiuerRequest> getAll() {
        List<ToKiuerRequest> requests = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                requests.add(setToKiuerRequest(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return requests;
    }

    public void create(ToKiuerRequest request) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, request.isSeen());
        query = Replacer.replaceFirst(query, request.getAddressee().getId());
        query = Replacer.replaceFirst(query, request.getSender().getId());
        query = Replacer.replaceFirst(query, request.getPost().getId());
        query = Replacer.replaceFirst(query, request.getType().getId());
        request.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(ToKiuerRequest request) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, request.isSeen());
        query = Replacer.replaceFirst(query, request.getAddressee().getId());
        query = Replacer.replaceFirst(query, request.getSender().getId());
        query = Replacer.replaceFirst(query, request.getPost().getId());
        query = Replacer.replaceFirst(query, request.getType().getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(ToKiuerRequest request) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, request.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public List<ToKiuerRequest> getAllBy(String flag, Object key){
        List<ToKiuerRequest> requestList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.KIUER)){
            query = SELECT_FROM_KIUER;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                requestList.add(setToKiuerRequest(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return requestList;
    }
}
