package com.example.adamschlough.connect4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Player {

    int getColor(String player, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String colorPref = "red";
        if (player.equals("Player One Color")) {
            colorPref = preferences.getString(player, "red");
        } else if (player.equals("Player Two Color")){
            colorPref = preferences.getString(player, "blue");
        }
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
