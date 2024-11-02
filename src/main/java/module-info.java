module com.assignment.tictactoe.service.tic_tac_toe {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.assignment.tictactoe.service.tic_tac_toe.controller to javafx.fxml;
    exports com.assignment.tictactoe.service.tic_tac_toe;
}