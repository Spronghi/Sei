package model;

/**
 * Created by spronghi on 15/09/16.
 */
public class RequestType {
    private int id;
    private String type;

    public RequestType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
