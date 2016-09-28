package com.spronghi.kiu.request;

import com.spronghi.kiu.http.ToHelperRequestService;
import com.spronghi.kiu.http.ToKiuerRequestService;
import com.spronghi.kiu.runtime.CurrentUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spronghi on 13/09/16.
 */
public class RequestChecker {
    private List<ToKiuerRequest> toKiuerRequests;
    private List<ToHelperRequest> toHelperRequests;

    public RequestChecker(){
        toHelperRequests = new ArrayList<>();
        toKiuerRequests = new ArrayList<>();
    }
    public List<ToKiuerRequest> checkForKiuerRequest(){
        toKiuerRequests.clear();
        for(ToKiuerRequest request : ToKiuerRequestService.getAllByAddressee(CurrentUser.getKiuer())){
            if(!(request.isSeen())){
                toKiuerRequests.add(request);
            }
        }
        return toKiuerRequests;
    }

    public List<ToHelperRequest> checkForHelperRequest(){
        for(ToHelperRequest request : ToHelperRequestService.getAllByAddressee(CurrentUser.getHelper())){
            if(!(request.isSeen())){
                toHelperRequests.add(request);
            }
        }
        return toHelperRequests;
    }
}
