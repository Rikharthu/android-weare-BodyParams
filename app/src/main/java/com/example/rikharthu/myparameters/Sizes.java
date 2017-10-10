package com.example.rikharthu.myparameters;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Sizes {

    public static final int MALE = 1;
    public static final int FEMALE = 2;

    public static final int XS = 1001;
    public static final int S = 1002;
    public static final int M = 1003;
    public static final int L = 1004;
    public static final int XL = 1005;
    public static final int XXL = 1006;
    public static final int XXXL = 1007;
    public static final int S_M = 1008;
    public static final int M_L = 1009;
    public static final int L_XL = 1010;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({XS, S, M, L, XL, XXL, XXXL})
    public @interface BodySize {

    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MALE, FEMALE})
    public @interface Gender {
    }
}
