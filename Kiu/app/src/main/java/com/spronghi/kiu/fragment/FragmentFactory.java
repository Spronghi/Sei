package com.spronghi.kiu.fragment;

public class FragmentFactory {
    public static <T> ModelFragment<T> getInstance(String key){
        return (ModelFragment<T>) FragmentRegister.<T>getFragment(key);
    }
}
