package com.assignment.tictactoe.service.tic_tac_toe.service;

public class HumanPlayer extends Player {
    boolean legalMove;

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void move(int row, int col) {
        legalMove = board.isLegalMove(row, col);

        if (legalMove) {
            board.updateMove(row, col, Piece.X);
            board.getBoardUi().update(row, col, true);
        }
    }
}
