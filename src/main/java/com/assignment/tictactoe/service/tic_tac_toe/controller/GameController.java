package com.assignment.tictactoe.service.tic_tac_toe.controller;

import com.assignment.tictactoe.service.tic_tac_toe.service.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameController implements BoardUi {

    private static final int GRID_SIZE = 3;
    private String currentPlayer = "X";
    private String playerName = "Chamod";
    private AiPlayer aiPlayer;
    private HumanPlayer humanPlayer;
    private BoardImpl board;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button20;

    @FXML
    private Button button21;

    @FXML
    private Button button22;

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane pane1;

    @FXML
    private JFXButton playAgainButton;

    @FXML
    private Label playerLabel;

    private Button[][] grid;
    private boolean isGameOver;

    @FXML
    private void initialize() {
        board = new BoardImpl(this);
        makeGrid();
        gameInitialize();
        isGameOver = false;
    }

    private void gameInitialize() {
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AiPlayer(board);
        board.boardInitialize();
    }

    private void makeGrid() {
        grid = new Button[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button buttonActions = new Button();
                buttonActions.setMinSize(137, 119);
                buttonActions.setOnMouseClicked(this::onButtonClick);
                gridPane.add(buttonActions, j, i);
                grid[i][j] = buttonActions;
            }
        }
    }

    @FXML
    private void onButtonClick(MouseEvent event) {
        Button buttonClick = (Button) event.getSource();
        int[] position = findPosition(buttonClick);

        if (position != null && !isGameOver && buttonClick.getText().isEmpty()) {
            humanPlayer.move(position[0], position[1]);
            board.findWinner();

            if (!isGameOver) {
                Platform.runLater(() -> {
                   aiPlayer.move(-1, -1);
                   board.findWinner();
                });
            }
        }
    }

    private int[] findPosition(Button buttonClick) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == buttonClick) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        if (isGameOver) return;
        Button updateButton = grid[row][col];

        if (updateButton.getText().isEmpty()) {
            updateButton.setText(currentPlayer);
            updateButton.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
            playerLabel.setText("Current Player: " + (isHuman ? playerName : "AI"));
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    @Override
    public void notifyWinner(Piece winningPiece) {
        isGameOver = true;

        if (winningPiece == Piece.X) {
            playerLabel.setText(playerName + " has won the game!");
            playerLabel.setStyle("-fx-text-fill: #06D001;");
        } else if (winningPiece == Piece.O) {
            playerLabel.setText("AI has won the game!");
            playerLabel.setStyle("-fx-text-fill: #E4003A;");
        } else {
            playerLabel.setText("It's a tie game!");
            playerLabel.setStyle("-fx-text-fill: #F72798;");
        }
    }

    @FXML
    void playAgainButtonOnAction(ActionEvent event) {
        restartGame();
    }

    private void restartGame() {
        playerLabel.setStyle("-fx-text-fill: #758694;");
        board.restartGame();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j].setText("");
                grid[i][j].setStyle("-fx-background-color: transparent; -fx-border-color: black;");
            }
        }
        currentPlayer = "X";
        isGameOver = false;
        playerLabel.setText(playerName + ", It's your turn!");
    }
}
