package integration.dao;

import exception.FlagNotFoundException;
import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 14/09/16.
 */
public class HelperDAO implements FilterDAO<Helper>{
    private static final String SELECT = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status`,`profile_cost` FROM helper";
    private static final String SELECT_FROM_ID = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status`,`profile_cost` FROM helper WHERE `id`=?";
    private static final String INSERT = "INSERT INTO helper (name, surname, birth, telephone, username, password, feedback, favorite_city, profile_status,profile_cost) VALUES ('?','?','?','?','?','?',?,'?','?',?)";
    private static final String UPDATE = "UPDATE helper SET name='?', surname='?', birth='?', telephone='?', username='?', password='?', feedback=?, favorite_city='?',profile_status='?', profile_cost=? WHERE id=?";
    private static final String DELETE = "DELETE FROM helper WHERE id=?";
    private static final String SELECT_FROM_USERNAME = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status`,`profile_cost` FROM helper WHERE `username`=\"?\"";
    private static final Logger logger = Logger.getLogger(HelperDAO.class.getName());

    public HelperDAO(){}

    private Helper setHelper(ResultSet rs) throws SQLException {
        Helper helper = new Helper();
        helper.setId(rs.getInt(1));
        helper.setName(rs.getString(2));
        helper.setSurname(rs.getString(3));
        helper.setBirth(rs.getDate(4).toLocalDate());
        helper.setTelephone(rs.getString(5));
        helper.setUsername(rs.getString(6));
        helper.setPassword(rs.getString(7));
        helper.setFeedback(rs.getBigDecimal(8).floatValue());
        helper.setFavoriteCity(rs.getString(9));
        helper.setProfileStatus(rs.getString(10));
        helper.setFavoriteCost(rs.getBigDecimal(11).doubleValue());
        return helper;
    }

    @Override
    public Helper get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setHelper(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<Helper> getAll() {
        List<Helper> helpers = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                helpers.add(setHelper(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return helpers;
    }

    @Override
    public void create(Helper helper) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, helper.getName());
        query = Replacer.replaceFirst(query, helper.getSurname());
        query = Replacer.replaceFirst(query, helper.getBirth(), "yyyy-MM-dd");
        query = Replacer.replaceFirst(query, helper.getTelephone());
        query = Replacer.replaceFirst(query, helper.getUsername());
        query = Replacer.replaceFirst(query, helper.getPassword());
        query = Replacer.replaceFirst(query, helper.getFeedback());
        query = Replacer.replaceFirst(query, helper.getFavoriteCity());
        query = Replacer.replaceFirst(query, helper.getProfileStatus());
        query = Replacer.replaceFirst(query, helper.getFavoriteCost());
        helper.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    @Override
    public void update(Helper helper) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, helper.getName());
        query = Replacer.replaceFirst(query, helper.getSurname());
        query = Replacer.replaceFirst(query, helper.getBirth(), "yyyy-MM-dd");
        query = Replacer.replaceFirst(query, helper.getTelephone());
        query = Replacer.replaceFirst(query, helper.getUsername());
        query = Replacer.replaceFirst(query, helper.getPassword());
        query = Replacer.replaceFirst(query, helper.getFeedback());
        query = Replacer.replaceFirst(query, helper.getFavoriteCity());
        query = Replacer.replaceFirst(query, helper.getFavoriteCost());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(Helper helper) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, helper.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public List<Helper> getAllBy(String flag, Object key){
        List<Helper> helperList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.USERNAME)){
            query = SELECT_FROM_USERNAME;
            query = Replacer.replaceFirst(query, ((String) key).toLowerCase());
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                helperList.add(setHelper(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return helperList;
    }
}
