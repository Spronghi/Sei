package com.spronghi.kiu.fragment;

import android.util.Log;

import com.spronghi.kiu.R;
import com.spronghi.kiu.fragment.create_post_helper.CreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_helper.EndCreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_helper.StartCreatePostHelperFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.CostCreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.CreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.PlaceCreatePostKiuerFragment;
import com.spronghi.kiu.fragment.create_post_kiuer.StartCreatePostKiuerFragment;

import java.util.HashMap;
import java.util.Map;

class FragmentRegister {
    static private Map<String, ModelFragment> container;
    static {
        container = new HashMap<>();
        try {
            container.put("ViewKiuerFragment", new ViewKiuerFragment().clone());
            container.put("EditKiuerFragment", new EditKiuerFragment().clone());
            container.put("ViewHelperFragment", new ViewHelperFragment().clone());
            container.put("EditHelperFragment", new EditHelperFragment().clone());
            container.put("ViewPostKiuerFragment", new ViewPostKiuerFragment().clone());
            container.put("ViewPostHelperFragment", new ViewPostHelperFragment().clone());
            container.put("CreatePostKiuerFragment", new CreatePostKiuerFragment().clone());
            container.put("PlaceCreatePostKiuerFragment", new PlaceCreatePostKiuerFragment().clone());
            container.put("CostCreatePostKiuerFragment", new CostCreatePostKiuerFragment().clone());
            container.put("StartCreatePostKiuerFragment", new StartCreatePostKiuerFragment().clone());
            container.put("CreatePostHelperFragment", new CreatePostHelperFragment().clone());
            container.put("StartCreatePostHelperFragment", new StartCreatePostHelperFragment().clone());
            container.put("StartCreatePostHelperFragment", new StartCreatePostHelperFragment().clone());
            container.put("ListPostKiuerFragment", new ListPostKiuerFragment().clone());
            container.put("ListPostHelperFragment", new ListPostHelperFragment().clone());
        } catch (CloneNotSupportedException e) {
            Log.d("FragmentRegister", e.getLocalizedMessage());
        }

    }
    static public <T> ModelFragment<T> getFragment(String key){
        return (ModelFragment<T>) container.get(key);
    }
}
