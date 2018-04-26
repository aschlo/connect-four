package com.example.adamschlough.connect4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void onRadioButtonClicked(View view) {
        editor = sharedPreferences.edit();
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.playerOneRed:
                if (checked)
                    editor.putString("Player One Color", "red").apply();
                    break;
            case R.id.playerOneBlue:
                if (checked)
                    editor.putString("Player One Color", "blue").apply();
                    break;
            case R.id.playerOneYellow:
                if (checked)
                    editor.putString("Player One Color", "yellow").apply();
                break;
            case R.id.playerOneGreen:
                if (checked)
                    editor.putString("Player One Color", "green").apply();
                    break;
            case R.id.playerOnePurple:
                if (checked)
                    editor.putString("Player One Color", "purple").apply();
                    break;
            case R.id.playerTwoRed:
                if (checked)
                    editor.putString("Player Two Color", "red").apply();
                    break;
            case R.id.playerTwoBlue:
                if (checked)
                    editor.putString("Player Two Color", "blue").apply();
                    break;
            case R.id.playerTwoYellow:
                if (checked)
                    editor.putString("Player Two Color", "yellow").apply();
                    break;
            case R.id.playerTwoGreen:
                if (checked)
                    editor.putString("Player Two Color", "green").apply();
                    break;
            case R.id.playerTwoPurple:
                if (checked)
                    editor.putString("Player Two Color", "purple").apply();
                    break;
        }
    }
}
