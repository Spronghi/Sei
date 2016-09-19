package integration.dao;

import exception.FlagNotFoundException;
import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.PostHelper;
import model.PostHelper;
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
public class PostHelperDAO {
    private static final String SELECT = "SELECT `id`, `open`, `title`, `city`, `cost`, `start`, `end`, `helper_id` FROM post_helper";
    private static final String SELECT_FROM_ID = "SELECT `id`, `open`, `title`, `city`, `cost`, `start`, `end`, `helper_id` FROM post_helper WHERE `id`=?";
    private static final String INSERT = "INSERT INTO post_helper (open, title, city, cost, start, end, helper_id) VALUES (?,'?','?',?,'?','?',?)";
    private static final String UPDATE = "UPDATE post_helper SET open=?, title='?', city='?', cost=?, start='?', end='?',helper_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM post_helper WHERE id=?";
    private static final String SELECT_FROM_HELPER = "SELECT `id`, `open`, `title`, `city`, `cost`, `start`, `end`, `helper_id` FROM post_helper WHERE `helper_id`=?";
    private static final Logger logger = Logger.getLogger(PostHelperDAO.class.getName());

    private HelperDAO helperDAO;

    public PostHelperDAO(){
        helperDAO = new HelperDAO();
    }

    private PostHelper setPostHelper(ResultSet rs) throws SQLException {
        PostHelper post = new PostHelper();
        post.setId(rs.getInt(1));
        post.setOpen(rs.getBoolean(2));
        post.setTitle(rs.getString(3));
        post.setCity(rs.getString(4));
        post.setCost(rs.getBigDecimal(5).doubleValue());
        post.setStartDate(DateFormatter.toLocalDateTime(rs.getDate(6).toLocalDate(), rs.getTime(6).toLocalTime()));
        post.setEndDate(DateFormatter.toLocalDateTime(rs.getDate(7).toLocalDate(), rs.getTime(7).toLocalTime()));
        post.setHelper(helperDAO.get(rs.getInt(8)));
        return post;
    }

    public PostHelper get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setPostHelper(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    public List<PostHelper> getAll() {
        List<PostHelper> posts = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                posts.add(setPostHelper(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return posts;
    }

    public void create(PostHelper post) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, post.isOpen());
        query = Replacer.replaceFirst(query, post.getTitle());
        query = Replacer.replaceFirst(query, post.getCity());
        query = Replacer.replaceFirst(query, post.getCost());
        query = Replacer.replaceFirst(query, post.getStartDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getEndDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getHelper().getId());
        post.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(PostHelper post) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, post.isOpen());
        query = Replacer.replaceFirst(query, post.getTitle());
        query = Replacer.replaceFirst(query, post.getCity());
        query = Replacer.replaceFirst(query, post.getCost());
        query = Replacer.replaceFirst(query, post.getStartDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getEndDate(), "yyyy-MM-dd HH:mm");
        query = Replacer.replaceFirst(query, post.getHelper().getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(PostHelper post) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, post.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public List<PostHelper> getAllBy(String flag, Object key){
        List<PostHelper> postList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.HELPER)){
            query = SELECT_FROM_HELPER;
            query = Replacer.replaceFirst(query, (String) key);
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                postList.add(setPostHelper(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return postList;
    }
}
