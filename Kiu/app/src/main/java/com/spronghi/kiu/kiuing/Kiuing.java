package com.spronghi.kiu.kiuing;

import com.spronghi.kiu.model.PostKiuer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 14/09/16.
 */
public class Kiuing {
    private int id;
    private PostKiuer post;
    private List<KiuingOperation> operationList;

    public Kiuing() {}
    public Kiuing(PostKiuer post) {
        this.post = post;
        operationList = new ArrayList<>();
    }

    public void checkForOperations() {
        //TO DO check for operations
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KiuingOperation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<KiuingOperation> operationList) {
        this.operationList = operationList;
    }

    public PostKiuer getPost() {
        return post;
    }

    public void setPost(PostKiuer post) {
        this.post = post;
    }


    public void setComingTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals("is coming"))
                operation.setDone(true);
        }
    }

    public void setArrivedTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals("arrived"))
                operation.setDone(true);
        }
    }

    public void setIsKiuingTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals("is Kiuing"))
                operation.setDone(true);
        }
    }

    public void setIsFinishedTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals("finished the kiu"))
                operation.setDone(true);
        }
    }
}