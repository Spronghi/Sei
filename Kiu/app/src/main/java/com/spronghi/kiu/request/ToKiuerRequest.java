package com.spronghi.kiu.request;

import android.util.Log;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by spronghi on 13/09/16.
 */
public class ToKiuerRequest {
    private int id;
    private boolean seen;
    private Kiuer addressee;
    private Helper sender;
    private PostKiuer post;
    private String type;

    private ToKiuerRequest(){
        seen = false;
    }

    public ToKiuerRequest(Helper sender, Kiuer addressee, PostKiuer post, String type){
        seen = false;
        this.addressee = addressee;
        this.sender = sender;
        this.post = post;
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage(){
        Log.d("request", type);
        if(type.contains(Request.SEND))
            return "An Helper sent you a request!";
        else if(type.contains(Request.ACCEPT))
            return "An Helper accepted your request!";
        else if(type.contains(Request.REFUSE))
            return "An Helped refused your request!";
        else
            return "No Request type found";
    }

    @Override
    public String toString() {
        return "ToKiuerRequest{" +
                "id=" + id +
                ", seen=" + seen +
                ", addressee=" + addressee +
                ", sender=" + sender +
                ", post=" + post +
                ", type='" + type + '\'' +
                '}';
    }
}
