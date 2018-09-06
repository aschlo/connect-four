package com.example.adamschlough.connect4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Player {
    private SharedPreferences preferences;

    public int getColor(String player, Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String colorPref = preferences.getString(player, "red");
        int color;

        switch (colorPref){
            case "red":
                color = R.color.colorRed;
                break;
            case "blue":
                color = R.color.colorBlue;
                break;
            case "yellow":
                color = R.color.colorYellow;
                break;
            case "green":
                color = R.color.colorGreen;
                break;
            case "purple":
                color = R.color.colorPurple;
                break;
            default:
                color = R.color.colorRed;

        }

        return color;
    }

}
