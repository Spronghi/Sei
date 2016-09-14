package com.spronghi.kiu.request;

import android.content.res.Resources;

import com.spronghi.kiu.R;
import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by spronghi on 13/09/16.
 */
public class KiuerRequest {
    private boolean seen;
    private Kiuer addressee;
    private Helper sender;
    private PostKiuer post;
    private String type;

    private KiuerRequest(){}

    public KiuerRequest(Helper sender, Kiuer addressee, PostKiuer post, String type){
        seen = false;
        this.addressee = addressee;
        this.sender = sender;
        this.post = post;
        this.type = type;
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
        switch(type){
            case Request.SEND:
                return "An Helper sent you a request!";
            case Request.ACCEPT:
                return "An Helper accepted your request!";
            case Request.REFUSE:
                return "An Helped refused your request!";
        }
        return "";
    }
}
