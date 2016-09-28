package com.spronghi.kiu.kiuing;

import com.spronghi.kiu.http.KiuingService;
import com.spronghi.kiu.model.PostKiuer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 14/09/16.
 */
public class Kiuing {
    public static final String GOING = "going";
    public static final String ARRIVED = "arrived";
    public static final String FINISHED = "finished";
    public static final String KIUING = "kiuing";

    private int id;
    private PostKiuer post;
    private List<KiuingOperation> operationList;

    public Kiuing() {}
    public Kiuing(PostKiuer post) {
        this.post = post;
        operationList = new ArrayList<>();
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
            if(operation.getOperation().equals(GOING))
                operation.setDone(true);
        }
        KiuingService.update(this);
    }

    public void setArrivedTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals(ARRIVED))
                operation.setDone(true);
        }
        KiuingService.update(this);
    }

    public void setIsKiuingTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals(KIUING))
                operation.setDone(true);
        }
        KiuingService.update(this);
    }

    public void setIsFinishedTrue() {
        for(KiuingOperation operation : operationList){
            if(operation.getOperation().equals(FINISHED))
                operation.setDone(true);
        }
        KiuingService.update(this);
    }

    @Override
    public String toString() {
        return "Kiuing{" +
                "id=" + id +
                ", post=" + post +
                ", operationList=" + operationList +
                '}';
    }
}