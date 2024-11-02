package com.assignment.tictactoe.service.tic_tac_toe.service;

import java.util.ArrayList;
import java.util.List;

public class AiPlayer extends Player {
    private final int HUMAN = +1;
    private final int AI = -1;
    private Board board;

    public AiPlayer(Board board) {
        super(board);
        this.board = board;
    }

    public int[] getBestMove() {
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        List<int[]> availableMoves = getAvailableMoves(board.getState());

        for (int[] move : availableMoves) {
            board.updateMove(move[0], move[1], Piece.O);
            int score = minimax(board.getState(), 0, false);
            board.updateMove(move[0], move[1], Piece.EMPTY);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int minimax(int[][] state, int depth, boolean isMaximizing) {
        int score = evaluate(state);

        if (score != 0 || getAvailableMoves(state).isEmpty()) {
            return score;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int[] move : getAvailableMoves(state)) {
                state[move[0]][move[1]] = AI;
                bestScore = Math.max(bestScore, minimax(state, depth + 1, false));
                state[move[0]][move[1]] = 0;
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int[] move : getAvailableMoves(state)) {
                state[move[0]][move[1]] = HUMAN;
                bestScore = Math.min(bestScore, minimax(state, depth + 1, true));
                state[move[0]][move[1]] = 0;
            }
            return bestScore;
        }
    }

    private List<int[]> getAvailableMoves(int[][] state) {
        List<int[]> moves = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (state[row][col] == 0) {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    private int evaluate(int[][] state) {
        if (wins(state, AI)) {
            return 1;
        }
        if (wins(state, HUMAN)) {
            return -1;
        }
        return 0;
    }

    private boolean wins(int[][] state, int player) {
        for (int i = 0; i < 3; i++) {
            if (state[i][0] == player && state[i][1] == player && state[i][2] == player) {
                return true;
            }
            if (state[0][i] == player && state[1][i] == player && state[2][i] == player) {
                return true;
            }
        }
        if (state[0][0] == player && state[1][1] == player && state[2][2] == player) {
            return true;
        }
        if (state[2][0] == player && state[1][1] == player && state[0][2] == player) {
            return true;
        }
        return false;
    }

    @Override
    public void move(int row, int col) {
        int[] bestMove = getBestMove();
        if (bestMove[0] != -1 && bestMove[1] != -1) {
            board.updateMove(bestMove[0], bestMove[1], Piece.O);
            board.getBoardUi().update(bestMove[0], bestMove[1], false);
        }
    }
}
