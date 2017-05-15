package feup.lpoo.reversi.model;

import java.util.ArrayList;

/**
 * Created by antonioalmeida on 13/05/2017.
 */

public class GameMemento {
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
