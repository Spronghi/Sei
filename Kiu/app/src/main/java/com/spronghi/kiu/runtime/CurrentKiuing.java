package com.spronghi.kiu.runtime;

import com.spronghi.kiu.kiuing.Kiuing;

/**
 * Created by spronghi on 14/09/16.
 */
public class CurrentKiuing {
    private static Kiuing kiuing;
    static{
        kiuing = new Kiuing(CurrentPost.getPostKiuer());
    }
    public static Kiuing getKiuing() {
        return kiuing;
    }

    public static void setKiuing(Kiuing kiuing) {
        CurrentKiuing.kiuing = kiuing;
    }
}
