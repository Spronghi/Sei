package com.spronghi.kiu.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestChecker {
    private List<ToKiuerRequest> toKiuerRequestList;
    private List<ToHelperRequest> toHelperRequestList;

    public RequestChecker(){
        toKiuerRequestList = new ArrayList<>();
        toHelperRequestList = new ArrayList<>();
    }
    public List<ToKiuerRequest> checkForKiuerRequest(){
        // TO DO correct populate
        populateKiuerList();
        return toKiuerRequestList;
    }

    public List<ToHelperRequest> checkForHelperRequest(){
        // TO DO correct populate
        populateHelperList();
        return toHelperRequestList;
    }

    private void populateKiuerList(){
        //ToKiuerRequest kiuerRequest = new ToKiuerRequest(CurrentUser.getHelper(), CurrentUser.getKiuer(), CurrentPost.getPostKiuer(), Request.SEND);
        //toKiuerRequestList.add(kiuerRequest);
    }
    private void populateHelperList(){
        //ToHelperRequest helperRequest = new ToHelperRequest(CurrentUser.getKiuer(), CurrentUser.getHelper(), CurrentPost.getPostKiuer(), Request.SEND);
        //toHelperRequestList.add(helperRequest);
    }
}
