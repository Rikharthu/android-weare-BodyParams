package com.example.rikharthu.myparameters;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class GenderPreference extends TwoStatePreference {

    @BindView(R.id.pref_gender_image)
    ImageView mGenderImageView;
    @BindView(R.id.pref_gender_title)
    TextView mTitleTextView;

    public GenderPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public GenderPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GenderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GenderPreference(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gender_preference, parent, false);
        ButterKnife.bind(this, view);
        if (isChecked()) {
            // Female
            ((Animatable) mGenderImageView.getDrawable()).start();
        } else {
            // Male
//            ((Animatable) mGenderImageView.getDrawable()).start();
        }

        return view;
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        Timber.d("Setting checked: " + checked);
    }
}
