package com.spronghi.kiu.runtime;

import com.spronghi.kiu.model.Place;
import com.spronghi.kiu.model.PostKiuer;

/**
 * Created by spronghi on 08/09/16.
 */
public class CurrentPost {
    private static PostKiuer postKiuer;
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
    }
    public static PostKiuer get(){
        return postKiuer;
    }
}
