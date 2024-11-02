package com.assignment.tictactoe.service.tic_tac_toe.service;

public interface BoardUi {
    void update(int col, int row, boolean isHuman);
    void  notifyWinner(Piece winner);
}
