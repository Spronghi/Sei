package com.spronghi.kiu.fragment;

import android.support.v4.app.Fragment;


public abstract class ModelFragment<T> extends Fragment implements Cloneable {
    public abstract void setModel(T model);

    @Override
    public ModelFragment<T> clone() throws CloneNotSupportedException {
        return (ModelFragment<T>) super.clone();
    }
}
