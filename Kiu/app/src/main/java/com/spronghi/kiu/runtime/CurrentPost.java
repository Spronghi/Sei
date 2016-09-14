package com.spronghi.kiu.runtime;

import com.spronghi.kiu.model.Place;
import com.spronghi.kiu.model.PostHelper;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by spronghi on 08/09/16.
 */
public class CurrentPost {
    private static PostKiuer postKiuer;
    private static PostHelper postHelper;
    static {
        Place place = new Place();
        place.setCity("Lecce");
        place.setAddress("Via Don Bosco");
        place.setLocation("Pizzeria Sto Cazzo");

        postKiuer = new PostKiuer();
        postKiuer.setTitle("The first postKiuer");
        postKiuer.setStatus("This is the first postKiuer in this app!!");
        postKiuer.setHelper(CurrentUser.getHelper());
        postKiuer.setKiuer(CurrentUser.getKiuer());
        postKiuer.setStartDate("08/10/2016 12:48");
        postKiuer.setDuration(50);
        postKiuer.setCost(10);
        postKiuer.setOpen(true);
        postKiuer.setPlace(place);

        postHelper = new PostHelper();
        postHelper.setCost(10);
        postHelper.setStartDate("06/10/2016 12:48");
        postHelper.setEndDate("08/10/2016 12:48");
        postHelper.setHelper(CurrentUser.getHelper());
        postHelper.setCity("Lecce");
        postHelper.setTitle("Sucate Forte");
    }
    public static PostKiuer getPostKiuer(){
        return postKiuer;
    }
    public static PostHelper getPostHelper(){
        return postHelper;
    }
}
