package com.example.adamschlough.connect4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Switch switchView = findViewById(R.id.twoPlayerSwitch);

        switchView.setChecked(sharedPreferences.getBoolean("Two Player", false));

        setButtons();

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
                setButtons();
                break;
            case R.id.playerOneBlue:
                if (checked)
                    editor.putString("Player One Color", "blue").apply();
                setButtons();
                break;
            case R.id.playerOneYellow:
                if (checked)
                    editor.putString("Player One Color", "yellow").apply();
                setButtons();
                break;
            case R.id.playerOneGreen:
                if (checked)
                    editor.putString("Player One Color", "green").apply();
                setButtons();
                    break;
            case R.id.playerOnePurple:
                if (checked)
                    editor.putString("Player One Color", "purple").apply();
                setButtons();
                    break;
            case R.id.playerTwoRed:
                if (checked)
                    editor.putString("Player Two Color", "red").apply();
                setButtons();
                    break;
            case R.id.playerTwoBlue:
                if (checked)
                    editor.putString("Player Two Color", "blue").apply();
                setButtons();
                    break;
            case R.id.playerTwoYellow:
                if (checked)
                    editor.putString("Player Two Color", "yellow").apply();
                setButtons();
                    break;
            case R.id.playerTwoGreen:
                if (checked)
                    editor.putString("Player Two Color", "green").apply();
                setButtons();
                    break;
            case R.id.playerTwoPurple:
                if (checked)
                    editor.putString("Player Two Color", "purple").apply();
                setButtons();
                    break;
        }
    }

    public void setButtons(){

        RadioButton playerOneButton;
        RadioButton playerTwoButton;
        clearButtons();

        switch (sharedPreferences.getString("Player One Color", "red")){
            case "red":
                playerOneButton = findViewById(R.id.playerOneRed);
                findViewById(R.id.playerTwoRed).setEnabled(false);
                playerOneButton.setChecked(true);
                break;
            case "blue":
                playerOneButton =(findViewById(R.id.playerOneBlue));
                findViewById(R.id.playerTwoBlue).setEnabled(false);
                playerOneButton.setChecked(true);
                break;
            case "yellow":
                playerOneButton =(findViewById(R.id.playerOneYellow));
                findViewById(R.id.playerTwoYellow).setEnabled(false);
                playerOneButton.setChecked(true);
                break;
            case "green":
                playerOneButton =(findViewById(R.id.playerOneGreen));
                findViewById(R.id.playerTwoGreen).setEnabled(false);
                playerOneButton.setChecked(true);
                break;
            case "purple":
                playerOneButton =(findViewById(R.id.playerOnePurple));
                findViewById(R.id.playerTwoPurple).setEnabled(false);
                playerOneButton.setChecked(true);
                break;
            default:
                break;
        }

        switch (sharedPreferences.getString("Player Two Color", "blue")){
            case "red":
                playerTwoButton =(findViewById(R.id.playerTwoRed));
                findViewById(R.id.playerOneRed).setEnabled(false);
                playerTwoButton.setChecked(true);
                break;
            case "blue":
                playerTwoButton =(findViewById(R.id.playerTwoBlue));
                findViewById(R.id.playerOneBlue).setEnabled(false);
                playerTwoButton.setChecked(true);
                break;
            case "yellow":
                playerTwoButton =(findViewById(R.id.playerTwoYellow));
                findViewById(R.id.playerOneYellow).setEnabled(false);
                playerTwoButton.setChecked(true);
                break;
            case "green":
                playerTwoButton =(findViewById(R.id.playerTwoGreen));
                findViewById(R.id.playerOneGreen).setEnabled(false);
                playerTwoButton.setChecked(true);
                break;
            case "purple":
                playerTwoButton =(findViewById(R.id.playerTwoPurple));
                findViewById(R.id.playerOnePurple).setEnabled(false);
                playerTwoButton.setChecked(true);
                break;
            default:
                break;
        }
    }

    public void clearButtons(){

        RadioButton playerOneRed = findViewById(R.id.playerOneRed);
        RadioButton playerOneBlue = findViewById(R.id.playerOneBlue);
        RadioButton playerOneYellow = findViewById(R.id.playerOneYellow);
        RadioButton playerOneGreen = findViewById(R.id.playerOneGreen);
        RadioButton playerOnePurple = findViewById(R.id.playerOnePurple);
        RadioButton playerTwoRed = findViewById(R.id.playerTwoRed);
        RadioButton playerTwoBlue = findViewById(R.id.playerTwoBlue);
        RadioButton playerTwoYellow = findViewById(R.id.playerTwoYellow);
        RadioButton playerTwoGreen = findViewById(R.id.playerTwoGreen);
        RadioButton playerTwoPurple = findViewById(R.id.playerTwoPurple);

        playerOneRed.setEnabled(true);
        playerOneBlue.setEnabled(true);
        playerOneYellow.setEnabled(true);
        playerOneGreen.setEnabled(true);
        playerOnePurple.setEnabled(true);
        playerTwoRed.setEnabled(true);
        playerTwoBlue.setEnabled(true);
        playerTwoYellow.setEnabled(true);
        playerTwoGreen.setEnabled(true);
        playerTwoPurple.setEnabled(true);

    }

    public void onSwitchClick(View view){
        editor = sharedPreferences.edit();
        if (!sharedPreferences.getBoolean("Two Player", false)){
            editor.putBoolean("Two Player", true).apply();
        } else if (sharedPreferences.getBoolean("Two Player", false)){
            editor.putBoolean("Two Player", false).apply();
        }
    }

    public void onSaveClick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
