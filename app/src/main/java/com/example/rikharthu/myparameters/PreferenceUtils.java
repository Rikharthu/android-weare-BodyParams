package com.example.rikharthu.myparameters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    public static final String FIRST_LAUNCH = "first_launch";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_BODY_SIZE = "body_size";
    public static final String KEY_BRA_SIZE = "bra_size";
    public static final String KEY_FOOT_SIZE = "foot_size";

    private SharedPreferences mPreferences;
    private static PreferenceUtils sInstance;

    private PreferenceUtils(Context ctx) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(ctx.getApplicationContext());
    }

    public static PreferenceUtils get(Context context) {
        if (sInstance == null) {
            synchronized (PreferenceUtils.class) {
                if (sInstance == null) {
                    sInstance = new PreferenceUtils(context);
                }
            }
        }
        return sInstance;
    }

    public boolean setBodySize(@Sizes.BodySize int size) {
        return mPreferences.edit().putInt(KEY_BODY_SIZE, size).commit();
    }

    @Sizes.BodySize
    public int getBodySize() {
        return mPreferences.getInt(KEY_BODY_SIZE, -1);
    }

    public boolean setFootSize(float size) {
        return mPreferences.edit().putFloat(KEY_FOOT_SIZE, size).commit();
    }

    public float getFootSize() {
        return mPreferences.getFloat(KEY_FOOT_SIZE, -1);
    }

    public boolean setGender(@Sizes.Gender int gender) {
        return mPreferences.edit().putInt(KEY_GENDER, gender).commit();
    }

    @Sizes.Gender
    public int getGender() {
        return mPreferences.getInt(KEY_GENDER, -1);
    }

    public BodyParams getBodyParams() {
        String gender;
        switch (getGender()) {
            case Sizes.FEMALE:
                gender = "female";
                break;
            case Sizes.MALE:
                gender = "male";
                break;
            default:
                gender = null;
        }
        float footSize = getFootSize();
        String bodySize;
        switch (getBodySize()) {
            case Sizes.L:
                bodySize = "L";
                break;
            case Sizes.M:
                bodySize = "M";
                break;
            case Sizes.S:
                bodySize = "S";
                break;
            case Sizes.XL:
                bodySize = "XL";
                break;
            case Sizes.XS:
                bodySize = "XS";
                break;
            case Sizes.XXL:
                bodySize = "XXL";
                break;
            case Sizes.XXXL:
                bodySize = "XXXL";
                break;
            default:
                bodySize = null;
                break;
        }
        return new BodyParams(gender, bodySize, footSize);
    }
}
