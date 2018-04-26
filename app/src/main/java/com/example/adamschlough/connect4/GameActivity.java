package com.example.adamschlough.connect4;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int activePlayer = 0; //0 = red, 1 = blue

    int[] gameState = {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2}; //2 is active 3 is not active

    ArrayList<int[]> winningPosition = new ArrayList<>();

    SharedPreferences preferences;

    public ArrayList<int[]> setWinningPositions(View view){

        GridLayout gridLayout = (GridLayout) view;

        int[] array;

        for (int i = 0; i < gridLayout.getRowCount() * gridLayout.getColumnCount() - 1; i++){
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount()) {
                array = new int[]{i, i + 7, i + 14, i + 21};
                winningPosition.add(array);
            }
            if (i % gridLayout.getColumnCount() <= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 1, i + 2, i + 3};
                winningPosition.add(array);
            }
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount() && i % gridLayout.getColumnCount() <= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 8, i + 16, i + 24};
                winningPosition.add(array);
            }
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount() && i % gridLayout.getColumnCount() >= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 6, i + 12, i + 18};
                winningPosition.add(array);
            }
        }
        return winningPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        TextView textView = findViewById(R.id.turnTextView);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int firstPlayerColor = getFirstPlayerColor();
        textView.setVisibility(View.VISIBLE);
        textView.setText(R.string.player_one_first);
        textView.setTextColor(firstPlayerColor);

        setWinningPositions(gridLayout);
    }

    public void onTokenClick(View view) {
        ImageView playToken = (ImageView) view;

        int tappedPiece = Integer.parseInt(playToken.getTag().toString());

        if (gameState[tappedPiece] == 2) {

            if (tappedPiece > 6){
                gameState[tappedPiece - 7] = 2;
            }
            setToken(playToken);
            if (checkGameOver()){
                showGameOver();
            }
            if (preferences.getBoolean("Two Player", false)){
                if(!checkWinState()){
                    secondPlayer();
                    checkWinState();
                    if (checkGameOver()){
                        showGameOver();
                    }
                }
            }
            else {
                checkWinState();
                checkGameOver();
            }
        }
    }

    public void onPlayAgainClick(View view) {

        LinearLayout layout = findViewById(R.id.gameOverLayout);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        TextView turnText = findViewById(R.id.turnTextView);

        layout.setVisibility(View.INVISIBLE);
        turnText.setVisibility(View.VISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 3;

        }

        for (int i = 35; i < gameState.length; i++){

            gameState[i] = 2;

        }

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    private boolean checkWinState(){
        int firstColor = getFirstPlayerColor();
        int secondColor = getSecondPlayerColor();

        LinearLayout layout = findViewById(R.id.gameOverLayout);
        TextView textView = findViewById(R.id.winnnerText);
        TextView turnText = findViewById(R.id.turnTextView);

        for (int i = 0; i < winningPosition.size(); i++){

            int[] array = winningPosition.get(i);

            if (gameState[array[0]] == gameState[array[1]] &&
                    gameState[array[1]] == gameState[array[2]]&&
                    gameState[array[2]] == gameState[array[3]] &&
                    gameState[array[0]] != 2 && gameState[array[0]] != 3){

                layout.setVisibility(View.VISIBLE);
                turnText.setVisibility(View.INVISIBLE);
                turnText.setText(R.string.player_one_first);
                turnText.setTextColor(getResources().getColor(firstColor));
                if (gameState[array[0]] == 0){
                    layout.setBackgroundColor(getResources().getColor(firstColor));
                    textView.setTextColor(Color.BLACK);
                }else{
                    layout.setBackgroundColor(getResources().getColor(secondColor));
                    textView.setTextColor(Color.WHITE);
                }
                for (int j = 0; j < gameState.length; j++) {
                    gameState[j] = 3;
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkGameOver(){
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getRowCount() * gridLayout.getColumnCount(); i++){
            if (gameState[i] == 2 || gameState[i] == 3){
                return false;
            }
        }
        Log.i("GameOver", "Game Over");
        return true;
    }

    private void secondPlayer(){

        Random random = new Random();
        int position = random.nextInt(42);

        //Check to see if there are 3 in a row and play there if so
        for (int i = 0; i < winningPosition.size(); i++){
            int[] array = winningPosition.get(i);

            if (gameState[array[0]] == gameState[array[1]] &&
                    gameState[array[1]] == gameState[array[2]]&&
                    gameState[array[0]] != 2 && gameState[array[0]] != 3 &&
                    gameState[array[3]] == 2) {
                position = array[3];
            } else if (gameState[array[0]] == gameState[array[1]] &&
                    gameState[array[1]] == gameState[array[3]]&&
                    gameState[array[0]] != 2 && gameState[array[0]] != 3 &&
                    gameState[array[2]] == 2){
                position = array[2];
            } else if (gameState[array[0]] == gameState[array[2]] &&
                    gameState[array[2]] == gameState[array[3]]&&
                    gameState[array[0]] != 2 && gameState[array[0]] != 3 &&
                    gameState[array[1]] == 2){
                position = array[1];
            } else if (gameState[array[1]] == gameState[array[2]] &&
                    gameState[array[2]] == gameState[array[3]]&&
                    gameState[array[1]] != 2 && gameState[array[1]] != 3 &&
                    gameState[array[0]] == 2){
                position = array[0];
            }
        }

        while (gameState[position] != 2){
            position = random.nextInt(42);
        }

        Log.i("Random", "Setting piece at redChip" + position);

        int resID = getResources().getIdentifier("redChip" + position, "id", getPackageName());

        final View secondToken = findViewById(resID);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                setToken(secondToken);
            }
        }, 1000);

        if (position > 6){
            gameState[position - 7] = 2;
        }
    }

    private void setToken(View view){
        int firstColor = getFirstPlayerColor();
        int secondColor = getSecondPlayerColor();

        ImageView playToken = (ImageView) view;

        TextView turnTextView = findViewById(R.id.turnTextView);

        int tappedPiece = Integer.parseInt(playToken.getTag().toString());

        gameState[tappedPiece] = activePlayer;

        playToken.setTranslationY(-1000f);

        if (activePlayer == 0) {

            playToken.setImageResource(R.drawable.redpiece);
            playToken.setColorFilter(getResources().getColor(firstColor));

            turnTextView.setTextColor(getResources().getColor(secondColor));
            turnTextView.setText(getString(R.string.player_two_turn));

            activePlayer = 1;

        } else {

            playToken.setImageResource(R.drawable.redpiece);
            playToken.setColorFilter(getResources().getColor(secondColor));

            turnTextView.setTextColor(getResources().getColor(firstColor));
            turnTextView.setText(getString(R.string.player_one_turn));

            activePlayer = 0;
        }

        playToken.animate().translationYBy(1000f).setDuration(300);

    }

    public void showGameOver(){

        LinearLayout layout = findViewById(R.id.gameOverLayout);
        TextView textView = findViewById(R.id.winnnerText);
        TextView turnText = findViewById(R.id.turnTextView);

        textView.setTextColor(Color.WHITE);
        layout.setVisibility(View.VISIBLE);
        layout.setBackgroundColor(Color.BLACK);
        turnText.setVisibility(View.INVISIBLE);
        turnText.setText(R.string.player_one_first);
        turnText.setTextColor(Color.BLACK);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 3;
        }

    }

    public int getFirstPlayerColor() {
        String firstColorPref = preferences.getString("Player One Color", "red");
        int firstColor;

        switch (firstColorPref){
            case "red":
                firstColor = R.color.colorRed;
                break;
            case "blue":
                firstColor = R.color.colorBlue;
                break;
            case "yellow":
                firstColor = R.color.colorYellow;
                break;
            case "green":
                firstColor = R.color.colorGreen;
                break;
            case "purple":
                firstColor = R.color.colorPurple;
                break;
            default:
                firstColor = R.color.colorRed;

        }

        return firstColor;
    }

    public int getSecondPlayerColor(){
        String secondColorPref = preferences.getString("Player Two Color", "blue");
        int secondColor;

        switch (secondColorPref){
            case "red":
                secondColor = R.color.colorRed;
                break;
            case "blue":
                secondColor = R.color.colorBlue;
                break;
            case "yellow":
                secondColor = R.color.colorYellow;
                break;
            case "green":
                secondColor = R.color.colorGreen;
                break;
            case "purple":
                secondColor = R.color.colorPurple;
                break;
            default:
                secondColor = R.color.colorRed;

        }
        return secondColor;
    }
}
