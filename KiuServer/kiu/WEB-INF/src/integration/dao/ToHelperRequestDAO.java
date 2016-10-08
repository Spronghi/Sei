package integration.dao;

import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.ToHelperRequest;
import model.ToHelperRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class ToHelperRequestDAO {
    private static final String SELECT = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM helper_request";
    private static final String SELECT_FROM_ID = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM helper_request WHERE `id`=?";
    private static final String INSERT = "INSERT INTO helper_request (seen, kiuer_id, helper_id, post_kiuer_id, type_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE helper_request SET seen=?, kiuer_id=?, helper_id=?, post_kiuer_id=?, type_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM helper_request WHERE id=?";
    private static final String SELECT_FROM_HELPER = "SELECT `id`, `seen`, `kiuer_id`, `helper_id`, `post_kiuer_id`, `type_id` FROM helper_request WHERE `helper_id`=?";
    private static final Logger logger = Logger.getLogger(ToHelperRequestDAO.class.getName());

    private KiuerDAO kiuerDAO;
    private HelperDAO helperDAO;
    private PostKiuerDAO postDAO;
    private RequestTypeDAO requestDAO;
    public ToHelperRequestDAO(){
        postDAO = new PostKiuerDAO();
        kiuerDAO = new KiuerDAO();
        helperDAO = new HelperDAO();
        requestDAO = new RequestTypeDAO();
    }

    private ToHelperRequest setToHelperRequest(ResultSet rs) throws SQLException {
        ToHelperRequest request = new ToHelperRequest();
        request.setId(rs.getInt(1));
        request.setSeen(rs.getBoolean(2));
        request.setSender(kiuerDAO.get(rs.getInt(3)));
        request.setAddressee(helperDAO.get(rs.getInt(4)));
        request.setPost(postDAO.get(rs.getInt(5)));
        request.setType(requestDAO.get(rs.getInt(6)));
        return request;
    }

    public ToHelperRequest get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setToHelperRequest(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    public List<ToHelperRequest> getAll() {
        List<ToHelperRequest> requests = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                requests.add(setToHelperRequest(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return requests;
    }

    public void create(ToHelperRequest request) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, request.isSeen());
        query = Replacer.replaceFirst(query, request.getSender().getId());
        query = Replacer.replaceFirst(query, request.getAddressee().getId());
        query = Replacer.replaceFirst(query, request.getPost().getId());
        query = Replacer.replaceFirst(query, request.getType().getId());
        request.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(ToHelperRequest request) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, request.isSeen());
        query = Replacer.replaceFirst(query, request.getSender().getId());
        query = Replacer.replaceFirst(query, request.getAddressee().getId());
        query = Replacer.replaceFirst(query, request.getPost().getId());
        query = Replacer.replaceFirst(query, request.getType().getId());
        query = Replacer.replaceFirst(query, request.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(ToHelperRequest request) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, request.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public List<ToHelperRequest> getAllBy(String flag, Object key){
        List<ToHelperRequest> requestList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.HELPER)){
            query = SELECT_FROM_HELPER;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                requestList.add(setToHelperRequest(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return requestList;
    }
}
