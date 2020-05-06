package e.naveen.myapplicationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean game=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedcountner = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedcountner] == 2  && game) {

            gameState[tappedcountner] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.download);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]]
                        && gameState[winningPositions[0]] != 2) {
                    //System.out.println(gameState[winningPositions[0]]);
                    String winner="Red";
                    if(gameState[winningPositions[0]]==0){
                        winner="Yellow";
                    }


                    TextView winningMessage =(TextView) findViewById(R.id.winningMessage);
                    winningMessage.setText(winner+"  has won!"); ;
                    game=true;

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIs=true;
                    for(int counterState :gameState){
                        if(counterState==2) gameIs =false;
                        if(gameIs){

                            TextView winningMessage =(TextView) findViewById(R.id.winningMessage);
                            winningMessage.setText("Its draw"); ;
                            game=true;
                        }

                    }
                }

            }


        }
    }
    public void playAgain(View view){
        game=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridId);
        for(int i=0;i<9;i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

    }