package integration.dao;

import model.Place;
import integration.database.ConnectorFactory;
import integration.util.Replacer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by spronghi on 04/07/16.
 */
public class PlaceDAO {
    private static final String SELECT = "SELECT `id`, `city`, `address`, `location` FROM place";
    private static final String SELECT_FROM_ID = "SELECT `id`, `city`, `address`, `location` FROM place WHERE `id`=?";
    private static final String INSERT = "INSERT INTO place (city, address, location) VALUES ('?','?','?')";
    private static final String UPDATE = "UPDATE place SET city='?', address='?', location='?' WHERE id=?";
    private static final String DELETE = "DELETE FROM place WHERE id=?";

    private static final Logger logger = Logger.getLogger(PlaceDAO.class.getName());

    public PlaceDAO(){}

    private Place setPlace(ResultSet rs) throws SQLException {
        Place place = new Place();
        place.setId(rs.getInt(1));
        place.setCity(rs.getString(2));
        place.setAddress(rs.getString(3));
        place.setLocation(rs.getString(4));
        return place;
    }

    public Place get(int id) {
        String query = SELECT_FROM_ID;
        query = Replacer.replaceFirst(query, id);
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(query);
        try{
            if (rs.next()) {
                return setPlace(rs);
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    public List<Place> getAll() {
        List<Place> agencies = new ArrayList<>();
        ResultSet rs = ConnectorFactory.getConnection().executeQuery(SELECT);
        try{
            while(rs.next()) {
                agencies.add(setPlace(rs));
            }
        } catch(SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
        return agencies;
    }

    public void create(Place place) {
        String query = INSERT;
        query = Replacer.replaceFirst(query, place.getCity());
        query = Replacer.replaceFirst(query, place.getAddress());
        query = Replacer.replaceFirst(query, place.getLocation());
        place.setId(ConnectorFactory.getConnection().executeUpdate(query));
    }

    public void update(Place place) {
        String query = UPDATE;
        query = Replacer.replaceFirst(query, place.getCity());
        query = Replacer.replaceFirst(query, place.getAddress());
        query = Replacer.replaceFirst(query, place.getLocation());
        query = Replacer.replaceFirst(query, place.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }

    public void delete(Place place) {
        String query = DELETE;
        query = Replacer.replaceFirst(query, place.getId());
        ConnectorFactory.getConnection().executeUpdate(query);
    }
}
