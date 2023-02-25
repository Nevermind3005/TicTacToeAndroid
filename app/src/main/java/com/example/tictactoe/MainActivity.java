package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.enums.GameResult;
import com.example.tictactoe.enums.PlayerTurn;

public class MainActivity extends AppCompatActivity {

    private GameGrid gameGrid;
    private String playerNameX = "";
    private String playerNameY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        setContentView(R.layout.activity_main);

        Button setPlayerNamesButton = findViewById(R.id.set_player_names_button);
        setPlayerNamesButton.setOnClickListener(e -> setPlayerNameActivity());

        setPlayerTurn(gameGrid.getCurrentPlayerTurn());
        initializeGridVisual();
    }

    private void resetGame() {
        initialize();
        clearGridVisual();
    }

    private void setPlayerTurn(PlayerTurn playerTurn) {
        TextView turnTextView = findViewById(R.id.turn_text_view);
        String turn = "";
        switch (playerTurn) {
            case x:
                turn = "X (" + playerNameX + ")";
                break;
            case o:
                turn = "O (" + playerNameY + ")";
                break;
        }
        turnTextView.setText(getText(R.string.player_turn) + turn);
    }

    private void setPlayerNameActivity() {
        Intent playerNameIntent = new Intent(this, NameEditActivity.class);
        playerNameIntent.putExtra("player_x", playerNameX);
        playerNameIntent.putExtra("player_y", playerNameY);
        startActivityForResult(playerNameIntent, 1000);
    }

    private void setGameResultActivity(GameResult result, String playerName) {
        Intent gameResultIntent = new Intent(this, GameResultActivity.class);
        gameResultIntent.putExtra("game_result", result);
        gameResultIntent.putExtra("player_name", playerName);
        startActivity(gameResultIntent);
    }

    private void initialize() {
        gameGrid = new GameGrid();
    }

    private void clearGridVisual() {
        for (int i = 1; i <= 9; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id",
                    this.getPackageName()));
            button.setText("");
        }
        setPlayerTurn(gameGrid.getCurrentPlayerTurn());
    }

    private void initializeGridVisual() {
        int x = 0;
        int y = 0;
        for (int i = 1; i <= 9; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            int finalX = x;
            int finalY = y;
            button.setOnClickListener(e -> {
                PlayerTurn currentPlayer = gameGrid.getCurrentPlayerTurn();
                gameGrid.addIfNull(finalX, finalY);
                button.setText(gameGrid.getAtGrid(finalX, finalY));
                setPlayerTurn(gameGrid.getCurrentPlayerTurn());
                if (gameGrid.checkIfPlayerWon(currentPlayer)) {
                    GameResult winner = currentPlayer == PlayerTurn.x ? GameResult.X_WINNER : GameResult.O_WINNER;
                    String winnerName = currentPlayer == PlayerTurn.x ? playerNameX : playerNameY;
                    setGameResultActivity(winner, winnerName);
                    resetGame();
                } else if (gameGrid.checkIfTie()) {
                    setGameResultActivity(GameResult.TIE_WINNER, "");
                    resetGame();
                }
            });
            y++;
            if (y == 3) {
                y = 0;
                x++;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if(resultCode == Activity.RESULT_OK){
                playerNameX = data.getStringExtra("player_x");
                playerNameY = data.getStringExtra("player_y");
            }
        }
        setPlayerTurn(gameGrid.getCurrentPlayerTurn());
    }

}