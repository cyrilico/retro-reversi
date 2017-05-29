package feup.lpoo.reversi.model;

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
