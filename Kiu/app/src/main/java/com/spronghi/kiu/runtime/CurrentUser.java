package com.spronghi.kiu.runtime;

import com.spronghi.kiu.model.Helper;
import com.spronghi.kiu.model.Kiuer;

/**
 * Created by spronghi on 08/09/16.
 */
public class CurrentUser {
    private static Helper helper;
    private static Kiuer kiuer;
    private static boolean isKiuer;
    public static Helper getHelper() {
        return helper;
    }

    public static void setHelper(Helper helper) {
        CurrentUser.helper = helper;
    }

    public static Kiuer getKiuer() {
        return kiuer;
    }

    public static void setKiuer(Kiuer kiuer) {
        CurrentUser.kiuer = kiuer;
    }

    public static boolean isKiuer() {
        return isKiuer;
    }

    public static void setIsKiuer(boolean isKiuer) {
        CurrentUser.isKiuer = isKiuer;
    }
}
