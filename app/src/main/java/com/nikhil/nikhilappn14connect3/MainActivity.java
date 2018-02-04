package com.nikhil.nikhilappn14connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int yellow = 0;
    int red = 0;
    //score


    int activePlayer = 0;
    // 0=Yellow 1=Red
    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropin(View view){

        TextView scr = (TextView) findViewById(R.id.textView3);

        scr.setText("Yellow: " + yellow + " Red: " + red);

        TextView chance = (TextView) findViewById(R.id.textView4);
        if (activePlayer == 1){
            chance.setText(">Yellow");
            chance.setTextColor(getResources().getColor(R.color.yellow));
        }
        else {
            chance.setText(">Red");
            chance.setTextColor(getResources().getColor(R.color.red));
        }

        ImageView counter = (ImageView) view;
        //(ImageView)- normal view is converted to ImageView


        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;


            }

            counter.animate().translationYBy(1000f).rotation(180).setDuration(300);

            for (int[] winPos: winningPositions) {




                if (gameState[winPos[0]] == gameState[winPos[1]] &&
                        gameState[winPos[1]] == gameState[winPos[2]] &&
                        gameState[winPos[0]] != 2) {

                    String winner = "Red";

                    System.out.println(gameState[winPos[0]]);
                    //Someone has won

                    gameIsActive = false;

                    if (gameState[winPos[0]] == 0) {

                        winner = "Yellow";
                        yellow++;
                    }
                    else{
                        red++;

                    }



                    chance.setText(">Yellow");
                    chance.setTextColor(getResources().getColor(R.color.yellow));

                    scr.setText("Yellow: " + yellow + " Red: " + red);
                    //score updation


                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won");




                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {
                        //counterState will be eash positon in gameState

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a Draw");
                        chance.setText(">Yellow");
                        chance.setTextColor(getResources().getColor(R.color.yellow));
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);


                    }

                }
            }
            }





    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        gameIsActive=true;
        activePlayer = 0;


        for (int i=0; i < gameState.length; i++){

            gameState[i]=2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i=0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }


    public void resetScore(View view){

        yellow=0;
        red=0;
        TextView scr = (TextView) findViewById(R.id.textView3);
        scr.setText("Yellow: " + yellow + " Red: " + red);
        //score updation


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
