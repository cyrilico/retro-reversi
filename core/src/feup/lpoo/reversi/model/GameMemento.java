package feup.lpoo.reversi.model;

import java.io.Serializable;

public class GameMemento implements Serializable {
    private BoardModel board;
    private TurnState turn;

    public GameMemento(BoardModel board, TurnState turn) {
        this.board = board;
        this.turn = turn;
    }

    public BoardModel getBoard() {
        return board;
    }

    public TurnState getTurn() {
       return turn;
    }
}
