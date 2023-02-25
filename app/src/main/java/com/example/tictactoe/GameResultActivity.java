package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.enums.GameResult;

public class GameResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        Button closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(e -> finish());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object value = extras.get("game_result");
            String playerName = extras.getString("player_name");
            if (value instanceof GameResult) {
                setWinnerTextView((GameResult) value, playerName);
            }
        }
    }

    private void setWinnerTextView(GameResult gameResult, String playerName) {
        TextView resultTextView = findViewById(R.id.winner_text_view);
        String winner = "";
        switch (gameResult) {
            case X_WINNER:
                winner = getString(R.string.x_winner) + "(" + playerName + ")";
                break;
            case O_WINNER:
                winner = getString(R.string.o_winner) + "(" + playerName + ")";
                break;
            case TIE_WINNER:
                winner = getString(R.string.tie_winner);
                break;
        }
        resultTextView.setText(winner);
    }

}