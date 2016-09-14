package com.spronghi.kiu.request;

import com.spronghi.kiu.runtime.CurrentPost;
import com.spronghi.kiu.runtime.CurrentUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestChecker {
    private List<KiuerRequest> kiuerRequestList;
    private List<HelperRequest> helperRequestList;

    public RequestChecker(){
        kiuerRequestList = new ArrayList<>();
        helperRequestList = new ArrayList<>();
    }
    public List<KiuerRequest> checkForKiuerRequest(){
        // TO DO correct populate
        populateKiuerList();
        return kiuerRequestList;
    }

    public List<HelperRequest> checkForHelperRequest(){
        // TO DO correct populate
        populateHelperList();
        return helperRequestList;
    }

    private void populateKiuerList(){
        KiuerRequest kiuerRequest = new KiuerRequest(CurrentUser.getHelper(), CurrentUser.getKiuer(), CurrentPost.getPostKiuer(), Request.SEND);
        kiuerRequestList.add(kiuerRequest);
    }
    private void populateHelperList(){
        HelperRequest helperRequest = new HelperRequest(CurrentUser.getKiuer(), CurrentUser.getHelper(), CurrentPost.getPostKiuer(), Request.SEND);
        helperRequestList.add(helperRequest);
    }
}
