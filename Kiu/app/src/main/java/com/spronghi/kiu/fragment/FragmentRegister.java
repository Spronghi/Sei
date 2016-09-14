package com.spronghi.kiu.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.spronghi.kiu.R;
import com.spronghi.kiu.exception.KeyNotFoundException;
import com.spronghi.kiu.fragment.create_post_helper.CreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_helper.EndCreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_helper.StartCreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.CostCreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.CreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.PlaceCreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.StartCreatePostKiuerFragment;

import java.util.HashMap;
import java.util.Map;

class FragmentRegister extends FragmentControl{
    static private Map<String, ModelFragment> container;
    static {
        container = new HashMap<>();
        try {
            container.put(VIEW_KIUER, new ViewKiuerFragment().clone());
            container.put(EDIT_KIUER, new EditKiuerFragment().clone());
            container.put(VIEW_HELPER, new ViewHelperFragment().clone());
            container.put(EDIT_HELPER, new EditHelperFragment().clone());
            container.put(VIEW_POST_KIUER, new ViewPostKiuerFragment().clone());
            container.put(VIEW_POST_HELPER, new ViewPostHelperFragment().clone());
            container.put(CREATE_POST_KIUER, new CreatePostKiuerFragment().clone());
            container.put(CREATE_POST_KIUER_PLACE, new PlaceCreatePostKiuerFragment().clone());
            container.put(CREATE_POST_KIUER_COST, new CostCreatePostKiuerFragment().clone());
            container.put(CREATE_POST_KIUER_START, new StartCreatePostKiuerFragment().clone());
            container.put(CREATE_POST_HELPER, new CreatePostHelperFragment().clone());
            container.put(CREATE_POST_HELPER_START, new StartCreatePostHelperFragment().clone());
            container.put(CREATE_POST_HELPER_END, new EndCreatePostHelperFragment().clone());
            container.put(LIST_POST_KIUER, new ListPostKiuerFragment().clone());
            container.put(LIST_POST_HELPER, new ListPostHelperFragment().clone());
            container.put(LIST_REQUEST_KIUER, new ListRequestKiuerFragment().clone());
            container.put(LIST_REQUEST_HELPER, new ListRequestHelperFragment().clone());
            container.put(VIEW_REQUEST_KIUER, new RequestKiuerFragment().clone());
            container.put(VIEW_REQUEST_HELPER, new RequestHelperFragment().clone());
            container.put(SEND_REQUEST_HELPER, new SendRequestHelperFragment().clone());
            container.put(SEND_REQUEST_KIUER, new SendRequestKiuerFragment().clone());
            container.put(KIUING_KIUER, new KiuingKiuerFragment().clone());
        } catch (CloneNotSupportedException e) {
            Log.d("FragmentRegister", e.getLocalizedMessage());
        }

    }
    static public <T> ModelFragment<T> getFragment(String key) {
        return (ModelFragment<T>) container.get(key);
    }
}
