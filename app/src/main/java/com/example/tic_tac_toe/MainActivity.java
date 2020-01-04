package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //red=0,yellow=1,empty=2
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int [][] winningpos={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8} ,{2,4,6}};
    int activeplayer=0;
    boolean gameactive=true;
    public void dropin(View view)
    {
        ImageView counter= (ImageView) view;
        counter.setTranslationY(-1500);

        int tappedcoutner=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcoutner]==2 && gameactive) {
            gamestate[tappedcoutner] = activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.red);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.green);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(350);

            for (int[] wp : winningpos) {
                if (gamestate[wp[0]] == gamestate[wp[1]] && gamestate[wp[1]] == gamestate[wp[2]] && gamestate[wp[0]] != 2) {
                    gameactive=false;
                    String winningplayer="";
                    if(activeplayer==1)
                    {
                        winningplayer="RED";
                    }
                    else
                    {
                        winningplayer="GREEN";
                    }
                    TextView winnerTXT=findViewById(R.id.winnertxt);
                    Button playagain=findViewById(R.id.button);
                    winnerTXT.setText(winningplayer + " has won!");
                    playagain.setVisibility(View.VISIBLE);
                    winnerTXT.setVisibility(View.VISIBLE);


                }
            }
        }
    }
    public void playagain(View view)
    {
        TextView winnerTXT=findViewById(R.id.winnertxt);
        Button playagain=findViewById(R.id.button);
        playagain.setVisibility(View.INVISIBLE);
        winnerTXT.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout= (androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridlayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        gameactive=true;
         activeplayer=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
