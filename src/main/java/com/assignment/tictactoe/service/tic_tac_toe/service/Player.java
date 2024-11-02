package com.assignment.tictactoe.service.tic_tac_toe.service;

public abstract class Player {
    protected Board board;

    public Player(Board board) {
        this.board = board;
    }

    public abstract void move(int row, int col);
}
