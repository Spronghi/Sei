package com.spronghi.kiu.activity;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.FragmentControl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spronghi on 12/09/16.
 */
public class MenuFragmentRegister extends FragmentControl {
    static private Map<Integer, String> container;
    static {
        container = new HashMap<>();

        container.put(R.id.id_kiuer_drawer_menu_icon_kiuer, LIST_POST_KIUER);
        container.put(R.id.id_kiuer_drawer_menu_icon_helper, LIST_POST_HELPER);
        container.put(R.id.id_kiuer_drawer_menu_icon_requests, LIST_REQUEST_KIUER);
        //TO DO container.put(R.id.id_kiuer_drawer_menu_icon_friends, );
        container.put(R.id.id_kiuer_drawer_menu_icon_kiuing, KIUING_KIUER);

        container.put(R.id.id_helper_drawer_menu_icon_kiuer, LIST_POST_KIUER);
        container.put(R.id.id_helper_drawer_menu_icon_helper, LIST_POST_HELPER);
        container.put(R.id.id_helper_drawer_menu_icon_requests, LIST_REQUEST_HELPER);
        //TO DO container.put(R.id.id_helper_drawer_menu_icon_friends, );
        //TO DO container.put(R.id.id_helper_drawer_menu_icon_kiuing, );
    }
    static public String getKey(int id) {
        return container.get(id);
    }
}
