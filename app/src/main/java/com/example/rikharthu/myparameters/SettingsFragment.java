package com.example.rikharthu.myparameters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import timber.log.Timber;

public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.body_params);

        SharedPreferences prefs = getPreferenceScreen().getSharedPreferences();
        BodyParams params = new BodyParams(
                prefs.getBoolean("pref_gender", false) ? "female" : "male",
                prefs.getString("pref_body_size", null),
                Float.parseFloat(prefs.getString("pref_foot_size", "-1")));
        Timber.d(params.toString());
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Timber.d("Changed: " + key);

        if (key.equals("pref_gender")) {
            SwitchPreference genderPref = (SwitchPreference) findPreference(key);
            boolean isFemale = sharedPreferences.getBoolean(key, false);
            if (isFemale) {
                genderPref.setTitle("Female");
            } else {
                genderPref.setTitle("Male");
            }

            ListPreference footSizePref = (ListPreference) findPreference("pref_foot_size");
            if (isFemale) {
                footSizePref.setEntries(R.array.pref_female_foot_sizes);
                footSizePref.setEntryValues(R.array.pref_female_foot_sizes);
            } else {
                footSizePref.setEntries(R.array.pref_male_foot_sizes);
                footSizePref.setEntryValues(R.array.pref_male_foot_sizes);
            }
//            footSizePref.setTitle(footSizePref.getTitle() + " (changed)");
        } else if (key.equals("pref_foot_size")) {
            SharedPreferences prefs = getPreferenceScreen().getSharedPreferences();
            Timber.d(prefs.getString("pref_foot_size", null));
        }
    }
}
