package model;

/**
 * Created by spronghi on 15/09/16.
 */
public class ToHelperRequest {
    private int id;
    private boolean seen;
    private Helper addressee;
    private Kiuer sender;
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

    public Helper getAddressee() {
        return addressee;
    }

    public void setAddressee(Helper addressee) {
        this.addressee = addressee;
    }

    public Kiuer getSender() {
        return sender;
    }

    public void setSender(Kiuer sender) {
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
}
