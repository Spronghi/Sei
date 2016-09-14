package com.spronghi.kiu.kiuing;

import com.spronghi.kiu.model.PostKiuer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spronghi on 14/09/16.
 */
public class Kiuing {
    private PostKiuer post;
    private List<String> operationList;
    private Map<String, Boolean> kiuingMap;

    public Kiuing(PostKiuer post) {
        this.post = post;

        kiuingMap = new HashMap<>();
        kiuingMap.put(post.getHelper().getUsername() + " is coming", false);
        kiuingMap.put(post.getHelper().getUsername() + " arrived", false);
        kiuingMap.put(post.getHelper().getUsername() + " is Kiuing", false);
        kiuingMap.put(post.getHelper().getUsername() + " finished the kiu", false);

        operationList = new ArrayList<>();
        operationList.add(post.getHelper().getUsername() + " is coming");
        operationList.add(post.getHelper().getUsername() + " arrived");
        operationList.add(post.getHelper().getUsername() + " is Kiuing");
        operationList.add(post.getHelper().getUsername() + " finished the kiu");



    }

    public void checkForOperations() {
        //TO DO check for operations
    }

    public Map<String, Boolean> getKiuingMap() {
        return kiuingMap;
    }

    public PostKiuer getPost() {
        return post;
    }

    public void setPost(PostKiuer post) {
        this.post = post;
    }

    public List<String> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<String> operationList) {
        this.operationList = operationList;
    }

    public void setComingTrue() {
        kiuingMap.put(post.getHelper().getUsername() + " is coming", true);
    }

    public void setArrivedTrue() {
        kiuingMap.put(post.getHelper().getUsername() + " arrived", true);
    }

    public void setIsKiuingTrue() {
        kiuingMap.put(post.getHelper().getUsername() + " is Kiuing", true);
    }

    public void setIsFinishedTrue() {
        kiuingMap.put(post.getHelper().getUsername() + " finished the kiu", true);
    }
}