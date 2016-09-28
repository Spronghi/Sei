package com.spronghi.kiu.request;

import android.util.Log;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by spronghi on 13/09/16.
 */
public class ToHelperRequest {
    private int id;
    private boolean seen;
    private Helper addressee;
    private Kiuer sender;
    private PostKiuer post;
    private String type;

    private ToHelperRequest(){
        seen = false;
    }

    public ToHelperRequest(Kiuer sender, Helper addressee, PostKiuer post, String type){
        this.id= 0;
        this.seen = false;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage(){
        Log.d("request", type);
        if(type.contains(Request.SEND))
            return "A Kiuer sent you a request!";
        else if(type.contains(Request.ACCEPT))
            return "A Kiuer accepted your request!";
        else if(type.contains(Request.REFUSE))
            return "A Kiuer refused your request!";
        else
            return "No Request type found";
    }
}
