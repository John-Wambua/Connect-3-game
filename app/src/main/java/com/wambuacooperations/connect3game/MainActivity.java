package com.wambuacooperations.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: yellow 1: red 2:empty
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    Button playAgainbutton;
    TextView winnerTextView;

    int activePlayer=0;
    boolean gamePlaying=true;


    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamePlaying) {
            if (gameState[tappedCounter] == 2) {

                counter.setTranslationY(-2000);
                gameState[tappedCounter] = activePlayer;

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(2000).rotation(1000).setDuration(300);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                        //Someone has won
                        gamePlaying = false;
                        String message = "";
                        if (activePlayer == 1) {
                            message = "Yellow";
                        } else {
                            message = "Red";
                        }
                        playAgainbutton=(Button) findViewById(R.id.playAgainButton);
                        winnerTextView=(TextView) findViewById(R.id.winnerTextView);

                        winnerTextView.setText(message+" has won!");
                        winnerTextView.setVisibility(View.VISIBLE);
                        playAgainbutton.setVisibility(View.VISIBLE);
                       // Toast.makeText(this, message + " has won the game", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    }

    public void playAgain(View view){
        playAgainbutton=(Button) findViewById(R.id.playAgainButton);
        winnerTextView=(TextView) findViewById(R.id.winnerTextView);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainbutton.setVisibility(View.INVISIBLE);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gamePlaying=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
