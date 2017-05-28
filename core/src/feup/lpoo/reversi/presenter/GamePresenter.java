package feup.lpoo.reversi.presenter;

import feup.lpoo.reversi.Reversi;
import feup.lpoo.reversi.model.GameModel;
import feup.lpoo.reversi.model.MoveModel;
import feup.lpoo.reversi.model.PlayerModel;

/**
 * Created by antonioalmeida on 12/05/2017.
 */

public abstract class GamePresenter {
    protected Reversi reversi;
    protected GameModel game;
    protected PlayerModel blackPlayer;
    protected PlayerModel whitePlayer;

    public GamePresenter(Reversi reversi) {
        this.reversi = reversi;
    }

    public abstract void restartGame();

    public String getBlackPoints() {
        String result = String.format("%02d", game.getBlackPoints());
        return result;
    }

    public String getWhitePoints() {
        String result = String.format("%02d", game.getWhitePoints());
        return result;
    }

    public String getResult() {
        int black = game.getBlackPoints();
        int white = game.getWhitePoints();

        String result;
        if(black > white)
            result =  "Black wins!";
        else if(white > black)
            result =  "White wins!";
        else
            result = "It's a tie!";
        return result;
    }

    public void handleInput(int x, int y) throws CloneNotSupportedException {
        MoveModel move = game.getValidMove(x, y);

        if(move != null) {
            game.getCurrentPlayer().setMove(move);
            game.getCurrentPlayer().setReady();
        }
    }

    public boolean updateGame() {
        if(!game.isOver()) {
            if (game.getCurrentPlayer().isReady()) {
                game.getCurrentPlayer().resetReady();
                game.updateGame();
            }
            return true;
        }

        return false;
    }

    public boolean gameOver() {
        return game.isOver();
    }

    public abstract void updateAchievements();

    public char getCurrentPiece(int x, int y) {
        return game.getPieceAt(x,y);
    }

    public abstract void initPlayers();

    public boolean isBlackTurn() {
        return game.getCurrentPlayer() == blackPlayer;
    }

    public boolean isWhiteTurn() {
        return game.getCurrentPlayer() == whitePlayer;
    }

    public abstract void undoMove();
}
