package integration.dao;

import exception.FlagNotFoundException;
import integration.control.FilterControl;
import integration.database.ConnectorFactory;
import integration.util.Replacer;
import model.Kiuer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuerDAO implements FilterDAO<Kiuer> {
    private static final String SELECT = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status` FROM kiuer";
    private static final String SELECT_FROM_ID = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status` FROM kiuer WHERE `id`=?";
    private static final String INSERT = "INSERT INTO kiuer (name, surname, birth, telephone, username, password, feedback, favorite_city,profile_status) VALUES ('?','?','?','?','?','?',?,'?','?')";
    private static final String UPDATE = "UPDATE kiuer SET name='?', surname='?', birth='?', telephone='?', username='?', password='?', feedback=?, favorite_city='?',profile_status='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM kiuer WHERE id=?";
    private static final String SELECT_FROM_USERNAME = "SELECT `id`, `name`, `surname`, `birth`, `telephone`, `username`, `password`, `feedback`, `favorite_city`,`profile_status` FROM kiuer WHERE `username`=\"?\"";
    private static final Logger logger = Logger.getLogger(KiuerDAO.class.getName());

    public KiuerDAO(){}

    private Kiuer setKiuer(ResultSet rs) throws SQLException {
        Kiuer kiuer = new Kiuer();
        kiuer.setId(rs.getInt(1));
        kiuer.setName(rs.getString(2));
        kiuer.setSurname(rs.getString(3));
        kiuer.setBirth(rs.getDate(4).toLocalDate());
        kiuer.setTelephone(rs.getString(5));
        kiuer.setUsername(rs.getString(6));
        kiuer.setPassword(rs.getString(7));
        kiuer.setFeedback(rs.getBigDecimal(8).floatValue());
        kiuer.setFavoriteCity(rs.getString(9));
        kiuer.setProfileStatus(rs.getString(10));
        return kiuer;
    }

    @Override
    public Kiuer get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setKiuer(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    @Override
    public List<Kiuer> getAll() {
        List<Kiuer> kiuers = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                kiuers.add(setKiuer(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return kiuers;
    }

    @Override
    public void create(Kiuer kiuer) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, kiuer.getName());
        query = Replacer.replaceFirst(query, kiuer.getSurname());
        query = Replacer.replaceFirst(query, kiuer.getBirth(), "yyyy-MM-dd");
        query = Replacer.replaceFirst(query, kiuer.getTelephone());
        query = Replacer.replaceFirst(query, kiuer.getUsername());
        query = Replacer.replaceFirst(query, kiuer.getPassword());
        query = Replacer.replaceFirst(query, kiuer.getFeedback());
        query = Replacer.replaceFirst(query, kiuer.getFavoriteCity());
        query = Replacer.replaceFirst(query, kiuer.getProfileStatus());
        kiuer.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    @Override
    public void update(Kiuer kiuer) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, kiuer.getName());
        query = Replacer.replaceFirst(query, kiuer.getSurname());
        query = Replacer.replaceFirst(query, kiuer.getBirth(), "yyyy-MM-dd");
        query = Replacer.replaceFirst(query, kiuer.getTelephone());
        query = Replacer.replaceFirst(query, kiuer.getUsername());
        query = Replacer.replaceFirst(query, kiuer.getPassword());
        query = Replacer.replaceFirst(query, kiuer.getFeedback());
        query = Replacer.replaceFirst(query, kiuer.getFavoriteCity());
        query = Replacer.replaceFirst(query, kiuer.getProfileStatus());
        query = Replacer.replaceFirst(query, kiuer.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public void delete(Kiuer kiuer) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, kiuer.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    @Override
    public List<Kiuer> getAllBy(String flag, Object key){
        List<Kiuer> kiuerList = new ArrayList<>();
        String query="";
        if(flag.equals(FilterControl.USERNAME)){
            query = SELECT_FROM_USERNAME;
            query = Replacer.replaceFirst(query, ((String) key).toLowerCase());
        }
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);

        try{
            while(rs.next()) {
                kiuerList.add(setKiuer(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return kiuerList;
    }
}
