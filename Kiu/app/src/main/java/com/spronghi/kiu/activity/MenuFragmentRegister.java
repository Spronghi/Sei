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
        //container.put(R.id.id_kiuer_drawer_menu_icon_helper, LIST_POST_HELPER);
        container.put(R.id.id_kiuer_drawer_menu_icon_requests, LIST_REQUEST_KIUER);
        container.put(R.id.id_kiuer_drawer_menu_icon_posts, LIST_USER_KIUER);
        container.put(R.id.id_kiuer_drawer_menu_icon_kiuing, LIST_KIUING);

        container.put(R.id.id_helper_drawer_menu_icon_kiuer, LIST_POST_KIUER);
        //container.put(R.id.id_helper_drawer_menu_icon_helper, LIST_POST_HELPER);
        container.put(R.id.id_helper_drawer_menu_icon_requests, LIST_REQUEST_HELPER);
        container.put(R.id.id_helper_drawer_menu_icon_posts, LIST_USER_HELPER);
        container.put(R.id.id_helper_drawer_menu_icon_kiuing, LIST_KIUING);
    }
    static public String getKey(int id) {
        return container.get(id);
    }
}
