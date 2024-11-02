package com.assignment.tictactoe.service.tic_tac_toe.service;

public class Winner {
    private Piece winningPiece;
    private int col1, col2, col3;
    private int row1, row2, row3;

    public Winner() {

    }

    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(Piece winningPiece, int col1, int row1,  int col2, int row2, int col3, int row3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }

    public void setWinningPiece(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public int getCol1() {
        return col1;
    }

    public void setCol1(int col1) {
        this.col1 = col1;
    }

    public int getCol2() {
        return col2;
    }

    public void setCol2(int col2) {
        this.col2 = col2;
    }

    public int getCol3() {
        return col3;
    }

    public void setCol3(int col3) {
        this.col3 = col3;
    }

    public int getRow1() {
        return row1;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public int getRow2() {
        return row2;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public int getRow3() {
        return row3;
    }

    public void setRow3(int row3) {
        this.row3 = row3;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", col2=" + col2 +
                ", col3=" + col3 +
                ", row1=" + row1 +
                ", row2=" + row2 +
                ", row3=" + row3 +
                '}';
    }
}
