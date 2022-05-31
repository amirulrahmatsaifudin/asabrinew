package com.example.asabri.api;

import android.content.SharedPreferences;

import android.content.SharedPreferences;

import com.example.asabri.model.AccessToken;

public class TokenManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs) {
        if (INSTANCE == null) {
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(AccessToken token) {
        editor.putString("API_TOKEN", token.getToken()).commit();
    }

    public void deleteToken() {
        editor.remove("API_TOKEN").commit();
    }

    public AccessToken getToken() {
        AccessToken token = new AccessToken();
        token.setToken(prefs.getString("API_TOKEN", null));
        return token;
    }
}
