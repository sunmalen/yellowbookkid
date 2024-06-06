package com.malen.yellowbookkidmidterm;
import android.content.Context;
import android.content.SharedPreferences;

class SharedPrefsManager {
    private static final String PREF_NAME = "example_prefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(String key, String value, String s) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void clearData() {
        editor.clear();
        editor.apply();
    }
}
