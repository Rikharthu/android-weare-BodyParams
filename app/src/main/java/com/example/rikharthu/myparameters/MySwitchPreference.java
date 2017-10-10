package com.example.rikharthu.myparameters;

import android.content.Context;
import android.graphics.Color;
import android.preference.SwitchPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MySwitchPreference extends SwitchPreference {

    private Switch mSwitch;

    public MySwitchPreference(Context context) {
        super(context);
    }

    public MySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MySwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        mSwitch = findSwitchInChildViews((ViewGroup) view);
        if (mSwitch != null) {
            changeColor();
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    changeColor();
                }
            });
        }
    }

    private void changeColor() {
        if (mSwitch.isChecked()) {
            mSwitch.getThumbDrawable().setTint(Color.parseColor("#DF95AF"));
        } else {
            mSwitch.getThumbDrawable().setTint(Color.parseColor("#4040E6"));
        }
    }

    private Switch findSwitchInChildViews(ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View thisChildview = view.getChildAt(i);
            if (thisChildview instanceof Switch) {
                return (Switch) thisChildview;
            } else if (thisChildview instanceof ViewGroup) {
                Switch theSwitch = findSwitchInChildViews((ViewGroup) thisChildview);
                if (theSwitch != null) return theSwitch;
            }
        }
        return null;
    }
}
