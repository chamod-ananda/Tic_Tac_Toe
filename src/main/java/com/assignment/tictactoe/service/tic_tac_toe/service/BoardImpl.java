package com.assignment.tictactoe.service.tic_tac_toe.service;

public class BoardImpl implements Board {
    private final BoardUi boardUi;
    private final int SIZE = 3;
    private Piece[][] board;

    public BoardImpl(BoardUi boardUi) {
        this.boardUi = boardUi;
        board = new Piece[SIZE][SIZE];
        boardInitialize();
    }

    @Override
    public BoardUi getBoardUi() {
        return this.boardUi;
    }

    @Override
    public void boardInitialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        return board[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    public void restartGame() {
        boardInitialize();
    }

    public void findWinner() {
        Winner winner = checkWinner();

        if (winner != null) {
            boardUi.notifyWinner(winner.getWinningPiece());
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Winner checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != Piece.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return new Winner(board[i][0], 0, i, 1, i, 2, i); // Row i
            }
            if (board[0][i] != Piece.EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return new Winner(board[0][i], i, 0, i, 1, i, 2); // Column i
            }
        }
        if (board[0][0] != Piece.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return new Winner(board[0][0], 0, 0, 1, 1, 2, 2);
        }
        if (board[0][2] != Piece.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return new Winner(board[0][2], 2, 0, 1, 1, 0, 2);
        }
        if (isBoardFull()) {
            return new Winner(Piece.EMPTY);
        }
        return null;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int[][] getState() {
        int[][] state = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Piece.EMPTY) {
                    state[i][j] = 0;
                } else if (board[i][j] == Piece.X) {
                    state[i][j] = 1;
                } else {
                    state[i][j] = -1;
                }
            }
        }
        printBoard();
        return state;
    }
}
