package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OnePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private int turn;
    private int player1Points;
    private int player2Points;
    private int roundCount;
    private int computerTurni;

    private int randomNum1;
    private int randomNum2;

    private int iVertailu;
    private int jVertailu;

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
        computerTurni = 0;

        textViewTurn = findViewById(R.id.turnTextView);
        textViewTurn.setText("");

        updatePointsText();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                player1Points = 0;
                player2Points = 0;

                updatePointsText();
            }
        });

    }



    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (turn == 1) {
            ((Button) v).setText("X");

        } else {
            ((Button) v).setText("O");
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

        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }

        computerTurn();
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
        waitSecond();
        resetBoard();
    }

    public void player2Win()
    {
        player2Points++;
        Toast.makeText(this, "AI wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        waitSecond();
        resetBoard();
    }

    public void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        waitSecond();
        resetBoard();
    }

    public void updatePointsText()
    {
        textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        TextViewPlayer2 = findViewById(R.id.textViewPlayer2);

        textViewPlayer1.setText("X Player: "+ player1Points);
        TextViewPlayer2.setText("O AI:        "+ player2Points);
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
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }

    public void waitSecond() {
        try {
            TimeUnit.SECONDS.sleep((long) 0.5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void computerTurn()
    {

        try{
            Thread.sleep(100);                   // Wait for 3 Seconds
        } catch (Exception e){
            System.out.println("Error: "+e);      // Catch the exception
        }

        for (int i = 0; i < 3; i++) {
            if(computerTurni == 1)
            {
                break;
            }
            for (int j = 0; j < 3; j++) {
                if(computerTurni == 1)
                {
                    break;
                }

                if (buttons[i][j].getText() == "O" && buttons[i][jVertailu].getText() == "O" && computerTurni == 0) {
                    for(int x = 0; x < 3; x++){
                        if (buttons[i][x].getText() == ""){
                            buttons[i][x].setText("O");
                            computerTurni = 1;
                            break;

                        }
                    }
                }
                jVertailu = j;
            }
        }

        for (int j = 0; j < 3; j++) {
            if(computerTurni == 1)
            {
                break;
            }
            for (int i = 0; i < 3; i++) {
                if(computerTurni == 1)
                {
                    break;
                }

                if (buttons[i][j].getText() == "O" && buttons[iVertailu][j].getText() == "O" && computerTurni == 0) {
                    for(int x = 0; x < 3; x++){
                        if (buttons[x][j].getText() == ""){
                            buttons[x][j].setText("O");
                            computerTurni = 1;
                            break;

                        }
                    }
                }
                iVertailu = i;
            }
        }


        for (int i = 0; i < 3; i++) {
            if(computerTurni == 1)
            {
                break;
            }
            for (int j = 0; j < 3; j++) {
                if(computerTurni == 1)
                {
                    break;
                }

                if (buttons[i][j].getText() == "X" && buttons[i][jVertailu].getText() == "X" && computerTurni == 0) {
                    for(int x = 0; x < 3; x++){
                        if (buttons[i][x].getText() == ""){
                            buttons[i][x].setText("O");
                            computerTurni = 1;
                            break;

                        }
                    }
                }
                jVertailu = j;
            }
        }


        for (int j = 0; j < 3; j++) {
            if(computerTurni == 1)
            {
                break;
            }
            for (int i = 0; i < 3; i++) {
                if(computerTurni == 1)
                {
                    break;
                }

                if (buttons[i][j].getText() == "X" && buttons[iVertailu][j].getText() == "X" && computerTurni == 0) {
                    for(int x = 0; x < 3; x++){
                        if (buttons[x][j].getText() == ""){
                            buttons[x][j].setText("O");
                            computerTurni = 1;
                            break;

                        }
                    }
                }
                iVertailu = i;
            }
        }


        //Väliaikainen sen aikaa että saan ohjelmoitua AI:n lukemaan kulmasta kulmaan
        if(buttons[1][1].getText() == "")
        {
            buttons[1][1].setText("O");
            computerTurni = 1;
        }


        if (computerTurni == 0) {
            Random rn = new Random();
            for (int i = 0; i < 1000; i++) {
                randomNum1 = rn.nextInt(3) + 1;
                randomNum2 = rn.nextInt(3) + 1;
                randomNum1 = randomNum1 - 1;
                randomNum2 = randomNum2 - 1;

                if (buttons[randomNum1][randomNum2].getText() == "") {
                    buttons[randomNum1][randomNum2].setText("O");
                    break;
                }
            }
            computerTurni = 1;
        }
        computerTurni = 0;

        if (checkForWin()) {
            if (turn == 1) {
                player1Win();
            }
            else {
                player2Win();
            }
        }
        roundCount++;
        if (roundCount == 9){
            draw();
        }


        if (turn == 1) {
            turn = 2;
        } if (turn == 2) {
        turn = 1;
        }
    }

}