package integration.dao;

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
public class KiuerDAO{
    private static final String SELECT = "SELECT `id`, `email`,`username`, `password`, `favorite_city` FROM kiuer";
    private static final String SELECT_FROM_ID = "SELECT `id`, `email`, `username`, `password`, `favorite_city` FROM kiuer WHERE `id`=?";
    private static final String INSERT = "INSERT INTO kiuer (email, username, password, favorite_city) VALUES ('?','?','?','?')";
    private static final String UPDATE = "UPDATE kiuer SET email='?', username='?', password='?', favorite_city='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM kiuer WHERE id=?";
    private static final String SELECT_FROM_USERNAME = "SELECT `id`, `email`, `username`, `password`, `favorite_city` FROM kiuer WHERE `username`=\"?\"";
    private static final Logger logger = Logger.getLogger(KiuerDAO.class.getName());

    public KiuerDAO(){}

    private Kiuer setKiuer(ResultSet rs) throws SQLException {
        Kiuer kiuer = new Kiuer();
        kiuer.setId(rs.getInt(1));
        kiuer.setEmail(rs.getString(2));
        kiuer.setUsername(rs.getString(3));
        kiuer.setPassword(rs.getString(4));
        kiuer.setFavoriteCity(rs.getString(5));
        return kiuer;
    }

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

    public void create(Kiuer kiuer) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, kiuer.getEmail());
        query = Replacer.replaceFirst(query, kiuer.getUsername());
        query = Replacer.replaceFirst(query, kiuer.getPassword());
        query = Replacer.replaceFirst(query, kiuer.getFavoriteCity());
        kiuer.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(Kiuer kiuer) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, kiuer.getEmail());
        query = Replacer.replaceFirst(query, kiuer.getUsername());
        query = Replacer.replaceFirst(query, kiuer.getPassword());
        query = Replacer.replaceFirst(query, kiuer.getFavoriteCity());
        query = Replacer.replaceFirst(query, kiuer.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(Kiuer kiuer) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, kiuer.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

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
