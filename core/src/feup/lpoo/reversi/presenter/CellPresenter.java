package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.model.GameModel;

/**
 * Created by antonioalmeida on 12/05/2017.
 */

public class CellPresenter {
    private int x;
    private int y;

    public CellPresenter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void handleInput() throws CloneNotSupportedException {
        int move = getValidMove();

        if(move != -1) {
            GameModel.getInstance().getCurrentPlayer().setMoveIndex(move);
            GameModel.getInstance().updateGame();
        }
    }

    public int getValidMove() throws CloneNotSupportedException {
        return GameModel.getInstance().isValidMove(x, y);
    }

    public char getCurrentPiece() {
        char piece = 0;
        piece = GameModel.getInstance().getPieceAt(x,y);

        return piece;
    }
}
