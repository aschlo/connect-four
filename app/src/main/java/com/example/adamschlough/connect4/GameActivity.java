package com.example.adamschlough.connect4;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    int activePlayer = 0; //0 = red, 1 = blue

    int[] gameState = {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2}; //2 is active 3 is not active

    ArrayList<int[]> winningPostion = new ArrayList<>();

    public ArrayList<int[]> setWinningPositions(View view){

        GridLayout gridLayout = (GridLayout) view;

        int[] array;

        for (int i = 0; i < gridLayout.getRowCount() * gridLayout.getColumnCount() - 1; i++){
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount()) {
                array = new int[]{i, i + 7, i + 14, i + 21};
                winningPostion.add(array);
            }
            if (i % gridLayout.getColumnCount() <= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 1, i + 2, i + 3};
                winningPostion.add(array);
            }
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount() && i % gridLayout.getColumnCount() <= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 8, i + 16, i + 24};
                winningPostion.add(array);
            }
            if (i < (gridLayout.getRowCount() - 3) * gridLayout.getColumnCount() && i % gridLayout.getColumnCount() >= gridLayout.getColumnCount() - 4) {
                array = new int[]{i, i + 6, i + 12, i + 18};
                winningPostion.add(array);
            }
        }
        return winningPostion;
    }

    public void redDropIn(View view) {

        ImageView playToken = (ImageView) view;

        TextView turnTextView = findViewById(R.id.turnTextView);

        int tappedPiece = Integer.parseInt(playToken.getTag().toString());

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        if (gameState[tappedPiece] == 2) {

            if (tappedPiece > 6){
            gameState[tappedPiece - 7] = 2;
            }

            gameState[tappedPiece] = activePlayer;

            playToken.setTranslationY(-1000f);

            if (activePlayer == 0) {

                playToken.setImageResource(R.drawable.redpiece);
                playToken.setColorFilter(Color.RED);

                turnTextView.setBackgroundColor(Color.BLUE);
                turnTextView.setTextColor(Color.WHITE);
                turnTextView.setText(getString(R.string.blue_turn));

                activePlayer = 1;

            } else {

                playToken.setImageResource(R.drawable.redpiece);
                playToken.setColorFilter(Color.BLUE);

                turnTextView.setTextColor(Color.BLACK);
                turnTextView.setBackgroundColor(Color.RED);
                turnTextView.setText(getString(R.string.red_turn));

                activePlayer = 0;
            }

            playToken.animate().translationYBy(1000f).setDuration(300);
        }

//        if (gameState[tappedPiece] == 3){
//
//            for (int i = 0; i < gridLayout.getRowCount(); i++){
//                int changedPiece = tappedPiece + 7 * i;
//
//                ImageView playToken =
//
//                if (gameState[changedPiece] == 2){
//
//                    if (changedPiece > 6){
//                        gameState[changedPiece - 7] = 2;
//                    }
//
//                    gameState[changedPiece] = activePlayer;
//
//                    playToken.setTranslationY(-1000f);
//
//                    if (activePlayer == 0) {
//
//                        playToken.setImageResource(R.drawable.redpiece);
//                        playToken.setColorFilter(Color.RED);
//
//                        turnTextView.setBackgroundColor(Color.BLUE);
//                        turnTextView.setTextColor(Color.WHITE);
//                        turnTextView.setText(getString(R.string.blue_turn));
//
//                        activePlayer = 1;
//
//                    } else {
//
//                        playToken.setImageResource(R.drawable.redpiece);
//                        playToken.setColorFilter(Color.BLUE);
//
//                        turnTextView.setTextColor(Color.BLACK);
//                        turnTextView.setBackgroundColor(Color.RED);
//                        turnTextView.setText(getString(R.string.red_turn));
//
//                        activePlayer = 0;
//                    }
//
//
//                    playToken.animate().translationYBy(1000f).setDuration(300);
//
//
//
//                }
//
//            }
//
//        }
        checkWinState();
        checkGameOver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        TextView turnTextView = findViewById(R.id.turnTextView);
        turnTextView.setText(getString(R.string.red_goes_first));
        turnTextView.setBackgroundColor(Color.RED);
        setWinningPositions(gridLayout);
    }

    public void playAgain(View view) {

        LinearLayout layout = findViewById(R.id.gameOverLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 3;

        }

        for (int i = 35; i < gameState.length; i++){

            gameState[i] = 2;

        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    private void checkWinState(){

        for (int i = 0; i < winningPostion.size(); i++){

            int[] array = winningPostion.get(i);

            if (gameState[array[0]] == gameState[array[1]] &&
                    gameState[array[1]] == gameState[array[2]]&&
                    gameState[array[2]] == gameState[array[3]] &&
                    gameState[array[0]] != 2 && gameState[array[0]] != 3){

                LinearLayout layout = findViewById(R.id.gameOverLayout);
                TextView textView = findViewById(R.id.winnnerText);
                layout.setVisibility(View.VISIBLE);
                if (gameState[array[0]] == 0){
                    layout.setBackgroundColor(Color.RED);
                    textView.setTextColor(Color.BLACK);
                }else{
                    layout.setBackgroundColor(Color.BLUE);
                    textView.setTextColor(Color.WHITE);
                }
                for (int j = 0; j < gameState.length; j++) {
                    gameState[j] = 3;
                }
            }
        }
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

    }

}
