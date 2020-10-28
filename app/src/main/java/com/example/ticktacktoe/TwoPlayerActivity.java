package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private int turn;
    private int player1Points;
    private int player2Points;
    private int roundCount;

    private TextView textViewPlayer1;
    private TextView TextViewPlayer2;
    private TextView textViewTurn;
    private Button[][] buttons = new Button[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        turn = 1;
        roundCount = 0;

        //////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        /////////////////////////////////////////////////////////////////////////

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetBoard();
                    player1Points = 0;
                    player2Points = 0;

                    textViewPlayer1.setText("X Player 1: "+ player1Points);
                    TextViewPlayer2.setText("O Player 2: "+ player2Points);
                }
            });
        }
        //////////////////////////////////////////////////////////////////////////////////

            @Override
            public void onClick(View v) {
                if (!((Button) v).getText().toString().equals("")) {
                    return;
                }
                if (turn == 1) {
                    ((Button) v).setText("X");
                    turn = 2;
                } else {
                    ((Button) v).setText("O");
                    turn = 1;
                }


                roundCount++;
                if (roundCount == 9){
                    draw();
                }
                if (checkForWin()) {
                    if (turn == 1) {
                        player1Win();
                    }
                    else {
                        player2Win();
                    }
                }
                updateTurnTextView();

            }

            private boolean checkForWin() {
                String[][] field = new String[3][3];

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        field[i][j] = buttons[i][j].getText().toString();
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if (field[i][0].equals(field[i][1])
                            && field[i][0].equals(field[i][2])
                            && !field[i][0].equals("")) {
                        return true;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if (field[0][i].equals(field[1][i])
                            && field[0][i].equals(field[2][i])
                            && !field[0][i].equals("")) {
                        return true;
                    }
                }
                if (field[0][0].equals(field[1][1])
                        && field[0][0].equals(field[2][2])
                        && !field[0][0].equals("")) {
                    return true;
                }
                if (field[0][2].equals(field[1][1])
                        && field[0][2].equals(field[2][0])
                        && !field[0][2].equals("")) {
                    return true;
                }
                return false;
    }



    public void player1Win()
    {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
        updateTurnTextView();
    }

    public void player2Win()
    {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
        updateTurnTextView();
    }

    public void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    public void updatePointsText()
    {
        textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        TextViewPlayer2 = findViewById(R.id.textViewPlayer2);

        textViewPlayer1.setText("X Player 1: "+ player1Points);
        TextViewPlayer2.setText("O Player 2: "+ player2Points);
    }

    public void updateTurnTextView()
    {
        textViewTurn = findViewById(R.id.turnTextView);

        if (turn == 1) {
            textViewTurn.setText("Turn: X");
        } else {
            //turn = 1;
            textViewTurn.setText("Turn: O");
        }
    }


    public void resetBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        turn = 1;
        updateTurnTextView();
    }

    public void resetButtonClicked(View view) {
        resetBoard();
    }
}
