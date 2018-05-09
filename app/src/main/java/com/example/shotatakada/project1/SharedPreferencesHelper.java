package com.example.shotatakada.project1;

import android.content.SharedPreferences;

class SharedPreferencesHelper {

    public final String KEY_ENTRY = "input";

    private final SharedPreferences sharedPref;

    /**
     * Constructor with dependency injection.
     *
     * @param sharedPref The {@link SharedPreferences} that will be used in this DAO.
     */
    public SharedPreferencesHelper(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    /**
     * Saves the given string to {@link SharedPreferences}.
     *
     * @param message contains string entry to save to {@link SharedPreferences}.
     * @return {@code true} if writing to {@link SharedPreferences} succeeded. {@code false}
     *         otherwise.
     */
    public boolean saveEntry(String message){
        // Start a SharedPreferences transaction.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY_ENTRY, message);

        // Commit changes to SharedPreferences & return success/failure result
        return editor.commit();
    }

    public String getEntry() {
        return sharedPref.getString(KEY_ENTRY, "");
    }
}
