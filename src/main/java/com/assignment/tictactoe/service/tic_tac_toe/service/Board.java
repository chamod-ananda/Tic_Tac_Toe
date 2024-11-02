package com.assignment.tictactoe.service.tic_tac_toe.service;

public interface Board {
    BoardUi getBoardUi();
    void boardInitialize();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Winner checkWinner();
    void printBoard();
    int[][] getState();
}
