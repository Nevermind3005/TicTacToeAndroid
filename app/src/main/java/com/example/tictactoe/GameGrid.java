package com.example.tictactoe;

import com.example.tictactoe.enums.PlayerTurn;

public class GameGrid {

    private PlayerTurn[][] gameGrid;
    private PlayerTurn currentPlayerTurn;

    public GameGrid() {
        this.gameGrid = new PlayerTurn[3][3];
        currentPlayerTurn = PlayerTurn.x;
    }

    public String getAtGrid(int x, int y) {
        return gameGrid[x][y] == PlayerTurn.x ? "X" : "O";
    }

    public PlayerTurn getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public void addIfNull(int x, int y) {
        if (gameGrid[x][y] == null) {
            gameGrid[x][y] = currentPlayerTurn;
            switch (currentPlayerTurn) {
                case x:
                    currentPlayerTurn = PlayerTurn.o;
                    break;
                case o:
                    currentPlayerTurn = PlayerTurn.x;
                    break;
            }
        }
    }

    public boolean checkIfPlayerWon(PlayerTurn turn) {
        if(checkLeftToRight(turn)) {
            return true;
        }
        if(checkRightToLeft(turn)) {
            return true;
        }
        for(int i = 0; i < 3; i++) {
            if(checkHorizontalLine(turn,i)) {
                return true;
            }
            if(checkVerticalLine(turn,i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfTie() {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLeftToRight(PlayerTurn turn){
        return gameGrid[0][0] == turn && gameGrid[1][1] == turn && gameGrid[2][2] == turn;
    }

    private boolean checkRightToLeft(PlayerTurn turn){
        return gameGrid[0][2] == turn && gameGrid[1][1] == turn && gameGrid[2][0] == turn;
    }

    private boolean checkHorizontalLine(PlayerTurn turn,int x){
        return gameGrid[x][0] == turn && gameGrid[x][1] == turn && gameGrid[x][2] == turn;
    }

    private boolean checkVerticalLine(PlayerTurn turn,int y){
        return gameGrid[0][y] == turn && gameGrid[1][y] == turn && gameGrid[2][y] == turn;
    }

}
