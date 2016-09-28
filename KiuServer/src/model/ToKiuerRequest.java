package model;

/**
 * Created by spronghi on 15/09/16.
 */
public class ToKiuerRequest {
    private int id;
    private boolean seen;
    private Kiuer addressee;
    private Helper sender;
    private PostKiuer post;
    private RequestType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Kiuer getAddressee() {
        return addressee;
    }

    public void setAddressee(Kiuer addressee) {
        this.addressee = addressee;
    }

    public Helper getSender() {
        return sender;
    }

    public void setSender(Helper sender) {
        this.sender = sender;
    }

    public PostKiuer getPost() {
        return post;
    }

    public void setPost(PostKiuer post) {
        this.post = post;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ToKiuerRequest{" +
                "id=" + id +
                ", seen=" + seen +
                ", addressee=" + addressee +
                ", sender=" + sender +
                ", post=" + post +
                ", type=" + type +
                '}';
    }
}
