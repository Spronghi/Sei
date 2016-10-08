package integration.dao;

import exception.FlagNotFoundException;
import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.PostKiuer;
import util.DateFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class PostKiuerDAO {
    private static final String SELECT = "SELECT `id`, `open`, `duration`, `cost`, `start`, `to_helper_feedback`, `to_kiuer_feedback`, `kiuer_id`, `helper_id`, `place_id` FROM post_kiuer";
    private static final String SELECT_FROM_ID = "SELECT `id`, `open`, `duration`, `cost`, `start`, `to_helper_feedback`, `to_kiuer_feedback`, `kiuer_id`, `helper_id`, `place_id` FROM post_kiuer WHERE `id`=?";
    private static final String INSERT = "INSERT INTO post_kiuer (open, duration, cost, start, to_helper_feedback, to_kiuer_feedback, kiuer_id, helper_id, place_id) VALUES (?,?,?,'?',?,?,?,?,?)";
    private static final String UPDATE = "UPDATE post_kiuer SET open=?, duration=?, cost=?, start='?', to_helper_feedback=?, to_kiuer_feedback=?,kiuer_id=?, helper_id=?, place_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM post_kiuer WHERE id=?";
    private static final String SELECT_FROM_KIUER = "SELECT `id`, `open`, `duration`, `cost`, `start`, `to_helper_feedback`, `to_kiuer_feedback`, `kiuer_id`, `helper_id`, `place_id` FROM post_kiuer WHERE `kiuer_id`=?";
    private static final String SELECT_FROM_HELPER = "SELECT `id`, `open`, `duration`, `cost`, `start`, `to_helper_feedback`, `to_kiuer_feedback`, `kiuer_id`, `helper_id`, `place_id` FROM post_kiuer WHERE `helper_id`=?";
    private static final String SELECT_FROM_CITY = "SELECT post_kiuer.* from `post_kiuer` INNER JOIN `place` on post_kiuer.place_id=place.id && place.city=\"?\"";
    private static final Logger logger = Logger.getLogger(PostKiuerDAO.class.getName());

    private KiuerDAO kiuerDAO;
    private HelperDAO helperDAO;
    private PlaceDAO placeDAO;

    public PostKiuerDAO(){
        placeDAO = new PlaceDAO();
        kiuerDAO = new KiuerDAO();
        helperDAO = new HelperDAO();
    }

    private PostKiuer setPostKiuer(ResultSet rs) throws SQLException {
        PostKiuer post = new PostKiuer();
        post.setId(rs.getInt(1));
        post.setOpen(rs.getBoolean(2));
        post.setDuration(rs.getInt(3));
        post.setCost(rs.getBigDecimal(4).doubleValue());
        post.setStartDate(DateFormatter.toLocalDateTime(rs.getDate(5).toLocalDate(), rs.getTime(5).toLocalTime()));
        post.setToHelperFeedback(rs.getFloat(6));
        post.setToKiuerFeedback(rs.getFloat(7));
        post.setKiuer(kiuerDAO.get(rs.getInt(8)));
        post.setHelper(helperDAO.get(rs.getInt(9)));
        post.setPlace(placeDAO.get(rs.getInt(10)));
        return post;
    }

    public PostKiuer get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setPostKiuer(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    public List<PostKiuer> getAll() {
        List<PostKiuer> posts = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                posts.add(setPostKiuer(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return posts;
    }

    public void create(PostKiuer post) {
        String query = INSERT;
        placeDAO.create(post.getPlace());
        query = Replacer.replaceFirst(query, post.isOpen());
        query = Replacer.replaceFirst(query, post.getDuration());
        query = Replacer.replaceFirst(query, post.getCost());
        query = Replacer.replaceFirst(query, post.getStartDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getToHelperFeedback());
        query = Replacer.replaceFirst(query, post.getToKiuerFeedback());
        query = Replacer.replaceFirst(query, post.getKiuer().getId());
        query = Replacer.replaceFirst(query, post.getHelper().getId());
        query = Replacer.replaceFirst(query, post.getPlace().getId());
        post.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(PostKiuer post) {
        String query = UPDATE;
        placeDAO.update(post.getPlace());
        query = Replacer.replaceFirst(query, post.isOpen());
        query = Replacer.replaceFirst(query, post.getDuration());
        query = Replacer.replaceFirst(query, post.getCost());
        query = Replacer.replaceFirst(query, post.getStartDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getToHelperFeedback());
        query = Replacer.replaceFirst(query, post.getToKiuerFeedback());
        query = Replacer.replaceFirst(query, post.getKiuer().getId());
        query = Replacer.replaceFirst(query, post.getHelper().getId());
        query = Replacer.replaceFirst(query, post.getPlace().getId());
        query = Replacer.replaceFirst(query, post.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(PostKiuer post) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, post.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public List<PostKiuer> getAllBy(String flag, Object key){
        List<PostKiuer> postList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.KIUER)){
            query = SELECT_FROM_KIUER;
            query = Replacer.replaceFirst(query, (String) key);
        } else if(flag.equals(FilterControl.HELPER)){
            query = SELECT_FROM_HELPER;
            query = Replacer.replaceFirst(query, (String) key);
        } else if(flag.equals(FilterControl.CITY)){
            query = SELECT_FROM_CITY;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                postList.add(setPostKiuer(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return postList;
    }
}
