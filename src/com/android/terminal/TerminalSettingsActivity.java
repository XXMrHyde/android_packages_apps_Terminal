/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.terminal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.MenuItem;

import static com.android.terminal.Terminal.TAG;

/**
 * Settings for Terminal.
 */
public class TerminalSettingsActivity extends PreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_FULLSCREEN_MODE = "fullscreen_mode";
    public static final String KEY_SCREEN_ORIENTATION = "screen_orientation";
    public static final String KEY_FONT_SIZE = "font_size";
    public static final String KEY_BACKGROUND_COLOR = "background_color";
    public static final String KEY_TEXT_COLOR = "text_color";

    public static final String DEFAULT_TEXT_SIZE = "16";
    public static final String DARKKAT_BLUE_GREY = "#1b1f23";
    public static final String DEEP_TEAL_500 = "#009688";

    private SwitchPreference mFullscreenModePref;
    private ListPreference mScreenOrientationPref;
    private ListPreference mFontSizePref;
    private ListPreference mBackgroundColorPref;
    private ListPreference mTextColorPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);

        String stringValue;

        mFullscreenModePref = (SwitchPreference) findPreference(KEY_FULLSCREEN_MODE);

        mScreenOrientationPref = (ListPreference) findPreference(KEY_SCREEN_ORIENTATION);
        stringValue = sp.getString(KEY_SCREEN_ORIENTATION, "automatic");
        mScreenOrientationPref.setValue(stringValue);
        mScreenOrientationPref.setSummary(mScreenOrientationPref.getEntry());

        mFontSizePref = (ListPreference) findPreference(KEY_FONT_SIZE);
        stringValue = sp.getString(KEY_FONT_SIZE, DEFAULT_TEXT_SIZE);
        mFontSizePref.setValue(stringValue);
        mFontSizePref.setSummary(mFontSizePref.getEntry());


        mBackgroundColorPref = (ListPreference) findPreference(KEY_BACKGROUND_COLOR);
        stringValue = sp.getString(KEY_BACKGROUND_COLOR, DARKKAT_BLUE_GREY);
        mBackgroundColorPref.setValue(stringValue);
        mBackgroundColorPref.setSummary(mBackgroundColorPref.getEntry());

        mTextColorPref = (ListPreference) findPreference(KEY_TEXT_COLOR);
        stringValue = sp.getString(KEY_TEXT_COLOR, DEEP_TEAL_500);
        mTextColorPref.setValue(stringValue);
        mTextColorPref.setSummary(mTextColorPref.getEntry());

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        int index;
        if (KEY_SCREEN_ORIENTATION.equals(key)) {
            mScreenOrientationPref.setSummary(mScreenOrientationPref.getEntry());
        } else if (KEY_FONT_SIZE.equals(key)) {
            mFontSizePref.setSummary(mFontSizePref.getEntry());
        } else if (KEY_BACKGROUND_COLOR.equals(key)) {
            mBackgroundColorPref.setSummary(mBackgroundColorPref.getEntry());
        } else if (KEY_TEXT_COLOR.equals(key)) {
            mTextColorPref.setSummary(mTextColorPref.getEntry());
        }
    }
}
