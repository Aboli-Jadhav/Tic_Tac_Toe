package com.example.tictactoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1_turn=true;
    private int player1_points;
    private int player2_points;
    private int roundCount;
    private TextView player1_txt,player2_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1_txt=(TextView) findViewById(R.id.txt_player1);
        player2_txt=(TextView) findViewById(R.id.txt_player2);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String btnID="btn_"+i+j;
                int resID=getResources().getIdentifier(btnID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);

                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset_btn=findViewById(R.id.btn_reset);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(!((Button) view).getText().toString().equals(""))
        {
            return;
        }
        if(player1_turn)
        {
            ((Button) view).setText("X");
        }
        else
        {
            ((Button) view).setText("O");
        }
        roundCount++;
        if(chk_for_Win())
        {
            if(player1_turn)
            {
                    player1_Wins();
            }
            else
            {
                    player2_Wins();
            }
        }
        else if(roundCount==9)
        {
            draw();
        }
        else
        {
            player1_turn=!player1_turn;
        }
    }

    private  boolean chk_for_Win()
    {
        String[][] button_txt=new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                button_txt[i][j]=buttons[i][j].getText().toString();

            }
        }

        for(int i=0;i<3;i++)
        {
            if(button_txt[i][0].equals(button_txt[i][1])&&button_txt[i][0].equals(button_txt[i][2])&&!(button_txt[i][0].equals("")))
            {
                return  true;
            }
        }

        for(int i=0;i<3;i++)
        {
            if(button_txt[0][i].equals(button_txt[1][i])&&button_txt[0][i].equals(button_txt[2][i])&&!(button_txt[0][i].equals("")))
            {
                return  true;
            }
        }

        if(button_txt[0][0].equals(button_txt[1][1])&&button_txt[0][0].equals(button_txt[2][2])&&!(button_txt[0][0].equals("")))
        {
            return  true;
        }

        if(button_txt[0][2].equals(button_txt[1][1])&&button_txt[0][2].equals(button_txt[2][0])&&!(button_txt[0][2].equals("")))
        {
            return  true;
        }
        return  false;
    }

    private  void player1_Wins()
    {
        player1_points++;
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private  void player2_Wins()
    {
        player2_points++;
        Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void draw()
    {
        Toast.makeText(this,"Draw!",Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private  void updatePointsText()
    {
        player1_txt.setText("Player 1 :"+player1_points);
        player2_txt.setText("Player 2 :"+player2_points);
    }

    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");

            }
        }
        roundCount=0;
        player1_turn=true;
        //player2_points=0;
        //player2_points=0;
    }
    private  void resetGame()
    {
        player2_points=0;
        player1_points=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1_points",player1_points);
        outState.putInt("player2_points",player2_points);
        outState.putBoolean("player1_turn",player1_turn);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount=savedInstanceState.getInt("roundCount");
        player1_points=savedInstanceState.getInt("player1_points");
        player2_points=savedInstanceState.getInt("player2_points");
        player1_turn=savedInstanceState.getBoolean("player1_turn");
    }
}